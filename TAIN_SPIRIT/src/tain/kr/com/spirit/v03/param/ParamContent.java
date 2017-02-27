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
package tain.kr.com.spirit.v03.param;

import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : ParamContent.java
 *   -. Package    : tain.kr.com.spirit.v03.param
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 2. 27. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public final class ParamContent extends AbsParam {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(ParamContent.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	private ParamContent() {
		
		super();
		
		if (!flag)
			log.debug(">>>>> in class " + this.getClass().getSimpleName());
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public synchronized String getString(String key) {
		
		String value;
		
		value = getStringFromSystem(key);
		if (value != null)
			return value;
		
		value = getStringFromResourceBundle(key);
		if (value != null)
			return value;
		
		return null;
	}
	
	public String getString(String key, String defaultValue) {
		
		String value = getString(key);
		if (value == null)
			return defaultValue;
		
		return value;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private static ParamContent instance = null;
	
	public static final synchronized ParamContent getInstance() {
		
		if (instance == null) {
			instance = new ParamContent();
		}
		
		return instance;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * static test method
	 */
	private static void test01(String[] args) throws Exception {

		if (flag) {
			/*
			 * test begin
			 */
			String strKeyVersion = "tain.kr.com.spirit.version";
			String strKeyDesc = "tain.kr.com.spirit.desc";
			String strKeyIp = "tain.kr.com.spirit.ip";
			
			System.out.printf("[%s] = [%s]\n", strKeyVersion, ParamContent.getInstance().getString(strKeyVersion));
			System.out.printf("[%s] = [%s]\n", strKeyDesc,  ParamContent.getInstance().getString(strKeyDesc));
			System.out.printf("[%s] = [%s]\n", strKeyIp,  ParamContent.getInstance().getString(strKeyIp, "192.168.0.11"));
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
