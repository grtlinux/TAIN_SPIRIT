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
package tain.kr.com.spirit.v03.joint;

import java.io.IOException;
import java.net.Socket;

import org.apache.log4j.Logger;

import tain.kr.com.spirit.v03.param.ParamContent;
import tain.kr.com.spirit.v03.recvsend.ThrRecvSend;

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
	
	private static final String KEY_TARGET_HOST = "tain.kr.com.spirit.target.host";
	private static final String KEY_TARGET_PORT = "tain.kr.com.spirit.target.port";

	private static final String KEY_JOINT_HOST = "tain.kr.com.spirit.joint.host";
	private static final String KEY_JOINT_PORT = "tain.kr.com.spirit.joint.port";
	
	private final String targetHost;
	private final String targetPort;

	private final String jointHost;
	private final String jointPort;
	
	private Socket socket1;
	private Socket socket2;
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public ThrJointClient() throws Exception {
		
		super(THREAD_NAME);
		
		this.targetHost = ParamContent.getInstance().getString(KEY_TARGET_HOST, "192.168.0.11");
		this.targetPort = ParamContent.getInstance().getString(KEY_TARGET_PORT, "3389");
		
		this.jointHost = ParamContent.getInstance().getString(KEY_JOINT_HOST, "192.168.0.11");
		this.jointPort = ParamContent.getInstance().getString(KEY_JOINT_PORT, "13389");
		
		if (flag) {
			/*
			 * socket
			 */
			this.socket1 = new Socket(this.targetHost, Integer.parseInt(this.targetPort));
			this.socket2 = new Socket(this.jointHost, Integer.parseInt(this.jointPort));
		}
		
		if (flag) {
			/*
			 * validate
			 */
			if (this.socket1 == null) {
				throw new Exception("the value of socket1 is null pointer...");
			}

			if (this.socket2 == null) {
				throw new Exception("the value of socket2 is null pointer...");
			}
		}
		
		if (flag) {
			/*
			 * set options
			 */
			this.socket1.setSoTimeout(10 * 1000);
			this.socket2.setSoTimeout(10 * 1000);
		}

		if (flag)
			log.debug(">>>>> in class " + this.getClass().getSimpleName());
	}

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
						, this.socket2.getInputStream(), this.socket1.getOutputStream());

				this.thread2 = new ThrRecvSend(String.format("JOINT_CLIENT_RECVSEND_02"), this
						, this.socket1.getInputStream(), this.socket2.getOutputStream());
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
			if (this.socket1 != null) try { this.socket1.close(); } catch (IOException e) {}
			if (this.socket2 != null) try { this.socket2.close(); } catch (IOException e) {}
			
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

		if (flag)
			new ThrJointClient();

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
