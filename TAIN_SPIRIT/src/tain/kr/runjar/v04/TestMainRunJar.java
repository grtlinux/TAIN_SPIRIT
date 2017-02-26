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

import java.util.Properties;
import java.util.ResourceBundle;
import java.util.Set;

import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : TestMainRunJar.java
 *   -. Package    : tain.kr.runjar.v03
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 2. 27. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class TestMainRunJar {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(TestMainRunJar.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private static final String KEY_SPIRIT_VERSION = "tain.kr.com.spirit.version";
	private static final String KEY_SPIRIT_DESC = "tain.kr.com.spirit.desc";

	private String version;
	private String desc;
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public TestMainRunJar() {
		
		if (flag) {
			/*
			 * ResourceBundle
			 */
			if (flag) System.out.printf("\nResourceBundle\n");

			ResourceBundle rb = ResourceBundle.getBundle(this.getClass().getName().replace('.', '/'));
			
			this.version = rb.getString(KEY_SPIRIT_VERSION);
			this.desc = rb.getString(KEY_SPIRIT_DESC);
			
			if (flag) log.debug(String.format("ResourceBundle => [version=%s] [desc=%s]", this.version, this.desc));
		}

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
