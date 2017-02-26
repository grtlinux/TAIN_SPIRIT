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

import java.net.URLStreamHandler;
import java.net.URLStreamHandlerFactory;

import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : RsrcURLStreamHandlerFactory.java
 *   -. Package    : tain.kr.runjar.v03
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 2. 26. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class RsrcURLStreamHandlerFactory implements URLStreamHandlerFactory {

	private static boolean flag = true;

	private static final Logger log = Logger
			.getLogger(RsrcURLStreamHandlerFactory.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private ClassLoader classLoader = null;
	private URLStreamHandlerFactory factory = null;
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public RsrcURLStreamHandlerFactory(ClassLoader classLoader) {
		
		this.classLoader = classLoader;
		
		if (flag)
			log.debug(">>>>> in class " + this.getClass().getSimpleName());
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public void setURLStreamHandlerFactory(URLStreamHandlerFactory factory) {
		this.factory = factory;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	/* (non-Javadoc)
	 * @see java.net.URLStreamHandlerFactory#createURLStreamHandler(java.lang.String)
	 */
	@Override
	public URLStreamHandler createURLStreamHandler(String protocol) {
		
		if (flag) log.debug(String.format(">>>>> protocol = %s", protocol));
		
		if (JIJConstants.INTERNAL_URL_PROTOCOL.equals(protocol))    // "rsrc"
			return new RsrcURLStreamHandler(classLoader);
		
		if (factory != null)
			return factory.createURLStreamHandler(protocol);
		
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
