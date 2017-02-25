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
package tain.kr.com.spirit.v01.test.bridge.v01;

import java.io.IOException;
import java.net.Socket;

import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : ThrServer.java
 *   -. Package    : tain.kr.com.spirit.v01.test.bridge.v01
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 2. 25. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public final class ThrServer extends Thread {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(ThrServer.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private static final String CONNECT_HOST = "192.168.0.15";
	private static final String CONNECT_PORT = "3389";
	
	private int idx;
	private Socket socket1;
	private Socket socket2;
	
	public boolean flagStopThread = false;
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public ThrServer(int idx, Socket socket1) throws Exception {
		
		super(String.format("JOINT_%03d", idx));
		
		this.idx = idx;
		this.socket1 = socket1;
		this.socket2 = new Socket(CONNECT_HOST, Integer.parseInt(CONNECT_PORT));
		
		if (this.socket1 == null || this.socket2 == null) {
			throw new Exception("socket variables is null(tain.co.kr)");
		}
		
		if (flag)
			log.debug(">>>>> in class " + this.getClass().getSimpleName());
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public void run() {
		
		ThrRecvSend thread1 = null;
		ThrRecvSend thread2 = null;
		
		if (flag) {
			/*
			 * create thread
			 */
			try {
				thread1 = new ThrRecvSend(String.format("JOINT_%03d_1", this.idx), this
						, this.socket1.getInputStream(), this.socket2.getOutputStream());
				
				thread2 = new ThrRecvSend(String.format("JOINT_%03d_2", this.idx), this
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
			thread1.start();
			thread2.start();
		}
		
		if (flag) {
			/*
			 * join thread
			 */
			try {
				thread1.join();
				thread2.join();
			} catch (InterruptedException e) {}
		}
		
		if (flag) {
			/*
			 * close
			 */
			if (this.socket1 != null) try { this.socket1.close(); } catch (IOException e) {}
			if (this.socket2 != null) try { this.socket2.close(); } catch (IOException e) {}
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
