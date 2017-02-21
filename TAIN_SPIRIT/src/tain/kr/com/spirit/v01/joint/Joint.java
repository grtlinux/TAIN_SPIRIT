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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
public final class Joint implements ImpJoint {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(Joint.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private final int jointSeq;
	
	private final ThrControler thrControler1;
	private final ThrControler thrControler2;
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public Joint(int jointSeq) {
		
		this.jointSeq = jointSeq;
		
		/*
		 * create controlers
		 */
		this.thrControler1 = new ThrControler(String.format("THR_%03d%d", this.jointSeq, 1));
		this.thrControler2 = new ThrControler(String.format("THR_%03d%d", this.jointSeq, 2));
		
		/*
		 * set queue
		 */
		this.thrControler1.setRecvQueue(this.thrControler2.getSendQueue());  // 1_R (2_S)
		this.thrControler2.setRecvQueue(this.thrControler1.getSendQueue());  // 2_R (1_S)
		
		if (flag)
			log.debug(">>>>> in class " + this.getClass().getSimpleName());
	}

	public Joint() {
		this(JointSequence.getInstance().getSeq());
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	/* (non-Javadoc)
	 * @see tain.kr.com.spirit.v01.joint.ImpJoint#start()
	 */
	@Override
	public void start() {

		this.thrControler1.start();
		this.thrControler2.start();
	}

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

	/* (non-Javadoc)
	 * @see tain.kr.com.spirit.v01.joint.ImpJoint#setStream1(java.io.InputStream, java.io.OutputStream)
	 */
	@Override
	public void setStream1(InputStream is1, OutputStream os1) {
		
		this.thrControler1.setDataInputStream(is1);   // TODO   os1 -> dis
		this.thrControler1.setDataOutputStream(os1);  // TODO   dos -> is1
	}

	/* (non-Javadoc)
	 * @see tain.kr.com.spirit.v01.joint.ImpJoint#setStream2(java.io.InputStream, java.io.OutputStream)
	 */
	@Override
	public void setStream2(InputStream is2, OutputStream os2) {

		this.thrControler2.setDataInputStream(is2);   // TODO
		this.thrControler2.setDataOutputStream(os2);  // TODO
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
