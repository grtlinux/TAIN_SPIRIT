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
package tain.kr.com.spirit.v01.controler;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

import org.apache.log4j.Logger;

import tain.kr.com.spirit.v01.queue.QueueContent;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : ThrControler.java
 *   -. Package    : tain.kr.com.spirit.v01.controler
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 2. 21. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public final class ThrControler extends Thread implements ImpControler {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(ThrControler.class);

	///////////////////////////////////////////////////////////////////////////////////////////////

	private static final String THR_NAME = "CNTL";
	
	private final int indexThread;
	
	private final ThrRecver thrRecver;
	private final ThrSender thrSender;
	
	private QueueContent recvQueue = null;  // the other controler sendQueue
	private QueueContent sendQueue = null;
	
	private volatile boolean flagStop = false;
	
	private Socket socket = null;
	private DataInputStream dis = null;
	private DataOutputStream dos = null;
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public ThrControler(int indexThread) {
		
		super(String.format("THR_%04d_%s", indexThread, THR_NAME));
		
		this.indexThread = indexThread;
		
		this.thrRecver = new ThrRecver(this);
		this.thrSender = new ThrSender(this);
		
		this.recvQueue = null;               // the other controler sendQueue
		this.sendQueue = new QueueContent();

		if (flag)
			log.debug(String.format(">>>>> in class [%04d]", this.getClass().getSimpleName(), this.indexThread));
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public void run() {
		
		if (flag) {
			/*
			 * validate
			 */
			try {
				validation();
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}
		
		if (flag) {
			/*
			 * start thread
			 */
			this.thrRecver.start();
			this.thrSender.start();
		}
		
		/*
		 * blocking threads until child threads, thrSender and thrRecver
		 */
		
		if (flag) {
			/*
			 * thread join
			 */
			try {
				this.thrRecver.join();
				this.thrSender.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		if (flag) log.debug(String.format("[%s] END.", Thread.currentThread().getName()));
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private void validation() throws Exception {
		
		if (flag) {
			/*
			 * validate thread
			 */
			if (this.thrRecver == null) {
				throw new Exception("null pointer thread : ThrRecver");
			} else if (this.thrSender == null) {
				throw new Exception("null pointer thread : ThrSender");
			}
		}
		
		if (flag) {
			/*
			 * validate queue
			 */
			if (this.recvQueue == null) {
				throw new Exception("null pointer queue : recvQueue");
			} else if (this.sendQueue == null) {
				throw new Exception("null pointer queue : sendQueue");
			}
		}
		
		if (flag) {
			/*
			 * validate IO stream
			 */
			if (this.dis == null) {
				throw new Exception("null pointer DataInputStream : dis");
			} else if (this.dos == null) {
				throw new Exception("null pointer DataOutputStream : dos");
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean isFlagStop() {
		return this.flagStop;
	}
	
	public void stopThread() {
		
		try { Thread.sleep(2000); } catch (InterruptedException e) {}
		
		if (flag) log.debug(String.format("########## %s stopThread() ##########", Thread.currentThread().getName()));
		
		this.flagStop = true;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public boolean setRecvQueue(QueueContent recvQueue) {
		
		if (recvQueue != null) {
			this.recvQueue = recvQueue;
			return true;
		}
		
		return false;
	}
	
	public QueueContent getRecvQueue() {
		return this.recvQueue;
	}
	
	public QueueContent getSendQueue() {
		return this.sendQueue;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public void setDataInputStream(InputStream is) {
		this.dis = (DataInputStream) is;
	}
	
	public void setDataOutputStream(OutputStream os) {
		this.dos = (DataOutputStream) os;
	}
	
	public DataInputStream getDataInputStream() {
		return this.dis;
	}
	
	public DataOutputStream getDataOutputStream() {
		return this.dos;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public void setSocket(Socket socket) throws IOException {
		
		if (socket == null) {
			throw new IOException("null pointer socket");
		}
		
		this.socket = socket;
		
		try {
			this.dis = new DataInputStream(this.socket.getInputStream());
			this.dos = new DataOutputStream(this.socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
			// throw e;
		}
	}
	
	public Socket getSocket() {
		return this.socket;
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
