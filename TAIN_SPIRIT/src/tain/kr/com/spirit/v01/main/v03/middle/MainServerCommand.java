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
package tain.kr.com.spirit.v01.main.v03.middle;

import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

import tain.kr.com.spirit.v01.joint.ThrJoint;
import tain.kr.com.spirit.v01.queue.QueueContent;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : MainServer.java
 *   -. Package    : tain.kr.com.spirit.v01.main.v03.middle
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 2. 23. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public final class MainServerCommand {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(MainServerCommand.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public MainServerCommand() {
		if (flag)
			log.debug(">>>>> in class " + this.getClass().getSimpleName());
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
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private static final String PORT = "20025";
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * static test method
	 */
	private static void test01(String[] args) throws Exception {

		if (flag)
			new MainServerCommand();

		if (flag) {
			/*
			 * begin
			 */
			@SuppressWarnings("resource")
			ServerSocket serverSocket = new ServerSocket(Integer.parseInt(PORT));

			/*
			 * command queue
			 */
			QueueContent queue = new QueueContent();
			
			if (flag) {
				/*
				 * command thread
				 */
				Socket socket = serverSocket.accept();

				Thread thread = new ThrServerCommand(socket, queue);
				thread.start();
			}

			while (true) {
				/*
				 * job processing
				 */
				
				Socket socket1 = null;
				Socket socket2 = null;
				
				if (flag) {
					/*
					 * 1st connection
					 */
					socket1 = serverSocket.accept();
				}
				
				if (flag) {
					/*
					 * send signal
					 */
					queue.put(String.format("CONNECT"));
				}
				
				if (flag) {
					/*
					 * 2nd connection
					 */
					socket2 = serverSocket.accept();
				}

				if (flag) {
					/*
					 * joint thread
					 */
					ThrJoint joint = new ThrJoint();
					
					joint.setSocket1(socket1);
					joint.setSocket2(socket2);
					
					joint.start();
					
					joint.join();
				}
			}
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
