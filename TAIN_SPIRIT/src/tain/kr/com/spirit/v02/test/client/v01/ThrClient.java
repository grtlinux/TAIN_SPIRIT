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
package tain.kr.com.spirit.v02.test.client.v01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;

import tain.kr.com.spirit.v02.loop.LoopSleep;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : ThrClient.java
 *   -. Package    : tain.kr.com.spirit.v02.test.client.v01
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 2. 25. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public final class ThrClient extends Thread {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(ThrClient.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private static final String TYP_CHARSET = "euc-kr";

	private static final String CONNECT_HOST = "127.0.0.1";
	private static final String CONNECT_PORT = "20025";
	
	private final Socket socket;
	private final DataInputStream dis;
	private final DataOutputStream dos;
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public ThrClient() throws Exception {
		
		super("MAIN_CLIENT");
		
		this.socket = new Socket(CONNECT_HOST, Integer.parseInt(CONNECT_PORT));
		if (this.socket == null) {
			throw new IOException("ERROR: socket is null pointers..");
		}
		this.dis = new DataInputStream(this.socket.getInputStream());
		this.dos = new DataOutputStream(this.socket.getOutputStream());
		
		if (flag)
			log.debug(">>>>> in class " + this.getClass().getSimpleName());
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private String strSend;
	private byte[] bytSend;
	private int nSend;
	
	private String strRecv;
	private byte[] bytRecv;
	private int nRecv;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public void run() {
		
		if (flag) {
			/*
			 * start thread process
			 */
			for (int i=0; i < 1; i++) {
				if (flag) {
					/*
					 * send
					 */
					this.strSend = "Hello, world!!!";
					this.bytSend = this.strSend.getBytes(Charset.forName(TYP_CHARSET));
					this.nSend = this.bytSend.length;
					
					try {
						this.dos.write(this.bytSend, 0, this.nSend);
					} catch (IOException e) {
						e.printStackTrace();
						break;
					}
					
					if (flag) System.out.printf("%s [STATUS] SEND [%d:%s]\n"
							, Thread.currentThread().getName(), this.nSend, this.strSend);
				}
				
				if (flag) {
					/*
					 * recv
					 */
					this.nRecv = 1024;
					this.bytRecv = new byte[this.nRecv];
					
					try {
						this.nRecv = this.dis.read(this.bytRecv, 0, this.nRecv);
						if (this.nRecv == 0) {
							/*
							 * read data of 0 size
							 */
							if (flag) System.out.printf("%s [STATUS] read data of 0 size..\n", Thread.currentThread().getName());
						} else if (this.nRecv < 0) {
							/*
							 * EOF
							 */
							if (flag) System.out.printf("%s [STATUS] read data of EOF...\n", Thread.currentThread().getName());
							break;
						}
					} catch (IOException e) {
						/*
						 * Exception
						 */
						if (flag) System.out.printf("%s [STATUS] exception during reading...\n", Thread.currentThread().getName());
						if (flag) e.printStackTrace();
						break;
					}
					
					this.strRecv = new String(this.bytRecv, 0, this.nRecv, Charset.forName(TYP_CHARSET));
					
					if (flag) System.out.printf("%s [STATUS] RECV [%d:%s]\n"
							, Thread.currentThread().getName(), this.nRecv, this.strRecv);
				}
				
				if (flag) {
					/*
					 * sleep
					 */
					LoopSleep.sleep(1 * 1000);
				}
			}
			
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
