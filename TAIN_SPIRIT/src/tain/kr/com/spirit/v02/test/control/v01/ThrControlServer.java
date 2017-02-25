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
package tain.kr.com.spirit.v02.test.control.v01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;

import tain.kr.com.spirit.v02.loop.LoopSleep;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : ThrControlServer.java
 *   -. Package    : tain.kr.com.spirit.v02.test.control.v01
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 2. 25. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public final class ThrControlServer extends Thread {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(ThrControlServer.class);

	///////////////////////////////////////////////////////////////////////////////////////////////

	private static final String TYP_CHARSET = "euc-kr";

	private final Socket socket;
	private final DataInputStream dis;
	private final DataOutputStream dos;
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public ThrControlServer(ServerSocket serverSocket) throws Exception {
		
		super("CONTROL_SERVER");
		
		this.socket = serverSocket.accept();
		if (this.socket == null) {
			throw new IOException("ERROR: socket is null pointer..");
		}
		
		if (flag) {
			/*
			 * socket option
			 */
			if (flag && this.socket.getTcpNoDelay() == false) {        // TCP_NODELAY
				this.socket.setTcpNoDelay(true);
			}
			
			if (flag && this.socket.getSoTimeout() == 0) {              // SO_TIMEOUT
				// this.socket.setSoTimeout(0);  // 0 -> timeout of infinity
				this.socket.setSoTimeout(2 * 1000);
			}
			
			if (flag && this.socket.getSoLinger() == -1) {              // SO_LINGER
				this.socket.setSoLinger(true, 0);
			}
			
			if (flag && this.socket.getReceiveBufferSize() < 10240) {   // SO_RCVBUF
				this.socket.setReceiveBufferSize(10240);
			}
			
			if (flag && this.socket.getSendBufferSize() < 10240) {      // SO_SNDBUF
				this.socket.setSendBufferSize(10240);
			}
			
			if (flag && this.socket.getKeepAlive() == false) {         // SO_KEEPALIVE
				this.socket.setKeepAlive(true);
			}
			
			if (flag && this.socket.getReuseAddress() == false) {      // SO_REUSEADDR
				this.socket.setReuseAddress(true);
			}
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

	private static final int SIZ_RECV = 1024;
	
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
			this.bytRecv = new byte[SIZ_RECV];

			while (true) {
				if (flag) {
					/*
					 * recv
					 */
					try {
						this.nRecv = this.dis.read(this.bytRecv, 0, SIZ_RECV);
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
					} catch (SocketTimeoutException e) {
						/*
						 * SocketTimeoutException
						 */
						if (flag) System.out.printf("%s [STATUS] SocketTimeoutException...\n", Thread.currentThread().getName());
						continue;
					} catch (Exception e) {
						/*
						 * Exception
						 */
						if (flag) e.printStackTrace();
						break;
					}
					
					this.strRecv = new String(this.bytRecv, 0, this.nRecv, Charset.forName(TYP_CHARSET));
					
					if (flag) System.out.printf("%s [STATUS] RECV [%d:%s]\n"
							, Thread.currentThread().getName(), this.nRecv, this.strRecv);
				}
				
				if (flag) {
					/*
					 * send
					 */
					this.strSend = "OK!! How are you doing these days?~~";
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
