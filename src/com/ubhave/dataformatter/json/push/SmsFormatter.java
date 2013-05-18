/* **************************************************
 Copyright (c) 2012, University of Cambridge
 Neal Lathia, neal.lathia@cl.cam.ac.uk

This library was developed as part of the EPSRC Ubhave (Ubiquitous and
Social Computing for Positive Behaviour Change) Project. For more
information, please visit http://www.emotionsense.org

Permission to use, copy, modify, and/or distribute this software for any
purpose with or without fee is hereby granted, provided that the above
copyright notice and this permission notice appear in all copies.

THE SOFTWARE IS PROVIDED "AS IS" AND THE AUTHOR DISCLAIMS ALL WARRANTIES
WITH REGARD TO THIS SOFTWARE INCLUDING ALL IMPLIED WARRANTIES OF
MERCHANTABILITY AND FITNESS. IN NO EVENT SHALL THE AUTHOR BE LIABLE FOR ANY
SPECIAL, DIRECT, INDIRECT, OR CONSEQUENTIAL DAMAGES OR ANY DAMAGES
WHATSOEVER RESULTING FROM LOSS OF USE, DATA OR PROFITS, WHETHER IN AN
ACTION OF CONTRACT, NEGLIGENCE OR OTHER TORTIOUS ACTION, ARISING OUT OF OR
IN CONNECTION WITH THE USE OR PERFORMANCE OF THIS SOFTWARE.
 ************************************************** */

package com.ubhave.dataformatter.json.push;

import org.json.simple.JSONObject;

import com.ubhave.dataformatter.json.PushSensorJSONFormatter;
import com.ubhave.sensormanager.data.SensorData;
import com.ubhave.sensormanager.data.pushsensor.SmsData;

public class SmsFormatter extends PushSensorJSONFormatter
{
	private final static String CONTENT_LENGTH = "contentLength";
	private final static String WORD_COUNT = "wordCount";
	private final static String EVENT_TYPE = "eventType";
	private final static String ADDRESS = "address";
	
	@SuppressWarnings("unchecked")
	@Override
	protected void addSensorSpecificData(JSONObject json, SensorData data)
	{
		SmsData smsData = (SmsData) data;
		json.put(CONTENT_LENGTH, smsData.getContentLength());
		json.put(WORD_COUNT, smsData.getNoOfWords());
		json.put(EVENT_TYPE, smsData.getEventType());
		json.put(ADDRESS, smsData.getAddress());
		// TODO set features
	}
	
	@Override
	public SensorData toSensorData(String jsonString)
	{
		JSONObject jsonData = super.parseData(jsonString);
		if (jsonData != null)
		{
			long recvTimestamp = super.parseTimeStamp(jsonData);
			int smsLength = ((Long) jsonData.get(CONTENT_LENGTH)).intValue();
			int noOfWords = ((Long) jsonData.get(WORD_COUNT)).intValue();
			String addr = (String) jsonData.get(ADDRESS);
			String eventType = (String) jsonData.get(EVENT_TYPE);
			return new SmsData(recvTimestamp, smsLength, noOfWords, addr, eventType, null);
		}
		else return null;
	}
}
