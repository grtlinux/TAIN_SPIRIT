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
import java.io.EOFException;
import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : MainTestPiped.java
 *   -. Package    : tain.kr.com.spirit.v01.test
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 2. 21. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class MainTestPiped {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(MainTestPiped.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public MainTestPiped() {
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
			new MainTestPiped();

		if (flag) {
			/*
			 * transfer Piped IO Stream to Data IO Stream
			 */
			
			final PipedInputStream pis = new PipedInputStream();
			final PipedOutputStream pos = new PipedOutputStream(pis);  // pos -> pis
			
			System.out.println("START.....");
			
			new Thread() {
				@Override
				public void run() {
					DataOutputStream dos = new DataOutputStream(pos);
					
					try {
						for (int i=1; i < 1000; i++) {
							dos.writeInt(i);
						}
					} catch (Exception e) {
						e.printStackTrace();
					} finally {
						try { dos.close(); } catch (IOException e) {}
					}
				}
			}.start();
			
			DataInputStream dis = new DataInputStream(pis);
			
			try {
				int val = 0;
				while (true) {
					val = dis.readInt();
					System.out.println("> " + val);
				}
			} catch (EOFException e) {
				// not print error message
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try { dis.close(); } catch (IOException e) {}
			}
			
			System.out.println("FINISH......");
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
