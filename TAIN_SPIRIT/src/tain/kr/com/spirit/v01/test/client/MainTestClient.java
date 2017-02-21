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
package tain.kr.com.spirit.v01.test.client;

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
 *   -. FileName   : MainTestClient.java
 *   -. Package    : tain.kr.com.spirit.v01.test.client
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 2. 21. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class MainTestClient {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(MainTestClient.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public MainTestClient() {
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
			new MainTestClient();

		if (flag) {
			/*
			 * variable
			 */
			Socket socket;
			DataInputStream dis;
			DataOutputStream dos;
			
			try {
				/*
				 * connect
				 */
				socket = new Socket(HOST, Integer.parseInt(PORT));
				dis = new DataInputStream(socket.getInputStream());
				dos = new DataOutputStream(socket.getOutputStream());
			} catch (Exception e) {
				e.printStackTrace();
				throw e;
			}
			
			try {
				/*
				 * send data to server
				 */
				String strSend = "client sends data to server..";
				byte[] bytSend = strSend.getBytes(Charset.forName("euc-kr"));
				
				dos.write(bytSend, 0, bytSend.length);
				
				if (flag) System.out.printf("SEND (%3d) [%s].\n", bytSend.length, strSend);
				
				/*
				 * recv data from server
				 */
				byte[] bytRecv = new byte[1024];
				int nRecv = 0;
				
				nRecv = dis.read(bytRecv);
				
				String strRecv = new String(bytRecv, 0, nRecv, Charset.forName("euc-kr"));
				
				if (flag) System.out.printf("RECV (%3d) [%s].\n", nRecv, strRecv);
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				/*
				 * close
				 */
				try { dos.close(); } catch (IOException e) {}
				try { dis.close(); } catch (IOException e) {}
				try { socket.close(); } catch (IOException e) {}
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
