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
package tain.kr.com.spirit.v04.test.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;

import tain.kr.com.spirit.v03.loop.LoopSleep;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : ThrServer.java
 *   -. Package    : tain.kr.com.spirit.v03.test.server
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 2. 27. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public final class ThrServer extends Thread {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(ThrServer.class);

	///////////////////////////////////////////////////////////////////////////////////////////////

	private static final String THREAD_NAME = "TEST_SERVER";
	
	private final Socket socket;
	private final DataInputStream dis;
	private final DataOutputStream dos;

	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public ThrServer(ServerSocket serverSocket) throws Exception {
		
		super(THREAD_NAME);
		
		this.socket = serverSocket.accept();
		if (this.socket == null) {
			throw new IOException("ERROR: socket is null pointer..");
		}
		
		this.dis = new DataInputStream(this.socket.getInputStream());
		this.dos = new DataOutputStream(this.socket.getOutputStream());

		printInfo();
		
		if (flag)
			log.debug(">>>>> in class " + this.getClass().getSimpleName());
	}

	private void printInfo() {
		
		if (flag) System.out.printf("\t ParamContent [THREAD_NAME] = [%s]\n", THREAD_NAME);
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private static final String TYP_CHARSET = "euc-kr";
	private static final int SIZ_RECV = 1024;
	
	private String strSend;
	private byte[] bytSend;
	private int nSend;

	private String strRecv;
	private byte[] bytRecv = new byte[SIZ_RECV];
	private int nRecv;

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public void run() {
		
		if (flag) {
			/*
			 * start thread process for recv and send
			 */
			try {
				while (true) {
					/*
					 * for loop
					 */
					if (flag) {
						/*
						 * recv
						 */
						
						if (!recv()) {
							LoopSleep.sleep(1000);
							continue;
						}
						
						this.strRecv = new String(this.bytRecv, 0, this.nRecv, Charset.forName(TYP_CHARSET));
						
						if (flag) System.out.printf("%s [RECV] [%d:%s]\n", Thread.currentThread().getName(), this.nRecv, this.strRecv);
					}
					
					if (flag) {
						/*
						 * send
						 */
						this.strSend = "OK!! How are you doing these days?~~";
						this.bytSend = this.strSend.getBytes(Charset.forName(TYP_CHARSET));
						this.nSend = this.bytSend.length;
						
						send();
						
						if (flag) System.out.printf("%s [SEND] [%d:%s]\n", Thread.currentThread().getName(), this.nSend, this.strSend);
					}
					
					if (flag) {
						/*
						 * sleep
						 */
						LoopSleep.sleep(2 * 1000);
					}
				}  // for
				
			} catch (Exception e) {
				// e.printStackTrace();
				if (flag) System.out.println(e);
			} finally {
				if (flag) {
					/*
					 * close
					 */
					if (this.dos != null) try { this.dos.close(); } catch (IOException e) {}
					if (this.dis != null) try { this.dis.close(); } catch (IOException e) {}
					if (this.socket != null) try { this.socket.close(); } catch (IOException e) {}
				}
			}
		}
	}
	
	private boolean send() throws Exception {
		
		try {
			this.dos.write(this.bytSend, 0, this.nSend);
		} catch (IOException e) {
			throw e;
		}
		
		return true;
	}
	
	private boolean recv() throws Exception {
		
		try {
			this.nRecv = this.dis.read(this.bytRecv, 0, SIZ_RECV);
			if (this.nRecv < 0) {
				/*
				 * EOF
				 */
				if (flag) System.out.printf("%s [EOF] read data of EOF...\n", Thread.currentThread().getName());
				throw new Exception("read data of EOF. end of stream...");
			}
		} catch (SocketTimeoutException e) {
			// e.printStackTrace();
			if (flag) System.out.println(e);
			// throw e;
			return false;
		} catch (Exception e) {
			throw e;
		}
		
		return true;
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
