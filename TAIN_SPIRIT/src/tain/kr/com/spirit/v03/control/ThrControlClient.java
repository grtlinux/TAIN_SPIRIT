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
package tain.kr.com.spirit.v03.control;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;

import tain.kr.com.spirit.v03.joint.ThrJointClient;
import tain.kr.com.spirit.v03.loop.LoopSleep;
import tain.kr.com.spirit.v03.param.ParamContent;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : ThrControlClient.java
 *   -. Package    : tain.kr.com.spirit.v03.control
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 2. 27. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public final class ThrControlClient extends Thread {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(ThrControlClient.class);

	///////////////////////////////////////////////////////////////////////////////////////////////

	private static final String THREAD_NAME = "CONTROL_CLIENT";
	
	private static final String KEY_CONTROL_HOST = "tain.kr.com.spirit.control.host";
	private static final String KEY_CONTROL_PORT = "tain.kr.com.spirit.control.port";
	
	private final String controlHost;
	private final String controlPort;

	private final Socket socket;
	private final DataInputStream dis;
	private final DataOutputStream dos;
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public ThrControlClient() throws Exception {

		super(THREAD_NAME);
		
		this.controlHost = ParamContent.getInstance().getString(KEY_CONTROL_HOST, "192.168.0.11");
		this.controlPort = ParamContent.getInstance().getString(KEY_CONTROL_PORT, "20025");
		
		this.socket = new Socket(this.controlHost, Integer.parseInt(this.controlPort));
		if (this.socket == null) {
			throw new IOException("ERROR: socket is null pointers..");
		}
		this.dis = new DataInputStream(this.socket.getInputStream());
		this.dos = new DataOutputStream(this.socket.getOutputStream());

		if (flag)
			log.debug(">>>>> in class " + this.getClass().getSimpleName());
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private static final String TYP_CHARSET = "euc-kr";

	private static final int SIZ_RECV = 4096;

	private String strRecv;
	private byte[] bytRecv;
	private int nRecv;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public void run() {
		
		if (flag) {
			/*
			 * start thread process : client
			 * recv
			 */
			try {
				while (true) {
					
					if (flag) {
						/*
						 * recv
						 */
						this.bytRecv = new byte[SIZ_RECV];
						
						try {
							this.nRecv = this.dis.read(this.bytRecv, 0, SIZ_RECV);

							if (this.nRecv < 0) {
								/*
								 * EOF
								 */
								if (flag) System.out.printf("%s [STATUS] read data of EOF...\n", Thread.currentThread().getName());
								throw new Exception("read data of EOF, end of stream");
							}
						} catch (Exception e) {
							/*
							 * Exception
							 */
							throw e;
						}
						
						this.strRecv = new String(this.bytRecv, 0, this.nRecv, Charset.forName(TYP_CHARSET));
						
						if (flag) System.out.printf("%s [RECV] [%d:%s]\n"
								, Thread.currentThread().getName(), this.nRecv, this.strRecv);
					} // end of recv
					
					if (flag) {
						/*
						 * run thread process
						 */
						new ThrJointClient().start();
					}
					
					if (flag) {
						/*
						 * sleep
						 */
						LoopSleep.sleep(1 * 1000);
					}
				}
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
			new ThrControlClient();

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
