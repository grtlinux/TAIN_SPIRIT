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
package tain.kr.com.spirit.v01.joint;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import org.apache.log4j.Logger;

import tain.kr.com.spirit.v01.controler.ThrControler;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : Joint.java
 *   -. Package    : tain.kr.com.spirit.v01.joint
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 2. 21. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public final class ThrJoint extends Thread implements ImpJoint {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(ThrJoint.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@SuppressWarnings("unused")
	private final int jointSeq;
	
	private final ThrControler thrControler1;
	private final ThrControler thrControler2;
	
	private volatile boolean flagStop = false;

	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public ThrJoint(int jointSeq) {
		
		super(String.format("JOINT_%03d", jointSeq));

		this.jointSeq = jointSeq;
		
		/*
		 * create controlers
		 */
		this.thrControler1 = new ThrControler(this, String.format("%s%d", this.getName(), 1));
		this.thrControler2 = new ThrControler(this, String.format("%s%d", this.getName(), 2));
		
		/*
		 * set queue
		 */
		this.thrControler1.setRecvQueue(this.thrControler2.getSendQueue());  // 1_R (2_S)
		this.thrControler2.setRecvQueue(this.thrControler1.getSendQueue());  // 2_R (1_S)
		
		if (!flag)
			log.debug(">>>>> in class " + this.getClass().getSimpleName());
	}

	public ThrJoint() {
		this(JointSequence.getInstance().getSeq());
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	@Override
	public void run() {

		if (flag) {
			/*
			 * start controler threads
			 */
			this.thrControler1.start();
			this.thrControler2.start();
		}
		
		if (flag) {
			/*
			 * join controler threads for waiting to stop
			 */
			try {
				this.thrControler1.join();
				this.thrControler2.join();
			} catch (InterruptedException e) {}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	public boolean isFlagStop() {
		return this.flagStop;
	}
	
	public void stopThread() {
		
		final int MSEC_WAIT_STOPTHREAD = 1000;

		if (flag) log.debug(String.format("########## %s stopThread() ##########", Thread.currentThread().getName()));
		
		if (flag) try { Thread.sleep(MSEC_WAIT_STOPTHREAD); } catch (InterruptedException e) {}
		
		this.flagStop = true;

		if (!flag) try { Thread.sleep(MSEC_WAIT_STOPTHREAD); } catch (InterruptedException e) {}
	}

	///////////////////////////////////////////////////////////////////////////////////////////////

	/* (non-Javadoc)
	 * @see tain.kr.com.spirit.v01.joint.ImpJoint#setSocket1(java.net.Socket)
	 */
	@Override
	public void setSocket1(Socket socket1) throws IOException {
		
		this.thrControler1.setSocket(socket1);
	}

	/* (non-Javadoc)
	 * @see tain.kr.com.spirit.v01.joint.ImpJoint#setSocket2(java.net.Socket)
	 */
	@Override
	public void setSocket2(Socket socket2) throws IOException {
		
		this.thrControler2.setSocket(socket2);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	/* (non-Javadoc)
	 * @see tain.kr.com.spirit.v01.joint.ImpJoint#getOutDataInputStream1()
	 */
	@Override
	public DataInputStream getOutDataInputStream1() {
		
		return this.thrControler1.getOutDataInputStream();
	}

	/* (non-Javadoc)
	 * @see tain.kr.com.spirit.v01.joint.ImpJoint#getOutDataOutputStream1()
	 */
	@Override
	public DataOutputStream getOutDataOutputStream1() {

		return this.thrControler1.getOutDataOutputStream();
	}

	/* (non-Javadoc)
	 * @see tain.kr.com.spirit.v01.joint.ImpJoint#getOutDataInputStream2()
	 */
	@Override
	public DataInputStream getOutDataInputStream2() {

		return this.thrControler2.getOutDataInputStream();
	}

	/* (non-Javadoc)
	 * @see tain.kr.com.spirit.v01.joint.ImpJoint#getOutDataOutputStream2()
	 */
	@Override
	public DataOutputStream getOutDataOutputStream2() {

		return this.thrControler2.getOutDataOutputStream();
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	/* (non-Javadoc)
	 * @see tain.kr.com.spirit.v01.joint.ImpJoint#getInDataInputStream1()
	 */
	@Override
	public DataInputStream getInDataInputStream1() {

		return this.thrControler1.getInDataInputStream();
	}

	/* (non-Javadoc)
	 * @see tain.kr.com.spirit.v01.joint.ImpJoint#getInDataOutputStream1()
	 */
	@Override
	public DataOutputStream getInDataOutputStream1() {

		return this.thrControler1.getInDataOutputStream();
	}

	/* (non-Javadoc)
	 * @see tain.kr.com.spirit.v01.joint.ImpJoint#getInDataInputStream2()
	 */
	@Override
	public DataInputStream getInDataInputStream2() {

		return this.thrControler2.getInDataInputStream();
	}

	/* (non-Javadoc)
	 * @see tain.kr.com.spirit.v01.joint.ImpJoint#getInDataOutputStream2()
	 */
	@Override
	public DataOutputStream getInDataOutputStream2() {

		return this.thrControler2.getInDataOutputStream();
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
