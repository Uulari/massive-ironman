package suntracker.test;

import lejos.nxt.LCD;
import lejos.util.Delay;
import suntracker.controller.BluetoothController;

public class BluetoothTest {
	
	public void run() {
		BluetoothController btconn = new BluetoothController();
		btconn.openConnection();
		int zen = btconn.requestZenithAngle();
		Delay.msDelay(2000);
		btconn.closeConnection();
		
		btconn.openConnection();
		int azi = btconn.requestAzimuthAngle();
		Delay.msDelay(2000);
		btconn.closeConnection();
		
	}
}
