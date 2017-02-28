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

import org.apache.log4j.Logger;

import tain.kr.com.spirit.v04.joint.ThrJointClient;
import tain.kr.com.spirit.v04.loop.LoopSleep;
import tain.kr.com.spirit.v04.param.ParamContent;
import tain.kr.com.spirit.v04.util.Utils;

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
	
	private static final String KEY_RETRY = "tain.kr.com.spirit.joint.retry";
	private static int timesRetry;
	
	private static LoopSleep loopSleep;
	
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
			timesRetry = Integer.parseInt(strRetry);
			loopSleep = new LoopSleep();
		}

		if (flag) {
			/*
			 * begin thread
			 */
			while (true) {
				/*
				 * loop of joint client thread
				 */
				if (flag) {
					/*
					 * client thread
					 */
					try {
						Thread thread = new ThrJointClient();
						thread.start();
						thread.join();
						loopSleep.reset();
					} catch (Exception e) {
						if (flag) System.out.println(e + " - " + Utils.getInstance().getDateTime());
						// e.printStackTrace();
					}
				}
				
				if (flag) {
					/*
					 * sleep, unit is minute
					 */
					loopSleep.sleep(timesRetry);
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
