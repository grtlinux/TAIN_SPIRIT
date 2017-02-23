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
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.net.Socket;

import org.apache.log4j.Logger;

import tain.kr.com.spirit.v01.joint.ThrJoint;
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
	
	private final ThrJoint joint;
	private final String groupName;
	
	private final ThrRecver thrRecver;
	private final ThrSender thrSender;
	
	private QueueContent recvQueue = null;  // the other controler sendQueue
	private QueueContent sendQueue = null;
	
	private Socket socket = null;
	private DataInputStream inDis = null;
	private DataOutputStream inDos = null;
	
	/*
	 * outDis <-> inDos
	 * outDos <-> inDis
	 */
	private DataInputStream outDis = null;
	private DataOutputStream outDos = null;
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 * 
	 *   indexThread
	 *        0011 : controler-1
	 *        0012 : controler-2
	 */
	public ThrControler(ThrJoint joint, String groupName) {
		
		super(String.format("%s_%s", groupName, THR_NAME));
		
		this.joint = joint;
		this.groupName = groupName;
		
		this.thrRecver = new ThrRecver(this.joint, this);
		this.thrSender = new ThrSender(this.joint, this);
		
		this.recvQueue = null;               // the other controler sendQueue
		this.sendQueue = new QueueContent();

		if (flag) {
			/*
			 * outDos ---> inDis
			 */
			try {
				PipedInputStream pis = new PipedInputStream();
				PipedOutputStream pos = new PipedOutputStream(pis);
				
				this.outDos = new DataOutputStream(pos);
				this.inDis = new DataInputStream(pis);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (flag) {
			/*
			 * inDos ---> outDis
			 */
			try {
				PipedInputStream pis = new PipedInputStream();
				PipedOutputStream pos = new PipedOutputStream(pis);
				
				this.inDos = new DataOutputStream(pos);
				this.outDis = new DataInputStream(pis);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (!flag)
			log.debug(String.format(">>>>> in class [%s] (%s)", this.getClass().getSimpleName(), this.groupName));
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
		
		if (flag) {
			/*
			 * close
			 */
			if (this.socket != null) try { this.socket.close(); } catch (IOException e) {}
			
			if (this.inDis != null) try { this.inDis.close(); } catch (IOException e) {}
			if (this.inDos != null) try { this.inDos.close(); } catch (IOException e) {}
			
			if (this.outDis != null) try { this.outDis.close(); } catch (IOException e) {}
			if (this.outDos != null) try { this.outDos.close(); } catch (IOException e) {}
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
			if (this.inDis == null) {
				throw new Exception("null pointer DataInputStream : inDis");
			} else if (this.inDos == null) {
				throw new Exception("null pointer DataOutputStream : inDos");
			} else if (this.outDis == null) {
				throw new Exception("null pointer DataInputStream : outDis");
			} else if (this.outDos == null) {
				throw new Exception("null pointer DataOutputStream : outDos");
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public String getGroupName() {
		return this.groupName;
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
	
	public DataInputStream getInDataInputStream() {
		return this.inDis;
	}
	
	public DataOutputStream getInDataOutputStream() {
		return this.inDos;
	}
	
	public DataInputStream getOutDataInputStream() {
		return this.outDis;
	}
	
	public DataOutputStream getOutDataOutputStream() {
		return this.outDos;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	public void setSocket(Socket socket) throws IOException {
		
		if (socket == null) {
			throw new IOException("null pointer socket");
		}
		
		this.socket = socket;
		
		/*
		 * TODO 2017.02.23 very important
		 * Enable/disable SO_TIMEOUT with the specified timeout in milliseconds.
		 */
		if (flag) this.socket.setSoTimeout(10 * 1000);
		
		try {
			this.inDis = new DataInputStream(this.socket.getInputStream());
			this.inDos = new DataOutputStream(this.socket.getOutputStream());
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
