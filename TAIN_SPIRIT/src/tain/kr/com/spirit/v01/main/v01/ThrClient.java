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
package tain.kr.com.spirit.v01.main.v01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;

import org.apache.log4j.Logger;

import tain.kr.com.spirit.v01.joint.ThrJoint;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : ThrClient.java
 *   -. Package    : tain.kr.com.spirit.v01.main.v01
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 2. 22. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public final class ThrClient extends Thread {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(ThrClient.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private final Socket socket;
	
	private final ThrJoint joint;
	
	private final DataInputStream dis;
	private final DataOutputStream dos;
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public ThrClient(Socket socket) throws Exception {
		
		this.socket = socket;
		
		this.joint = new ThrJoint();
		
		this.joint.setSocket2(this.socket);
		
		this.dis = this.joint.getOutDataInputStream1();
		this.dos = this.joint.getOutDataOutputStream1();
		
		if (flag)
			log.debug(">>>>> in class " + this.getClass().getSimpleName());
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public void run() {
		
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
