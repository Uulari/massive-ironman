package suntracker.startconsole;

import suntracker.controller.BluetoothController;
import suntracker.controller.CompassController;
import suntracker.controller.MovementController;
import suntracker.controller.UltrasonicController;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.util.TimerListener;

public class StartTimer implements TimerListener {
	
	// instantiate controllers
	CompassController comp = new CompassController(SensorPort.S1);
	UltrasonicController us = new UltrasonicController(SensorPort.S2);
	MovementController movement = new MovementController(comp, us);
	BluetoothController btcont = new BluetoothController();
	
	//this function is executed when Timer reaches 0
	
	
	  
	//this function contains the main functionality of the robot. It is called when Timer reaches 0.
	@Override
	public void timedOut() {		
		
		movement.initiate();
		movement.turnNorth();
		btcont.openConnection();
		int azimuth = btcont.requestAzimuthAngle();
		btcont.closeConnection();
		btcont.openConnection();
		int zenith = btcont.requestZenithAngle();		
		btcont.closeConnection();
		if(azimuth > (comp.getDegrees()+3) || azimuth < (comp.getDegrees()-3)) {
			movement.setHorizontalAngle(azimuth);			
		}
		if (zenith > 5 && zenith <= 90) {
			movement.setVerticalAngle(zenith);			
		}		
		Sound.beepSequenceUp();
		if(us.getNearestObjectRange() < 100) {
			movement.findNewPosition(azimuth, zenith);
		}
	}
	
	public UltrasonicController getUSController() {
		return this.us;
	}

}