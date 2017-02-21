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
package tain.kr.com.spirit.v01.test.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : MainTestServer.java
 *   -. Package    : tain.kr.com.spirit.v01.test.server
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 2. 21. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public class MainTestServer {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(MainTestServer.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public MainTestServer() {
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
	
	private static final String PORT = "20025";
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * static test method
	 */
	@SuppressWarnings("resource")
	private static void test01(String[] args) throws Exception {

		if (flag)
			new MainTestServer();

		if (flag) {
			/*
			 * variable
			 */
			ServerSocket serverSocket;
			Socket socket;
			DataInputStream dis;
			DataOutputStream dos;
			
			serverSocket = new ServerSocket(Integer.parseInt(PORT));
			
			while (true) {
				
				socket = null;
				
				try {
					/*
					 * listen and connection
					 */
					socket = serverSocket.accept();
					dis = new DataInputStream(socket.getInputStream());
					dos = new DataOutputStream(socket.getOutputStream());
				} catch (Exception e) {
					e.printStackTrace();
					break;
				}
				
				try {
					/*
					 * recv data from client
					 */
					byte[] bytRecv = new byte[1024];
					int nRecv = 0;
					
					nRecv = dis.read(bytRecv);
					
					String strRecv = new String(bytRecv, 0, nRecv, Charset.forName("euc-kr"));
					
					if (flag) System.out.printf("RECV (%3d) [%s].\n", nRecv, strRecv);
					
					/*
					 * send data to client
					 */
					String strSend = "server sends data to client....";
					byte[] bytSend = strSend. getBytes(Charset.forName("euc-kr"));
					
					dos.write(bytSend, 0, bytSend.length);
					
					if (flag) System.out.printf("SEND (%3d) [%s].\n", bytSend.length, strSend);

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
