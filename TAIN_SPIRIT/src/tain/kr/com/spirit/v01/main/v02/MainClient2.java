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
package tain.kr.com.spirit.v01.main.v02;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : MainClient2.java
 *   -. Package    : tain.kr.com.spirit.v01.main.v02
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 2. 23. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class MainClient2 {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(MainClient2.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public MainClient2() {
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

	private static final String HOST = "127.0.0.1";
	private static final String PORT = "20025";

	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * static test method
	 */
	private static void test01(String[] args) throws Exception {

		if (flag)
			new MainClient2();

		if (flag) {
			/*
			 * connection and open
			 */
			Socket socket = new Socket(HOST, Integer.parseInt(PORT));
			DataInputStream dis = new DataInputStream(socket.getInputStream());
			DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
			
			try {
				/*
				 * processing
				 */
				for (int i=0; i < 10; i++) {
					/*
					 * recv
					 */
					byte[] bytRecv = new byte[1024];
					int nRecv = 0;
					
					nRecv = dis.read(bytRecv);
					
					String strRecv = new String(bytRecv, 0, nRecv, Charset.forName("euc-kr"));
					
					if (flag) log.debug(String.format("CLIENT-2 RECV (%3d) [%s]", nRecv, strRecv));
					
					/*
					 * send
					 */
					String strSend = String.format("client1 sends data to client2....(%3d)", i);
					byte[] bytSend = strSend.getBytes(Charset.forName("euc-kr"));
					
					dos.write(bytSend, 0, bytSend.length);
					
					if (flag) log.debug(String.format("CLIENT-2 SEND (%3d) [%s]", bytSend.length, strSend));
				}
				
				if (flag) log.debug(String.format("CLIENT2 : END"));
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				/*
				 * close
				 */
				if (dos != null) try { dos.close(); } catch (IOException e) {}
				if (dis != null) try { dis.close(); } catch (IOException e) {}
				if (socket != null) try { socket.close(); } catch (IOException e) {}
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
