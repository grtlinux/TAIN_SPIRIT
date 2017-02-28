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
import java.net.Socket;
import java.net.SocketTimeoutException;

import org.apache.log4j.Logger;

import tain.kr.com.spirit.v04.param.ParamContent;
import tain.kr.com.spirit.v04.recvsend.ThrRecvSend;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : ThrJointClient.java
 *   -. Package    : tain.kr.com.spirit.v03.joint
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 2. 27. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public final class ThrJointClient extends AbsJoint {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(ThrJointClient.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private static final String THREAD_NAME = "JOINT_CLIENT";
	
	private static final String KEY_JOINT_HOST = "tain.kr.com.spirit.joint.host";
	private static final String KEY_JOINT_PORT = "tain.kr.com.spirit.joint.port";
	private static final String KEY_TARGET_HOST = "tain.kr.com.spirit.target.host";
	private static final String KEY_TARGET_PORT = "tain.kr.com.spirit.target.port";

	private final String jointHost;
	private final String jointPort;
	private final String targetHost;
	private final String targetPort;

	private Socket socketJoint;
	private Socket socketTarget;
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public ThrJointClient() throws Exception {
		
		super(THREAD_NAME);
		
		this.jointHost = ParamContent.getInstance().getString(KEY_JOINT_HOST, "192.168.0.11");
		this.jointPort = ParamContent.getInstance().getString(KEY_JOINT_PORT, "20025");
		
		this.targetHost = ParamContent.getInstance().getString(KEY_TARGET_HOST, "192.168.0.11");
		this.targetPort = ParamContent.getInstance().getString(KEY_TARGET_PORT, "13389");
		
		connectJoint();
		connectTarget();

		if (flag)
			log.debug(">>>>> in class " + this.getClass().getSimpleName());
	}

	///////////////////////////////////////////////////////////////////////////////////////////////

	private final boolean connectJoint() throws Exception {
	
		if (flag) {
			/*
			 * socket of joint
			 */
			this.socketJoint = new Socket(this.jointHost, Integer.parseInt(this.jointPort));
			if (this.socketJoint == null) {
				throw new Exception("the value of socketJoint is null pointer...");
			}
		}
		
		if (flag) {
			/*
			 * send data "REQ" to joint client
			 */
			try {
				send("REQ");
			} catch (Exception e) {
				throw e;
			} finally {
				try { this.socketJoint.close(); } catch (IOException e) {}
			}
		}

		if (flag) {
			/*
			 * recv data "RES" from joint client, waiting for this signal
			 */
			try {
				recv();
			} catch (Exception e) {
				throw e;
			} finally {
				try { this.socketJoint.close(); } catch (IOException e) {}
			}
		}

		if (flag) {
			/*
			 * option
			 */
			this.socketJoint.setSoTimeout(5 * 1000);
		}
		
		return true;
	}
	
	private boolean connectTarget() throws Exception {
		
		if (flag) {
			/*
			 * socket of joint
			 */
			this.socketTarget = new Socket(this.targetHost, Integer.parseInt(this.targetPort));
			if (this.socketTarget == null) {
				try { this.socketJoint.close(); } catch (IOException e) {}
				throw new Exception("the value of socketTarget is null pointer...");
			}
		}

		return true;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private boolean send(final String req) throws Exception {
		
		DataOutputStream dos = new DataOutputStream(this.socketJoint.getOutputStream());
		byte[] bytSend = req.getBytes();
		
		try {
			dos.write(bytSend, 0, bytSend.length);
		} catch (IOException e) {
			throw e;    // -> finish
		} finally {
			if (dos != null) try { dos.close(); } catch (IOException e) {}
		}
		
		return true;
	}
	
	private boolean recv() throws Exception {
		
		DataInputStream dis = new DataInputStream(this.socketJoint.getInputStream());
		byte[] bytRecv = new byte[10];
		int nRecv;
		
		try {
			nRecv = dis.read(bytRecv, 0, 10);
			if (nRecv < 0) {
				/*
				 * EOF -> finish
				 * socket rejection
				 */
				throw new Exception(String.format("%s [EOF] return value of read is -1..", Thread.currentThread().getName()));
			}
			
			if (flag) {
				/*
				 * check RES
				 */
				String str = new String(bytRecv, 0, nRecv);
				if (!"RES".equalsIgnoreCase(str)) {
					/*
					 * not match
					 */
					throw new Exception(String.format("%s [EOF] there is no signal 'RES'..", Thread.currentThread().getName()));
				}
			}
		} catch (SocketTimeoutException e) {
			throw e;   // -> finish
		} catch (Exception e) {
			throw e;   // -> finish
		} finally {
			if (dis != null) try { dis.close(); } catch (IOException e) {}
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
				this.thread1 = new ThrRecvSend(String.format("JOINT_CLIENT_RECVSEND_01"), this
						, this.socketJoint.getInputStream(), this.socketTarget.getOutputStream());

				this.thread2 = new ThrRecvSend(String.format("JOINT_CLIENT_RECVSEND_02"), this
						, this.socketTarget.getInputStream(), this.socketJoint.getOutputStream());
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
			if (this.socketTarget != null) try { this.socketTarget.close(); } catch (IOException e) {}
			
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
