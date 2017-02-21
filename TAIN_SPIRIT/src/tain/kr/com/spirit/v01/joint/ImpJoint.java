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
package tain.kr.com.spirit.v01.joint;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;


/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : ImpJoing.java
 *   -. Package    : tain.kr.com.spirit.v01.joint
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 2. 21. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public interface ImpJoint {

	public abstract void start();
	
	public abstract void setSocket1(Socket socket1) throws IOException;
	public abstract void setSocket2(Socket socket2) throws IOException;
	
	public abstract void setStream1(InputStream is1, OutputStream os1);
	public abstract void setStream2(InputStream is2, OutputStream os2);
}
