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
package tain.kr.com.spirit.v04.recvsend;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.SocketTimeoutException;

import org.apache.log4j.Logger;

import tain.kr.com.spirit.v04.joint.AbsJoint;
import tain.kr.com.spirit.v04.loop.LoopSleep;


/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : ThrRecvSend.java
 *   -. Package    : tain.kr.com.spirit.v03.recvsend
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 2. 27. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public final class ThrRecvSend extends Thread {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(ThrRecvSend.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private final AbsJoint joint;
	private final DataInputStream dis;
	private final DataOutputStream dos;
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public ThrRecvSend(String thrName, AbsJoint joint, InputStream is, OutputStream os) {
		
		super(thrName);
		
		this.joint = joint;
		this.dis = new DataInputStream(is);
		this.dos = new DataOutputStream(os);
		
		if (!flag)
			log.debug(">>>>> in class " + this.getClass().getSimpleName());
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	protected static final int SIZ_RECV = 4096;

	private LoopSleep loopSleep;
	
	private byte[] bytRecv;
	private int nRecv;
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public void run() {
		
		if (flag) {
			/*
			 * initialize
			 */
			this.loopSleep = new LoopSleep();
			
			this.bytRecv = new byte[SIZ_RECV];
			this.nRecv = 0;
		}
		
		if (flag) {
			
			try {
				/*
				 * start loop
				 */
				while (!this.joint.flagStopThread) {
					if (flag) {
						/*
						 * recv
						 */
						if (!recv()) {
							loopSleep.sleep();
							continue;
						}
					}
					
					if (flag) {
						/*
						 * send
						 */
						send();
					}
					
					if (flag) {
						/*
						 * reset loop sleep time
						 */
						loopSleep.reset();
					}
				}
			} catch (Exception e) {
				if (!flag) e.printStackTrace();
			} finally {
				if (flag) {
					/*
					 * close and stop thread
					 */
					if (this.dis != null) try { this.dis.close(); } catch (IOException e) {}
					if (this.dos != null) try { this.dos.close(); } catch (IOException e) {}
					
					this.joint.flagStopThread = true;
					
					if (flag) System.out.printf("%s [END] ...\n", Thread.currentThread().getName());
				}
			}
		}
	}
	
	private boolean recv() throws Exception {
		
		try {
			this.nRecv = this.dis.read(this.bytRecv, 0, SIZ_RECV);
			if (this.nRecv < 0) {
				/*
				 * EOF -> finish
				 */
				if (flag) System.out.printf("%s [EOF] read data of EOF...\n", Thread.currentThread().getName());
				throw new Exception("read data of EOF. end of stream...");
			}
		} catch (SocketTimeoutException e) {
			// throw e;
			return false;   // -> continue;
		} catch (Exception e) {
			throw e;   // -> finish
		}
		
		return true;
	}

	private boolean send() throws Exception {
		
		try {
			this.dos.write(this.bytRecv, 0, this.nRecv);
		} catch (IOException e) {
			throw e;    // -> finish
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
