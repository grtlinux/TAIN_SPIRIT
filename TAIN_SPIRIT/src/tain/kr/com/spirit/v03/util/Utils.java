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
package tain.kr.com.spirit.v03.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : Utils.java
 *   -. Package    : tain.kr.com.spirit.v03.util
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 2. 27. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public final class Utils {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(Utils.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	private Utils() {
		if (!flag)
			log.debug(">>>>> in class " + this.getClass().getSimpleName());
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public final String[] listToArray(final List<String> strList) {
		return strList.toArray(new String[strList.size()]);
	}
	
	public final List<String> arrayToList(final String[] strArr) {
		return new ArrayList<String>(Arrays.asList(strArr));
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
	
	private static Utils instance = null;
	
	public static final synchronized Utils getInstance() {
		
		if (instance == null) {
			instance = new Utils();
		}
		
		return instance;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * static test method
	 */
	private static void test01(String[] args) throws Exception {

		if (flag)
			new Utils();

		if (flag) {
			/*
			 * test begin
			 */
			String[] strArr = { "first", "second", "third", "forth" };
			
			if (flag) {
				/*
				 * convert to list from array
				 */
				List<String> strList = Utils.getInstance().arrayToList(strArr);
				
				for (String str : strList) {
					System.out.println("> " + str);
				}
				System.out.println();
				
				System.out.println(">>>>> " + strList);
			}
			
			if (flag) {
				/*
				 * convert to array from list
				 */
				List<String> strList = Utils.getInstance().arrayToList(strArr);
				
				String[] arr = Utils.getInstance().listToArray(strList);
				
				for (String str : arr) {
					System.out.println("> " + str);
				}
				System.out.println();
			}
		}
	}

	/*
	 * main method
	 */
	public static void main(String[] args) throws Exception {

		if (flag)
			log.debug(">>>>> " + new Object(){}.getClass().getEnclosingClass().getName());

		if (flag) test01(args);
	}
}
