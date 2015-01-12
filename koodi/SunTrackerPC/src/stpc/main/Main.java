package stpc.main;
import lejos.pc.comm.*;
import java.io.*;

import stpc.bluetooth.BluetoothConnector;
import stpc.solarposition.SolarPositionCalculator;


public class Main {	
	
	public static void main(String[] args) {
		BluetoothConnector btconnector = new BluetoothConnector();
		btconnector.start();
		
	}
}
