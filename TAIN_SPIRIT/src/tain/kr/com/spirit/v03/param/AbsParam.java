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

import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;

import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : AbsParam.java
 *   -. Package    : tain.kr.com.spirit.v03.param
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 2. 27. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public abstract class AbsParam {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(AbsParam.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	protected static final String FILE_NAME_RESOURCES = "resources/resources";

	protected final Properties prop;
	protected final ResourceBundle resourceBundle;
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	protected AbsParam() {
		
		this.prop = System.getProperties();
		this.resourceBundle = ResourceBundle.getBundle(FILE_NAME_RESOURCES);
		
		if (!flag)
			log.debug(">>>>> in class " + this.getClass().getSimpleName());
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	protected String getStringFromSystem(String key) {
		return this.prop.getProperty(key);
	}
	
	protected String getStringFromResourceBundle(String key) {
		
		String strValue = null;
		
		try {
			strValue = this.resourceBundle.getString(key);
		} catch (MissingResourceException e) {}
		
		return strValue;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private static final String KEY_INFORMATION = "tain.kr.com.spirit.information";
	private static final String KEY_PROJECT = "tain.kr.com.spirit.project";
	private static final String KEY_COMPANY = "tain.kr.com.spirit.company";
	private static final String KEY_AUTHOR = "tain.kr.com.spirit.author";
	private static final String KEY_VERSION = "tain.kr.com.spirit.version";
	private static final String KEY_SITE = "tain.kr.com.spirit.site";
	private static final String KEY_DESC = "tain.kr.com.spirit.desc";
	private static final String KEY_COPYRIGHT = "tain.kr.com.spirit.copyright";
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public void printInformation() {
		
		if (flag) {
			if (flag) System.out.printf("\t----------------------------------------------------\n");
			if (flag) System.out.printf("\t[%s] = [%s]\n", KEY_INFORMATION, this.resourceBundle.getString(KEY_INFORMATION));
			if (flag) System.out.printf("\t[%s] = [%s]\n", KEY_PROJECT, this.resourceBundle.getString(KEY_PROJECT));
			if (flag) System.out.printf("\t[%s] = [%s]\n", KEY_COMPANY, this.resourceBundle.getString(KEY_COMPANY));
			if (flag) System.out.printf("\t[%s] = [%s]\n", KEY_AUTHOR, this.resourceBundle.getString(KEY_AUTHOR));
			if (flag) System.out.printf("\t[%s] = [%s]\n", KEY_VERSION, this.resourceBundle.getString(KEY_VERSION));
			if (flag) System.out.printf("\t[%s] = [%s]\n", KEY_SITE, this.resourceBundle.getString(KEY_SITE));
			if (flag) System.out.printf("\t[%s] = [%s]\n", KEY_DESC, this.resourceBundle.getString(KEY_DESC));
			if (flag) System.out.printf("\t[%s] = [%s]\n", KEY_COPYRIGHT, this.resourceBundle.getString(KEY_COPYRIGHT));
			if (flag) System.out.printf("\t----------------------------------------------------\n");
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
