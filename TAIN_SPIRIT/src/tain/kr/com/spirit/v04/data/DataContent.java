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
package tain.kr.com.spirit.v04.data;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;

import org.apache.log4j.Logger;

/**
 * Code Templates > Comments > Types
 *
 * <PRE>
 *   -. FileName   : DataContent.java
 *   -. Package    : tain.kr.com.spirit.v04.data
 *   -. Comment    :
 *   -. Author     : taincokr
 *   -. First Date : 2017. 2. 27. {time}
 * </PRE>
 *
 * @author taincokr
 *
 */
public final class DataContent extends AbsData {

	private static boolean flag = true;

	private static final Logger log = Logger.getLogger(DataContent.class);

	///////////////////////////////////////////////////////////////////////////////////////////////
	
	private static final String TYP_CHARSET = "euc-kr";
	
	private String strData;
	
	///////////////////////////////////////////////////////////////////////////////////////////////

	/*
	 * constructor
	 */
	public DataContent(int size) {
		
		super(size);
		
		if (!flag)
			log.debug(">>>>> in class " + this.getClass().getSimpleName());
	}

	public DataContent() {
		this(AbsData.SIZ_DEF_BYTDATA);
	}
	
	public DataContent(String strData) throws IOException {
		this();
		
		setStrData(strData);
	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	public void setStrData(String strData) throws IOException {
		this.strData = strData;
		
		byte[] bytSrc = this.strData.getBytes(Charset.forName(TYP_CHARSET));
		this.size = bytSrc.length;
		
		if (this.bytData.length < this.size) {
			throw new IOException(String.format("[ERROR] size (%d) is more than byte buffer 'bytData' (%d)"
					, this.size, this.bytData.length));
		}
		
		System.arraycopy(bytSrc, 0, this.bytData, 0, this.size);
	}
	
	public void setStrData() {
		this.strData = new String(this.bytData, 0, this.size, Charset.forName(TYP_CHARSET));
	}
	
	public String getStrData() {
		return this.strData;
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	/* (non-Javadoc)
	 * @see tain.kr.com.spirit.v04.data.AbsData#readFromInputStream(java.io.InputStream)
	 */
	@Override
	public int readFromInputStream(InputStream is) throws IOException {
		
		this.size = is.read(this.bytData);
		
		if (this.size < 0) {
			throw new IOException(String.format("[STATUS] read EOF from InputStream.."));
		}
		
		if (!flag) setStrData();
		
		return this.size;
	}

	/* (non-Javadoc)
	 * @see tain.kr.com.spirit.v04.data.AbsData#writeToOutputStream(java.io.OutputStream)
	 */
	@Override
	public void writeToOutputStream(OutputStream os) throws IOException {
		
		os.write(this.bytData, 0, this.size);
		os.flush();
	}

	/* (non-Javadoc)
	 * @see tain.kr.com.spirit.v04.data.AbsData#createClone()
	 */
	@Override
	public DataContent createClone() {
		
		DataContent content = null;
		
		try {
			content = (DataContent) clone();
		} catch (CloneNotSupportedException e) {
			e.printStackTrace();
		}
		
		return content;
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

		if (flag)
			new DataContent();

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
