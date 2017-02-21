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

import java.io.DataOutputStream;

import org.apache.log4j.Logger;

import tain.kr.com.spirit.v01.data.DataContent;
import tain.kr.com.spirit.v01.joint.ThrJoint;
import tain.kr.com.spirit.v01.loop.LoopSleep;
import tain.kr.com.spirit.v01.queue.QueueContent;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : ThrSender.java
 *   -. Package    : tain.kr.com.spirit.v01.controler
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 2. 21. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public final class ThrSender extends Thread {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(ThrSender.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private static final String THR_NAME = "SEND";
	
	private final ThrJoint joint;
	private final ThrControler thrControler;
	private final LoopSleep loopSleep;
	
	private QueueContent sendQueue;  // this controler sendQueue
	private DataContent content;
	private DataOutputStream dos;
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public ThrSender(ThrJoint joint, ThrControler thrControler) {
		
		super(String.format("%s_%s", thrControler.getGroupName(), THR_NAME));
		
		this.joint = joint;
		this.thrControler = thrControler;
		this.loopSleep = new LoopSleep();
		
		if (flag)
			log.debug(">>>>> in class " + this.getClass().getSimpleName());
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	@Override
	public void run() {

		if (flag) {
			/*
			 * base initialize
			 */
			try {
				baseInitialize();
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}
		
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
			 * job processing
			 */
			while (!this.joint.isFlagStop()) {
				
				if (flag) {
					/*
					 * read from sendQueue
					 */
					try {
						this.content = (DataContent) this.sendQueue.get(this.loopSleep.getMSec());
						//if (this.content == null)
						//	continue;
					} catch (Exception e) {
						if (!flag) e.printStackTrace();
						continue;
					}
				}

				if (flag) {
					/*
					 * write to DataOutputStream
					 */
					try {
						this.content.writeToOutputStream(this.dos);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				
				if (flag) log.debug(String.format("%s SEND(%4d): %s."
						, Thread.currentThread().getName(), this.content.getSize(), this.content.getStrData()));

				this.loopSleep.reset();
			}
		}

		if (flag) {
			/*
			 * end job
			 */
			if (flag) log.debug(String.format("[%s] END", Thread.currentThread().getName()));
			
			if (flag) this.joint.stopThread();
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private void baseInitialize() throws Exception {
		
		if (flag) {
			/*
			 * initialize
			 */
			this.sendQueue = this.thrControler.getSendQueue();
			this.content = new DataContent();
			this.dos = this.thrControler.getInDataOutputStream();
		}
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private void validation() throws Exception {
		
		if (flag) {
			/*
			 * validate
			 */
			if (this.sendQueue == null) {
				throw new Exception("null pointer queue : sendQueue");
			}
			
			if (this.dos == null) {
				throw new Exception("null pointer DataOutputStream");
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
