package suntracker.controller;

import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

//Wrapper class for ultrasonicSensor
public class UltrasonicController {
	
	UltrasonicSensor us;
	
	public UltrasonicController(SensorPort port) {
		us = new UltrasonicSensor(port);
	}	
	
	public void setContinuousMode() {
		this.us.continuous();		
	}
	
	public float getContinuousReading() {
		return this.us.getDistance();		
	}
	
	public float getNearestObjectRange() {
		return this.us.getRange();
	}
}
