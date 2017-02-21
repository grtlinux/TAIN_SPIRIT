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
package tain.kr.com.spirit.v01.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;

import tain.kr.com.spirit.v01.joint.ThrJoint;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : MainTest01.java
 *   -. Package    : tain.kr.com.spirit.v01.test
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 2. 21. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class MainTest01 {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(MainTest01.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public MainTest01() {
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
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * static test method
	 */
	private static void test01(String[] args) throws Exception {

		if (flag)
			new MainTest01();

		if (flag) {
			/*
			 * Joint
			 */
			final ThrJoint joint = new ThrJoint();
			
			if (flag) {
				/*
				 * 1_R -> 2_S
				 */
				new Thread("SENDER_1") {
					@Override
					public void run() {
						
						try {
							DataOutputStream dos = joint.getOutDataOutputStream1();
							
							for (int i=0; i < 10; i++) {
								String strData = String.format("ABCDEFGHIJ%010d", i);
								byte[] bytData = strData.getBytes(Charset.forName("euc-kr"));
								
								dos.write(bytData);
								
								if (flag) System.out.printf("SEND (%3d) [%s].\n", bytData.length, strData);
							}
							
							dos.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}.start();
				
				new Thread("RECVER_2") {
					public void run() {
						
						try {
							DataInputStream dis = joint.getOutDataInputStream2();
							
							byte[] bytRead = new byte[1024];
							int nRead = 0;
							
							while ((nRead = dis.read(bytRead)) != -1) {
								
								if (flag) System.out.printf("RECV (%3d) [%s].\n"
										, nRead, new String(bytRead, 0, nRead, Charset.forName("euc-kr")));
							}
							
							dis.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}.start();
			}
			
			if (!flag) {
				/*
				 * 2_R -> 1_S
				 */
				new Thread("SENDER_2") {
					@Override
					public void run() {
						
						try {
							DataOutputStream dos = joint.getOutDataOutputStream2();
							
							for (int i=0; i < 10; i++) {
								String strData = String.format("abcdefghij%010d", i);
								byte[] bytData = strData.getBytes(Charset.forName("euc-kr"));
								
								dos.write(bytData);
								
								if (flag) System.out.printf("SEND (%3d) [%s].\n", bytData.length, strData);
							}
							
							dos.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}.start();
				
				new Thread("RECVER_1") {
					public void run() {
						
						try {
							DataInputStream dis = joint.getOutDataInputStream1();
							
							byte[] bytRead = new byte[1024];
							int nRead = 0;
							
							while ((nRead = dis.read(bytRead)) != -1) {
								
								if (flag) System.out.printf("RECV (%3d) [%s].\n"
										, nRead, new String(bytRead, 0, nRead, Charset.forName("euc-kr")));
							}
							
							dis.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}.start();
			}

			joint.start();
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
