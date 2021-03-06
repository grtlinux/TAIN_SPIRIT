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
package tain.kr.com.spirit.v03.main;

import java.util.Date;

import org.apache.log4j.Logger;

import tain.kr.com.spirit.v03.control.ThrControlClient;
import tain.kr.com.spirit.v03.loop.LoopSleep;
import tain.kr.com.spirit.v03.param.ParamContent;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : MainControlClient.java
 *   -. Package    : tain.kr.com.spirit.v03.main
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 2. 27. {time}
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
	
	private static final String KEY_RETRY = "tain.kr.com.spirit.control.retry";
	private static int minRetry;
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * static test method
	 */
	private static void test01(String[] args) throws Exception {

		if (flag)
			new MainControlClient();

		if (flag) ParamContent.getInstance().printInformation();
		
		if (flag) {
			String strRetry = ParamContent.getInstance().getString(KEY_RETRY, "30");
			minRetry = Integer.parseInt(strRetry);
		}

		if (flag) {
			/*
			 * begin loop of client thread
			 */
			for (int i=0; ; i = ++i % 1000) {
				if (flag) {
					/*
					 * client thread
					 */
					try {
						Thread thread = new ThrControlClient();
						thread.start();
						thread.join();
					} catch (Exception e) {
						// e.printStackTrace();
						if (flag) System.out.println(e + " - " + new Date().toString());
					}
				}
				
				if (flag) {
					/*
					 * sleep
					 */
					LoopSleep.sleep(minRetry * 60 * 1000);
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
