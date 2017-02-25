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
package tain.kr.com.spirit.v02.test.control.v01;

import java.net.ServerSocket;

import org.apache.log4j.Logger;

import tain.kr.com.spirit.v02.loop.LoopSleep;
import tain.kr.com.spirit.v02.queue.QueueContent;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : MainControlServer.java
 *   -. Package    : tain.kr.com.spirit.v02.test.control.v01
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 2. 25. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public final class MainControlServer {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(MainControlServer.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private static final String CONTROL_LISTEN_PORT = "20025";
	private static final String JOINT_LISTEN_PORT = "13389";
	
	private QueueContent queue;
	
	private ServerSocket serverSocketControl;
	private ServerSocket serverSocketJoint;
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public MainControlServer() throws Exception {
		
		initialize();
		
		if (flag)
			log.debug(">>>>> in class " + this.getClass().getSimpleName());
	}

	private void initialize() throws Exception {
		
		if (flag) {
			/*
			 * initialize
			 */
			this.queue = new QueueContent();
			
			this.serverSocketControl = new ServerSocket(Integer.parseInt(CONTROL_LISTEN_PORT));
			if (flag) log.debug(String.format(">> control listening port = %s..", CONTROL_LISTEN_PORT));

			this.serverSocketJoint = new ServerSocket(Integer.parseInt(JOINT_LISTEN_PORT));
			if (flag) log.debug(String.format(">> joint listening port = %s..", JOINT_LISTEN_PORT));
		}
		
		if (flag) {
			/*
			 * validate
			 */
			if (this.queue == null) {
				throw new Exception("the value of queue is null pointer...");
			}
			
			if (this.serverSocketControl == null) {
				throw new Exception("the value of serverSocketControl is null pointer...");
			}

			if (this.serverSocketJoint == null) {
				throw new Exception("the value of serverSocketJoint is null pointer...");
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public QueueContent getQueue() {
		return this.queue;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public void execute() throws Exception {
		
		if (flag) {
			/*
			 * begin
			 */
			while (true) {
				if (flag) {
					/*
					 * control server thread
					 */
					Thread thread = new ThrControlServer(this, this.serverSocketControl);
					thread.start();
				}

				if (flag) {
					/*
					 * joint server thread
					 */
					Thread thread = new ThrJointServer(this, this.serverSocketJoint);
					thread.start();
					thread.join();
				}
				
				if (!flag) {
					/*
					 * sleep
					 */
					LoopSleep.sleep(1 * 500);
				}
			}
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

		if (flag)
			new MainControlServer().execute();;
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
