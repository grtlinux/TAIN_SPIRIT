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
package tain.kr.com.spirit.v03.queue;

import java.util.Vector;

import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : QueueContent.java
 *   -. Package    : tain.kr.com.spirit.v03.queue
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 2. 27. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public final class QueueContent implements ImpQueue {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(QueueContent.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private final Vector<Object> queue;
	private int size;
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public QueueContent() {
		
		this.queue = new Vector<Object>(5, 5);
		this.size = 0;
		
		if (!flag)
			log.debug(">>>>> in class " + this.getClass().getSimpleName());
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	/* (non-Javadoc)
	 * @see tain.kr.com.spirit.v02.queue.ImpQueue#put(java.lang.Object)
	 */
	@Override
	public synchronized int put(Object object) throws Exception {
		
		if (object == null) {
			throw new Exception(String.format("[WARN] object parameter is null pointer"));
		}
		
		this.queue.addElement(object);
		this.size ++;
		
		this.notifyAll();
		
		return this.size;
	}

	/* (non-Javadoc)
	 * @see tain.kr.com.spirit.v02.queue.ImpQueue#get()
	 */
	@Override
	public synchronized Object get() throws Exception {
		
		try {
			wait();
		} catch (InterruptedException e) {}
		
		if (this.size <= 0) {
			return null;
		}
		
		Object object = this.queue.elementAt(0);
		this.queue.remove(0);
		this.size --;
		
		return object;
	}

	/* (non-Javadoc)
	 * @see tain.kr.com.spirit.v02.queue.ImpQueue#get(long)
	 */
	@Override
	public synchronized Object get(long timeout) throws Exception {
		
		try {
			wait(timeout);
		} catch (InterruptedException e) {}
		
		if (this.size <= 0) {
			// return null;
			throw new Exception("return null pointer : [this.size = 0]");  // TODO : 2017.02.21
		}
		
		Object object = this.queue.elementAt(0);
		this.queue.remove(0);
		this.size --;
		
		return object;
	}

	/* (non-Javadoc)
	 * @see tain.kr.com.spirit.v02.queue.ImpQueue#clear()
	 */
	@Override
	public synchronized void clear() {
		
		this.queue.removeAllElements();
		this.size = 0;
	}

	/* (non-Javadoc)
	 * @see tain.kr.com.spirit.v02.queue.ImpQueue#getSize()
	 */
	@Override
	public int getSize() {
		return this.size;
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
