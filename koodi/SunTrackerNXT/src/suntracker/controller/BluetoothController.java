package suntracker.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.microedition.lcdui.List;


import lejos.nxt.LCD;
import lejos.nxt.comm.BTConnection;
import lejos.nxt.comm.Bluetooth;
import lejos.util.Delay;

public class BluetoothController {
	
	MovementController mvmnt = new MovementController();
	BTConnection btc = null;
	String connected = "Connected";
	String waiting = "Waiting...";
	String closing = "Closing...";

	DataInputStream dis;
	DataOutputStream dos;

	// method for opening connection on NXT side
	public void openConnection() {

		LCD.drawString(waiting, 0, 0);
		LCD.refresh();

		btc = Bluetooth.waitForConnection();

		LCD.clear();
		LCD.drawString(connected, 0, 0);
		LCD.refresh();

		this.dis = btc.openDataInputStream();
		this.dos = btc.openDataOutputStream();

	}

	// method used to request Azimuth angle from PC
	public int requestAzimuthAngle() {

		int azimuth = 0;

		try {

			this.dos.writeInt(75843);
			this.dos.flush();
			Delay.msDelay(1000);
			azimuth = dis.readInt();
			if (azimuth >= 0 && azimuth <= 360) {
				this.dos.writeBoolean(true);
				this.dos.flush();
				LCD.clear();
				Delay.msDelay(2000);
//				LCD.drawString("Angle: " + azimuth + " degrees", 0, 0);
				LCD.drawString(""+this.mvmnt.getCurrentAngle(), 0, 2);
				Delay.msDelay(2000);
				LCD.refresh();
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return azimuth;

	}

	// method used to request zenith angle from PC
	public int requestZenithAngle() {
		int zenith = 0;

		try {

			this.dos.writeInt(75844);
			this.dos.flush();
			Delay.msDelay(1000);
			zenith = dis.readInt();
			if (zenith >= 0 && zenith <= 360) {
				this.dos.writeBoolean(true);				
				this.dos.flush();
				LCD.clear();
				Delay.msDelay(2000);
//				LCD.drawString("Angle: " + zenith + " degrees", 0, 2);
				LCD.drawString(""+this.mvmnt.getCurrentAngle(), 0, 2);
				Delay.msDelay(2000);
				LCD.refresh();
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return zenith;

	}

	// Method for closing BT connection with PC
	public void closeConnection() {
		try {
			this.dis.close();
			this.dos.close();
			Thread.sleep(100); // wait for data to drain
			LCD.clear();
			LCD.drawString(closing, 0, 0);
			LCD.refresh();
			btc.close();
			LCD.clear();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
