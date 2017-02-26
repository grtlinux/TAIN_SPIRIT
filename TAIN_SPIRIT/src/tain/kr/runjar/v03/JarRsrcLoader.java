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
package tain.kr.runjar.v03;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Map;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;

import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : JarRsrcLoader.java
 *   -. Package    : tain.kr.runjar.v03
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 2. 26. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class JarRsrcLoader {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(JarRsrcLoader.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public JarRsrcLoader() {
		if (flag)
			log.debug(">>>>> in class " + this.getClass().getSimpleName());
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private static class ManifestInfo {
		String rsrcMainClass;
		String[] rsrcClassPath;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private static String[] splitSpaces(String line) throws Exception {
		
		if (line == null)
			return null;
		
		if (flag) {
			List<String> result = new ArrayList<String>();
			
			String[] arr = line.split("\\s");
			
			for (String str : arr) {
				if (!"".equals(str))
					result.add(str);
			}
			
			return (String[]) result.toArray(new String[result.size()]);
		}
		
		return null;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private static ManifestInfo getManifestInfo() throws Exception {
		
		if (flag) System.out.printf("\t 1) JarFile.MANIFEST_NAME = %s\n\n", JarFile.MANIFEST_NAME);
		
		Enumeration<URL> urls = Thread.currentThread().getContextClassLoader().getResources(JarFile.MANIFEST_NAME);
		while (urls.hasMoreElements()) {
			URL url = (URL) urls.nextElement();
			if (flag) System.out.printf("\t 2) %s\n", url);
			
			InputStream is = url.openStream();
			if (is != null) {
				Manifest manifest = new Manifest(is);
				Attributes attributes = manifest.getMainAttributes();
				
				if (flag) {
					for (Map.Entry<Object, Object> entry : attributes.entrySet()) {
						String key = String.valueOf(entry.getKey());
						String val = String.valueOf(entry.getValue());
						if (flag) System.out.printf("\t\t 3) [%s] = [%s]\n", key, val);
					}
					if (flag) System.out.println();
				}
				
				ManifestInfo manifestInfo = new ManifestInfo();
				manifestInfo.rsrcMainClass = attributes.getValue(JIJConstants.REDIRECTED_MAIN_CLASS_MANIFEST_NAME); // "Rsrc-Main-Class"
				
				String rsrcClassPath = attributes.getValue(JIJConstants.REDIRECTED_CLASS_PATH_MANIFEST_NAME);  // "Rsrc-Class-Path"
				if (rsrcClassPath == null)
					rsrcClassPath = JIJConstants.DEFAULT_REDIRECTED_CLASSPATH;                      // ""
				manifestInfo.rsrcClassPath = splitSpaces(rsrcClassPath);
				
				if (flag && (manifestInfo.rsrcMainClass != null) && !manifestInfo.rsrcMainClass.trim().equals(""))
					return manifestInfo;
			}
			
			if (!flag) break;
		}
		
		if (flag) System.err.printf("Missing attributes for JarRsrcLoader in Manifest (%s, %s)\n"
				, JIJConstants.REDIRECTED_MAIN_CLASS_MANIFEST_NAME        // "Rsrc-Main-Class"
				, JIJConstants.REDIRECTED_CLASS_PATH_MANIFEST_NAME);      // "Rsrc-Class-Path"
		
		return null;
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
			new JarRsrcLoader();

		if (flag) {
			/*
			 * begin
			 */
			ManifestInfo manifestInfo = getManifestInfo();
			if (flag) System.out.printf("\t 4) [%s] = %s\n", manifestInfo.rsrcMainClass, new ArrayList<String>(Arrays.asList(manifestInfo.rsrcClassPath)));
			
			ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
			URL.setURLStreamHandlerFactory(new RsrcURLStreamHandlerFactory(classLoader));
			
			URL[] rsrcUrls = new URL[manifestInfo.rsrcClassPath.length];
			
			for (int i=0; i < manifestInfo.rsrcClassPath.length; i++) {
				String rsrcPath = manifestInfo.rsrcClassPath[i];
				
				if (rsrcPath.endsWith(JIJConstants.PATH_SEPARATOR))     //   "/"
					rsrcUrls[i] = new URL(JIJConstants.INTERNAL_URL_PROTOCOL_WITH_COLON + rsrcPath);   // "rsrc:"
				else
					rsrcUrls[i] = new URL(JIJConstants.JAR_INTERNAL_URL_PROTOCOL_WITH_COLON + rsrcPath + JIJConstants.JAR_INTERNAL_SEPARATOR);   //  "jar:rsrc:"   "!/"
			}
			
			ClassLoader jceClassLoader = new URLClassLoader(rsrcUrls, null);
			Thread.currentThread().setContextClassLoader(jceClassLoader);
			
			Class<?> cls = Class.forName(manifestInfo.rsrcMainClass, true, jceClassLoader);
			Method main = cls.getMethod(JIJConstants.MAIN_METHOD_NAME, new Class[] { args.getClass() });
			main.invoke((Object) null, new Object[] { args });
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
