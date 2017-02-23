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
package tain.kr.com.spirit.v01.main.v03.middle;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;

import tain.kr.com.spirit.v01.loop.LoopSleep;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : ThrClient.java
 *   -. Package    : tain.kr.com.spirit.v01.main.v03.middle
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 2. 23. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public final class ThrClientCommand extends Thread {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(ThrClientCommand.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private final Socket socket;
	private final DataInputStream dis;
	private final DataOutputStream dos;
	
	private final LoopSleep loopSleep;
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public ThrClientCommand(Socket socket) throws IOException {
		
		super("THREAD_MIDDLE_CLIENT");
		
		this.socket = socket;
		//this.socket.setSoTimeout(10 * 1000);     // socket timeout
		this.dis = new DataInputStream(this.socket.getInputStream());
		this.dos = new DataOutputStream(this.socket.getOutputStream());
		
		this.loopSleep = new LoopSleep();

		if (flag)
			log.debug(">>>>> in class " + this.getClass().getSimpleName());
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public void run() {
		/*
		 * thread start
		 */
		if (flag) {
			try {
				loopSleep.reset();
				
				for (int i=0; ; i++) {
					if (flag) {
						/*
						 * recv
						 */
						byte[] bytRecv = new byte[1024];
						int nRecv = 0;
						
						try {
							nRecv = this.dis.read(bytRecv);
							if (nRecv == 0) {
								loopSleep.sleep();
								continue;
							} else if (nRecv < 0) {
								/*
								 * the end of the input stream
								 */
								break;
							}
						} catch (Exception e) {
							loopSleep.sleep();
							continue;
						}
						
						String strRecv = new String(bytRecv, 0, nRecv, Charset.forName("euc-kr"));
						
						if (flag) log.debug(String.format("%s RECV [%d:%s]"
								, Thread.currentThread().getName(), nRecv, strRecv));
					}
					
					if (!flag) {
						/*
						 * send
						 */
						String strSend = String.format("client sends data to server....(seq-%03d)", i);
						byte[] bytSend = strSend.getBytes(Charset.forName("euc-kr"));
						
						this.dos.write(bytSend, 0, bytSend.length);
						
						if (flag) log.debug(String.format("%s SEND [%d:%s]"
								, Thread.currentThread().getName(), bytSend.length, strSend));
					}
					
					if (flag) {
						/*
						 * sleep
						 */
						LoopSleep.sleep(5 * 1000);
					}
					
					if (!flag) {
						/*
						 * reset loopSleep
						 */
						loopSleep.reset();
					}
				} // for

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
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
