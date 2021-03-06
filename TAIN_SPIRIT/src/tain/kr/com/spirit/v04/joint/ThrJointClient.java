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

	private DataInputStream dis;
	private DataOutputStream dos;
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	private final boolean connectJoint() throws Exception {
	
		if (flag) {
			/*
			 * socket of joint
			 */
			try {
				this.socketJoint = new Socket(this.jointHost, Integer.parseInt(this.jointPort));
			} catch (Exception e) {
				throw e;
			}
			
			if (this.socketJoint == null) {
				throw new Exception("the value of socketJoint is null pointer...");
			}
			
			this.dis = new DataInputStream(this.socketJoint.getInputStream());
			this.dos = new DataOutputStream(this.socketJoint.getOutputStream());
			
			if (flag) log.debug(String.format("connected to the joint server named '%s'.", this.socketJoint));
		}
		
		if (flag) {
			/*
			 * send data "REQ" to joint client
			 * recv data "RES" from joint client, waiting for this signal
			 */
			try {
				send("REQ");
				if (flag) log.debug(String.format("send a data 'REQ' to the joint server and wait for a data 'RES' from the joint server."));
				
				recv();
				if (flag) log.debug(String.format("recv a data 'RES' from the joint server."));
			} catch (Exception e) {
				if (this.socketJoint != null) try { this.socketJoint.close(); } catch (IOException e1) {}
				throw e;
			}
		}

		if (flag) {
			/*
			 * option
			 *    SO_TIMEOUT
			 *    SO_LINGER
			 */
			int timeout = 5 * 1000;
			
			this.socketJoint.setSoTimeout(timeout);
			
			if (flag) log.debug(String.format("set the option of SO_TIMEOUT '%d ms' in the joint socket.", timeout));
			
			if (flag && this.socketJoint.getSoLinger() == -1) {  // SO_LINGER
				this.socketJoint.setSoLinger(true, 0);
				if (flag) log.debug(String.format("set the option of SO_LINGER '%d' in the joint socket.", this.socketJoint.getSoLinger()));
			}
		}
		
		return true;
	}
	
	private final boolean connectTarget() throws Exception {
		
		if (flag) {
			/*
			 * socket of joint
			 */
			try {
				this.socketTarget = new Socket(this.targetHost, Integer.parseInt(this.targetPort));
			} catch (Exception e) {
				if (this.socketJoint != null) try { this.socketJoint.close(); } catch (IOException e1) {}
				throw e;
			}
			
			if (this.socketTarget == null) {
				if (this.socketJoint != null) try { this.socketJoint.close(); } catch (IOException e) {}
				throw new Exception("the value of socketTarget is null pointer...");
			}
			
			if (flag) log.debug(String.format("connected to the target server named '%s'.", this.socketTarget));
		}

		if (flag) {
			/*
			 * option
			 *    SO_TIMEOUT
			 *    SO_LINGER
			 */
			int timeout = 5 * 1000;

			this.socketTarget.setSoTimeout(timeout);
			
			if (flag) log.debug(String.format("set the option of SO_TIMEOUT '%d ms' in the target socket.", timeout));

			if (flag && this.socketTarget.getSoLinger() == -1) {  // SO_LINGER
				this.socketTarget.setSoLinger(true, 0);
				if (flag) log.debug(String.format("set the option of SO_LINGER '%d' in the target socket.", this.socketTarget.getSoLinger()));
			}
		}

		return true;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private final boolean send(final String req) throws Exception {
		
		byte[] bytSend = req.getBytes();
		
		try {
			this.dos.write(bytSend, 0, bytSend.length);
		} catch (IOException e) {
			throw e;    // -> finish
		}
		
		if (flag) log.debug(String.format("[SEND] '%s'.", req));
		
		return true;
	}
	
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
				 * check RES
				 */
				String str = new String(bytRecv, 0, nRecv);
				if (!"RES".equalsIgnoreCase(str)) {
					/*
					 * not match
					 */
					throw new Exception(String.format("%s [EOF] there is no signal 'RES'..", Thread.currentThread().getName()));
				}

				if (flag) log.debug(String.format("[RECV] '%s'.", str));
			}
		} catch (SocketTimeoutException e) {
			throw e;   // -> finish
		} catch (Exception e) {
			throw e;   // -> finish
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
		
		if (flag) log.debug(String.format("[START] data transformation between the joint server and the target server."));
		
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
			
			if (flag) System.out.printf("\t%s [END THREAD] ...\n", Thread.currentThread().getName());
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
