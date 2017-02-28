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
package tain.kr.com.spirit.v04.main;

import java.net.ServerSocket;

import org.apache.log4j.Logger;

import tain.kr.com.spirit.v04.joint.ThrJointServer;
import tain.kr.com.spirit.v04.loop.LoopSleep;
import tain.kr.com.spirit.v04.param.ParamContent;
import tain.kr.com.spirit.v04.util.Utils;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : MainControlServer.java
 *   -. Package    : tain.kr.com.spirit.v03.main
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 2. 27. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public final class MainControlServer {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(MainControlServer.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private static final String KEY_JOINT_LISTEN_PORT = "tain.kr.com.spirit.joint.listen.port";
	
	private String jointListenPort;
	
	private ServerSocket jointServerSocket;
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public MainControlServer() throws Exception{
		
		/*
		 * JOINT ServerSocket
		 */
		this.jointListenPort = ParamContent.getInstance().getString(KEY_JOINT_LISTEN_PORT, "20025");
		this.jointServerSocket = new ServerSocket(Integer.parseInt(this.jointListenPort));
		
		if (flag) log.debug(String.format(">> ParamContent [%s] = [%s]", KEY_JOINT_LISTEN_PORT, this.jointListenPort));
		if (flag) log.debug(String.format(">> joint listening port = %s..", this.jointListenPort));

		if (this.jointServerSocket == null)
			throw new Exception("the value of jointServerSocket is null pointer...");
		
		if (!flag) log.debug(">>>>> in class " + this.getClass().getSimpleName());
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public void execute() throws Exception {
		
		if (flag) {
			/*
			 * begin thread
			 */
			while (true) {
				/*
				 * loop of joint server thread
				 */
				if (flag) {
					/*
					 * joint server thread
					 */
					try {
						Thread thread = new ThrJointServer(this.jointServerSocket);
						thread.start();
						thread.join();
					} catch (Exception e) {
						if (flag) System.out.println(e + " - " + Utils.getInstance().getDateTime());
						//e.printStackTrace();
					}
				}
				
				if (flag) {
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
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * static test method
	 */
	private static void test01(String[] args) throws Exception {

		if (flag) ParamContent.getInstance().printInformation();

		if (flag)
			new MainControlServer().execute();
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
