/**
 * Copyright 2014, 2015, 2016 TAIN, Inc. all rights reserved.
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
 * Copyright 2014, 2015, 2016 TAIN, Inc.
 *
 */
package tain.kr.runjar.v03;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : RsrcURLStreamHandler.java
 *   -. Package    : tain.kr.com.test.runJar.v02
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2016. 4. 15. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class RsrcURLStreamHandler extends URLStreamHandler {

	private static boolean flag = true;

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private ClassLoader classLoader = null;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public RsrcURLStreamHandler(ClassLoader classLoader) {
		this.classLoader = classLoader;
	}
	
	protected URLConnection openConnection(URL url) throws IOException {
		return new RsrcURLConnection(url, this.classLoader);
	}
	
	protected void parseURL(URL url, String spec, int start, int limit) {
	
		if (!flag) System.out.println("[" + url + ", " + spec + ", " + start + ", " + limit + "]");
		
		String file;
		
		if (spec.startsWith(JIJConstants.INTERNAL_URL_PROTOCOL_WITH_COLON))
			file = spec.substring(5);
		else if (url.getFile().equals(JIJConstants.CURRENT_DIR))
			file = spec;
		else if (url.getFile().endsWith(JIJConstants.PATH_SEPARATOR))
			file = url.getFile() + spec;
		else
			file = spec;
		
		if (!flag) System.out.println("file = [" + file + "]");
		
		setURL(url, JIJConstants.INTERNAL_URL_PROTOCOL, "", -1, null, null, file, null, null);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////

}
