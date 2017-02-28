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
package tain.kr.runjar.v04;

import java.lang.reflect.Method;
import java.util.MissingResourceException;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : TestMainRunJar.java
 *   -. Package    : tain.kr.runjar.v04
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 2. 27. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public final class TestMainRunJar {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(TestMainRunJar.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private final Properties prop;
	private final ResourceBundle resourceBundle;
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public TestMainRunJar() {
		
		this.prop = System.getProperties();
		this.resourceBundle = ResourceBundle.getBundle(this.getClass().getName().replace('.', '/'));

		printInformation();

		if (flag)
			log.debug(">>>>> in class " + this.getClass().getSimpleName());
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
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
	
	private void printInformation() {
		
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
	
	private String getString(String strKey) {
		
		String strValue = null;
		
		strValue = this.prop.getProperty(strKey);
		if (strValue != null)
			return strValue;
		
		try {
			strValue = this.resourceBundle.getString(strKey);
		} catch (MissingResourceException e) {}
		
		return strValue;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	private static final String KEY_CLASS = "class";

	private String strType;
	private String strClass;
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	public void execute() throws Exception {
		
		this.strType = getString(KEY_CLASS);
		if (this.strType == null) {
			throw new Exception("ERROR: there's no class name.. -Dclass=[SpiritServer/SpiritClient/TestServer/TestClient].");
		}
		
		this.strClass = getString(this.strType);
		
		if (this.strClass == null) {
			throw new Exception("ERROR: there's wrong class name.. -Dclass=[SpiritServer/SpiritClient/TestServer/TestClient].(strType=" + this.strType + ")");
		}
		
		runSpirit(this.strClass);
	}
	
	private void runSpirit(String strClass) throws Exception {
		
		if (flag) {
			if (flag) System.out.printf("class=[%s]\n", strClass);
			
			Class<?> cls = Class.forName(strClass);
			
			Method main = cls.getDeclaredMethod("main", new Class[] { String[].class });
			
			main.invoke(null, (Object) new String[] { "Hello", "World!!!" });
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
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * static test method
	 */
	private static void test01(String[] args) throws Exception {

		if (flag)
			new TestMainRunJar();
		
		if (flag) {
			
			if (flag) {
				/*
				 * System.getProperties
				 */
				if (flag) System.out.printf("\n########## System.getProperties()\n");
				
				Properties prop = System.getProperties();
				
				Set<String> setKeys = prop.stringPropertyNames();
				String[] strKeys = setKeys.toArray(new String[setKeys.size()]);
				
				for (String strKey : strKeys) {
					String strVal = prop.getProperty(strKey);
					
					if (flag) System.out.printf("[%s] -> [%s]\n", strKey, strVal);
				}
			}
			
			if (flag) {
				/*
				 * some elements of System.getProperties()
				 */
				if (flag) System.out.printf("\n########## some elements of System.getProperties()\n");
				
				Properties prop = System.getProperties();
				
				String strVal = prop.getProperty("tain.kr.com.ip", "192.168.0.11");
				
				if (flag) System.out.printf("IP = [%s]\n", strVal);
			}
			
			if (flag) {
				/*
				 * ResourceBundle resources/resources
				 */
				if (flag) System.out.printf("\n########## ResourceBundle.getBundle('resources/resources')\n");

				ResourceBundle rb = ResourceBundle.getBundle("resources/resources");
				
				String strKey = "tain.kr.com.spirit.version";
				String strVal = rb.getString(strKey);
				
				if (flag) log.debug(String.format("ResourceBundle.getBundle('resources/resources') => [%s] = [%s]", strKey, strVal));
			}
		}

		if (flag) {
			/*
			 * Running arguments
			 */
			if (flag) System.out.printf("\n########## Running arguments\n");

			for (int i=0; i < args.length; i++) {
				if (flag) System.out.println(String.format("(%d) [%s]", i, args[i]));
			}
		}
	}

	/*
	 * static test method
	 */
	private static void test02(String[] args) throws Exception {
		
		if (flag) new TestMainRunJar().execute();
	}
	
	/*
	 * main method
	 */
	public static void main(String[] args) throws Exception {

		if (flag)
			log.debug(">>>>> " + new Object() {
			}.getClass().getEnclosingClass().getName());

		if (!flag) test01(args);
		
		if (flag) test02(args);
	}
}
