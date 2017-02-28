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
package tain.kr.com.spirit.v04.test.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;

import tain.kr.com.spirit.v03.loop.LoopSleep;
import tain.kr.com.spirit.v03.param.ParamContent;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : ThrClient.java
 *   -. Package    : tain.kr.com.spirit.v03.test.client
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 2. 27. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public final class ThrClient extends Thread {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(ThrClient.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private static final String THREAD_NAME = "TEST_CLIENT";
	
	private static final String KEY_CONNECT_HOST = "tain.kr.com.spirit.test.client.host";
	private static final String KEY_CONNECT_PORT = "tain.kr.com.spirit.test.client.port";
	
	private final String connectHost;
	private final String connectPort;
	
	private final Socket socket;
	private final DataInputStream dis;
	private final DataOutputStream dos;
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public ThrClient() throws Exception {
		
		super(THREAD_NAME);
		
		this.connectHost = ParamContent.getInstance().getString(KEY_CONNECT_HOST, "192.168.0.11");
		this.connectPort = ParamContent.getInstance().getString(KEY_CONNECT_PORT, "13389");
		
		this.socket = new Socket(this.connectHost, Integer.parseInt(this.connectPort));
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
		if (flag) System.out.printf("\t ParamContent [%s] = [%s]\n", KEY_CONNECT_HOST, this.connectHost);
		if (flag) System.out.printf("\t ParamContent [%s] = [%s]\n", KEY_CONNECT_PORT, this.connectPort);
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
			 * start thread process for send and recv
			 */
			try {
				for (int i=0; i < 1; i++) {
					/*
					 * for loop
					 */
					if (flag) {
						/*
						 * send
						 */
						this.strSend = "Hello, world!!!";
						this.bytSend = this.strSend.getBytes(Charset.forName(TYP_CHARSET));
						this.nSend = this.bytSend.length;
						
						send();
						
						if (flag) System.out.printf("%s [SEND] [%d:%s]\n", Thread.currentThread().getName(), this.nSend, this.strSend);
					}
					
					if (flag) {
						/*
						 * recv
						 */
						
						recv();
						
						this.strRecv = new String(this.bytRecv, 0, this.nRecv, Charset.forName(TYP_CHARSET));
						
						if (flag) System.out.printf("%s [RECV] [%d:%s]\n", Thread.currentThread().getName(), this.nRecv, this.strRecv);
					}
					
					if (flag) {
						/*
						 * sleep
						 */
						LoopSleep.sleep(2 * 1000);
					}
				}  // for
				
			} catch (Exception e) {
				e.printStackTrace();
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
	
	private void send() throws Exception {
		
		try {
			this.dos.write(this.bytSend, 0, this.nSend);
		} catch (IOException e) {
			throw e;
		}
	}
	
	private void recv() throws Exception {
		
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
			throw e;
		} catch (Exception e) {
			throw e;
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

	/*
	 * static test method
	 */
	private static void test01(String[] args) throws Exception {

		if (flag)
			new ThrClient();

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
