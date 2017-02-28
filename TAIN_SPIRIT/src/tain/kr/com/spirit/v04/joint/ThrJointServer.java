/**
 * Copyright 2014, 2015, 2016, 2017 TAIN, Inc. all rights reserved.
 *
 * Licensed under the GNU GENERAL PUBLIC LICENSE, Version 3, 29 June 2007 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.gnu.org/licenses/
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 * -----------------------------------------------------------------
 * Copyright 2014, 2015, 2016, 2017 TAIN, Inc.
 *
 */
package tain.kr.com.spirit.v04.joint;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;

import org.apache.log4j.Logger;

import tain.kr.com.spirit.v04.recvsend.ThrRecvSend;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : ThrJointServer.java
 *   -. Package    : tain.kr.com.spirit.v03.joint
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 2. 27. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public final class ThrJointServer extends AbsJoint {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(ThrJointServer.class);

	///////////////////////////////////////////////////////////////////////////////////////////////

	private static final String THREAD_NAME = "JOINT_SERVER";
	
	private final ServerSocket jointServerSocket;
	
	private Socket socketJoint;
	private Socket socketClient;
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public ThrJointServer(ServerSocket jointServerSocket) throws Exception {
		
		super(THREAD_NAME);
		
		this.jointServerSocket = jointServerSocket;
		
		listenJoint();   // waiting for the connection of joint client
		listenClient();  // waiting for the connection of client
		
		if (flag)
			log.debug(">>>>> in class " + this.getClass().getSimpleName());
	}

	///////////////////////////////////////////////////////////////////////////////////////////////

	private DataInputStream dis;
	private DataOutputStream dos;
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	private final boolean listenJoint() throws Exception {
		
		if (flag) {
			/*
			 * socket of joint
			 */
			this.socketJoint = this.jointServerSocket.accept();
			if (this.socketJoint == null) {
				throw new Exception("the value of socketJoint is null pointer...");
			}
			
			this.dis = new DataInputStream(this.socketJoint.getInputStream());
			this.dos = new DataOutputStream(this.socketJoint.getOutputStream());
		}
		
		if (flag) {
			/*
			 * option
			 */
			this.socketJoint.setSoTimeout(5 * 1000);
		}
		
		if (flag) {
			/*
			 * recv data "REQ" from joint client
			 */
			try {
				recv();
			} catch (Exception e) {
				if (this.socketJoint != null) try { this.socketJoint.close(); } catch (IOException e1) {}
				throw e;
			}
		}
		
		return true;
	}
	
	private final boolean listenClient() throws Exception {
		
		if (flag) {
			/*
			 * socket of joint
			 */
			this.socketClient = this.jointServerSocket.accept();
			if (this.socketClient == null) {
				if (this.socketJoint != null) try { this.socketJoint.close(); } catch (IOException e) {}
				throw new Exception("the value of socketClient is null pointer...");
			}
		}
		
		if (flag) {
			/*
			 * option
			 */
			this.socketClient.setSoTimeout(5 * 1000);
		}
		
		if (flag) {
			/*
			 * send data "RES" to joint client
			 */
			try {
				send("RES");
			} catch (Exception e) {
				if (this.socketJoint != null) try { this.socketJoint.close(); } catch (IOException e1) {}
				if (this.socketClient != null) try { this.socketClient.close(); } catch (IOException e1) {}
				throw e;
			}
		}
		
		return true;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	private final boolean recv() throws Exception {
		
		byte[] bytRecv = new byte[10];
		int nRecv;
		
		try {
			nRecv = this.dis.read(bytRecv, 0, 10);
			if (nRecv < 0) {
				/*
				 * EOF -> finish
				 * socket rejection
				 */
				throw new Exception(String.format("%s [EOF] return value of read is -1..", Thread.currentThread().getName()));
			}
			
			if (flag) {
				/*
				 * check REQ
				 */
				String str = new String(bytRecv, 0, nRecv);
				if (!"REQ".equals(str)) {
					/*
					 * not match
					 */
					throw new Exception(String.format("%s [EOF] there is no signal 'REQ'..", Thread.currentThread().getName()));
				}
			}
		} catch (SocketTimeoutException e) {
			throw e;   // -> finish
		} catch (Exception e) {
			throw e;   // -> finish
		}

		return true;
	}
	
	private final boolean send(final String res) throws Exception {
		
		byte[] bytSend = res.getBytes();
		
		try {
			this.dos.write(bytSend, 0, bytSend.length);
		} catch (IOException e) {
			throw e;    // -> finish
		}
		
		return true;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private ThrRecvSend thread1;
	private ThrRecvSend thread2;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public void run() {
		
		this.thread1 = null;
		this.thread2 = null;
		
		if (flag) {
			/*
			 * create thread
			 */
			try {
				this.thread1 = new ThrRecvSend(String.format("JOINT_SERVER_RECVSEND_01"), this
						, this.socketJoint.getInputStream(), this.socketClient.getOutputStream());

				this.thread2 = new ThrRecvSend(String.format("JOINT_SERVER_RECVSEND_02"), this
						, this.socketClient.getInputStream(), this.socketJoint.getOutputStream());
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}
		
		if (flag) {
			/*
			 * start thread
			 */
			this.thread1.start();
			this.thread2.start();
		}
		
		if (flag) {
			/*
			 * join thread
			 */
			try {
				this.thread1.join();
				this.thread2.join();
			} catch (InterruptedException e) {}
		}
		
		if (flag) {
			/*
			 * close
			 */
			if (this.socketJoint != null) try { this.socketJoint.close(); } catch (IOException e) {}
			if (this.socketClient != null) try { this.socketClient.close(); } catch (IOException e) {}
			
			if (flag) System.out.printf("%s [END] ...\n", Thread.currentThread().getName());
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * static test method
	 */
	private static void test01(String[] args) throws Exception {

		if (flag) {

		}
	}

	/*
	 * main method
	 */
	public static void main(String[] args) throws Exception {

		if (flag)
			log.debug(">>>>> " + new Object() {
			}.getClass().getEnclosingClass().getName());

		if (flag)
			test01(args);
	}
}
