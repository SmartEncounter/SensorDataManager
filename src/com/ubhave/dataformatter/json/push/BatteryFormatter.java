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

import android.os.BatteryManager;

import com.ubhave.dataformatter.json.PushSensorJSONFormatter;
import com.ubhave.sensormanager.data.SensorData;
import com.ubhave.sensormanager.data.pushsensor.BatteryData;

public class BatteryFormatter extends PushSensorJSONFormatter
{
	private final static String LEVEL = "level";
	private final static String SCALE = "scale";
	private final static String TEMPERATURE = "temperature";
	private final static String VOLTAGE = "voltage";
	private final static String PLUGGED = "plugged";
	private final static String STATUS = "status";
	private final static String HEALTH = "health";
	
	private static String getHealthString(int healthValue)
	{
		switch (healthValue)
		{
		case BatteryManager.BATTERY_HEALTH_DEAD:
			return "BATTERY_HEALTH_DEAD";
		case BatteryManager.BATTERY_HEALTH_GOOD:
			return "BATTERY_HEALTH_GOOD";
		case BatteryManager.BATTERY_HEALTH_OVERHEAT:
			return "BATTERY_HEALTH_OVERHEAT";
		case BatteryManager.BATTERY_HEALTH_OVER_VOLTAGE:
			return "BATTERY_HEALTH_OVER_VOLTAGE";
		case BatteryManager.BATTERY_HEALTH_UNKNOWN:
			return "BATTERY_HEALTH_UNKNOWN";
		case BatteryManager.BATTERY_HEALTH_UNSPECIFIED_FAILURE:
			return "BATTERY_HEALTH_UNSPECIFIED_FAILURE";
		default:
			return "UNKNOWN";
		}
	}

	private static String getStatusString(int statusValue)
	{
		switch (statusValue)
		{
		case BatteryManager.BATTERY_STATUS_CHARGING:
			return "BATTERY_STATUS_CHARGING";
		case BatteryManager.BATTERY_STATUS_DISCHARGING:
			return "BATTERY_STATUS_DISCHARGING";
		case BatteryManager.BATTERY_STATUS_FULL:
			return "BATTERY_STATUS_FULL";
		case BatteryManager.BATTERY_STATUS_NOT_CHARGING:
			return "BATTERY_STATUS_NOT_CHARGING";
		case BatteryManager.BATTERY_STATUS_UNKNOWN:
			return "BATTERY_STATUS_UNKNOWN";
		default:
			return "UNKNOWN";
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	protected void addSensorSpecificData(JSONObject json, SensorData data)
	{
		BatteryData batteryData = (BatteryData) data;
		json.put(LEVEL, batteryData.getBatteryLevel());
		json.put(SCALE, batteryData.getScale());
		json.put(TEMPERATURE, batteryData.getTemperature());
		json.put(VOLTAGE, batteryData.getVoltage());
		json.put(PLUGGED, batteryData.isCharging());
		json.put(STATUS, getStatusString(batteryData.getStatus()));
		json.put(HEALTH, getHealthString(batteryData.getHealth()));
	}
}