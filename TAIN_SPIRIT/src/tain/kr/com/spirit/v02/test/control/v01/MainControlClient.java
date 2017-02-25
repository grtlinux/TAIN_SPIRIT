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

import org.apache.log4j.Logger;

import tain.kr.com.spirit.v02.loop.LoopSleep;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : MainControlClient.java
 *   -. Package    : tain.kr.com.spirit.v02.test.control.v01
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 2. 25. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public final class MainControlClient {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(MainControlClient.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public MainControlClient() {
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
			new MainControlClient();

		if (flag) {
			/*
			 * begin and retry connection
			 */
			for (int i=0; i < 100; i++) {
				
				if (flag) {
					/*
					 * client thread
					 */
					Thread thread = new ThrControlClient();
					thread.start();
					thread.join();
				}
				
				if (flag) {
					/*
					 * sleep
					 */
					LoopSleep.sleep(10 * 1000);
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
