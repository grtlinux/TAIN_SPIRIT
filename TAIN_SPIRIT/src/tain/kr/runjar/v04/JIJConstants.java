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


/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : JIJConstants.java
 *   -. Package    : tain.kr.runjar.v03
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 2. 26. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
final class JIJConstants {

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	static final String REDIRECTED_MAIN_CLASS_MANIFEST_NAME  = "Rsrc-Main-Class";
	static final String REDIRECTED_CLASS_PATH_MANIFEST_NAME  = "Rsrc-Class-Path";
	static final String DEFAULT_REDIRECTED_CLASSPATH         = "";
	
	static final String UTF8_ENCODING                        = "UTF-8";
	
	static final String PATH_SEPARATOR                       = "/";
	static final String CURRENT_DIR                          = "./";

	static final String INTERNAL_URL_PROTOCOL                = "rsrc";
	static final String INTERNAL_URL_PROTOCOL_WITH_COLON     = "rsrc:";
	
	static final String JAR_INTERNAL_URL_PROTOCOL_WITH_COLON = "jar:rsrc:";
	static final String JAR_INTERNAL_SEPARATOR               = "!/";
	
	static final String MAIN_METHOD_NAME                     = "main";
	
	///////////////////////////////////////////////////////////////////////////////////////////////
}
