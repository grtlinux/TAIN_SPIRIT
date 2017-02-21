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

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;

import tain.kr.com.spirit.v01.controler.ThrControler;

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
			 * 1st testing
			 */
			final ThrControler thrControler1 = new ThrControler(String.format("THR_%04d", 1));
			final ThrControler thrControler2 = new ThrControler(String.format("THR_%04d", 2));
			
			if (flag) {
				/*
				 * set queue
				 */
				thrControler1.setRecvQueue(thrControler2.getSendQueue());  // 1_R (2_S) 
				thrControler2.setRecvQueue(thrControler1.getSendQueue());  // 2_R (1_S) 
			}
			
			if (flag) {
				/*
				 * thrControler1 set IO stream
				 */
				String strData = ""
						+ "ABCDEFGHIJ0000000000"
						+ "ABCDEFGHIJ0000000001"
						+ "ABCDEFGHIJ0000000002"
						+ "ABCDEFGHIJ0000000003"
						+ "ABCDEFGHIJ0000000004"
						+ "ABCDEFGHIJ0000000005"
						+ "ABCDEFGHIJ0000000006"
						+ "ABCDEFGHIJ0000000007"
						+ "ABCDEFGHIJ0000000008"
						+ "ABCDEFGHIJ0000000009"
						+ "ABCDEFGHIJ0000000010"
						+ "ABCDEFGHIJ0000000011"
						+ "ABCDEFGHIJ0000000012"
						+ "ABCDEFGHIJ0000000013"
						+ "ABCDEFGHIJ0000000014"
						+ "ABCDEFGHIJ0000000015"
						+ "ABCDEFGHIJ0000000016"
						+ "ABCDEFGHIJ0000000017"
						+ "ABCDEFGHIJ0000000018"
						+ "ABCDEFGHIJ0000000019"
						+ "ABCDEFGHIJ0000000020";
				byte[] bytData = strData.getBytes(Charset.forName("euc-kr"));
				
				DataInputStream dis1 = new DataInputStream(new ByteArrayInputStream(bytData));
				DataOutputStream dos1 = new DataOutputStream(new ByteArrayOutputStream(1024));
				
				thrControler1.setDataInputStream(dis1);
				thrControler1.setDataOutputStream(dos1);
			}
			
			if (flag) {
				/*
				 * thrControler2 set IO stream
				 */
				String strData = ""
						+ "abcdefghij-------000"
						+ "abcdefghij-------001"
						+ "abcdefghij-------019"
						+ "abcdefghij-------020";
				byte[] bytData = strData.getBytes(Charset.forName("euc-kr"));
				
				DataInputStream dis2 = new DataInputStream(new ByteArrayInputStream(bytData));
				DataOutputStream dos2 = new DataOutputStream(new ByteArrayOutputStream(1024));

				thrControler2.setDataInputStream(dis2);
				thrControler2.setDataOutputStream(dos2);
			}
			
			if (flag) {
				/*
				 * start thrControler1, thrControler2
				 */
				thrControler1.start();
				thrControler2.start();
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
