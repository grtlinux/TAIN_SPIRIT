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
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

import tain.kr.com.spirit.v03.main.MainControlServer;
import tain.kr.com.spirit.v03.recvsend.ThrRecvSend;

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
	
	private static final String STR_MSG = "SIGN";
	
	private final MainControlServer mainControlServer;
	private final ServerSocket jointServerSocket;
	
	private Socket socket1;
	private Socket socket2;
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public ThrJointServer(MainControlServer mainControlServer, ServerSocket jointServerSocket) throws Exception {
		
		super(THREAD_NAME);
		
		this.mainControlServer = mainControlServer;
		this.jointServerSocket = jointServerSocket;
		
		if (flag) {
			/*
			 * make socket to connect
			 */
			this.socket1 = this.jointServerSocket.accept();  // MainClient connection
			
			/*
			 * send signal
			 */
			this.mainControlServer.getQueue().put(STR_MSG);  // send signal
			
			this.socket2 = this.jointServerSocket.accept();  // ThrJointClient connection
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
				this.thread1 = new ThrRecvSend(String.format("JOINT_SERVER_RECVSEND_01"), this
						, this.socket1.getInputStream(), this.socket2.getOutputStream());

				this.thread2 = new ThrRecvSend(String.format("JOINT_SERVER_RECVSEND_02"), this
						, this.socket2.getInputStream(), this.socket1.getOutputStream());
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
			
			if (flag) System.out.printf("[%s] END ...\n", Thread.currentThread().getName());
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
