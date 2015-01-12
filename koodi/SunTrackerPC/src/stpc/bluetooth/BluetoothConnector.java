package stpc.bluetooth;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import lejos.pc.comm.NXTConnector;

import stpc.solarposition.AzimuthZenithAngle;
import stpc.solarposition.SolarPositionCalculator;

public class BluetoothConnector {

	SolarPositionCalculator spc = new SolarPositionCalculator(60.19, 24.94);
	NXTConnector conn = new NXTConnector();
	DataOutputStream dos = null;
	DataInputStream dis = null;

	public void start() {

		while (true) {
			
			

			// Connect to any NXT over Bluetooth
			boolean connected = conn.connectTo("btspp://");
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (!connected) {
				System.err.println("Failed to connect to any NXT");
			} else if (connected == true) {
				this.dos = conn.getDataOut();
				this.dis = conn.getDataIn();

			}

			
			int msg = 0;
			AzimuthZenithAngle aziZen;

			// Wait for NXT to request data, then send them. Repeat.
			if (connected) {
				try {

					aziZen = spc.getSolarPosition();
					System.out.println("1");
					int azimuth = (int) aziZen.getAzimuth();
					System.out.println("2");
					int zenith = (int) aziZen.getZenithAngle();
					System.out.println("3");
					msg = this.dis.readInt();
					System.out.println(msg + "");
					if (msg == 75843) {
						System.out.println("Recieved code: " + msg);
						sendAzimuth(this.dos, azimuth);
						this.dos.flush();
					} else if (msg == 75844) {
						System.out.println("Recieved code: " + msg);
						sendZenith(this.dos, zenith);
					}
					System.out.println("message recieved on recievers end: " + this.dis.readBoolean());
				} catch (IOException ioe) {
					System.out.println("IO Exception in NXT communication:");
					System.out.println(ioe.getMessage());
					
				}
			}
		}
		/*
		 * // close connection try { dis.close(); dos.close(); conn.close(); }
		 * catch (IOException ioe) {
		 * System.out.println("IOException closing connection:");
		 * System.out.println(ioe.getMessage()); }
		 */
	}

	public static void sendAzimuth(DataOutputStream dos, int azimuth)
			throws IOException {

		System.out.println("Sending Azimuth");

		dos.writeInt(azimuth);
		dos.flush();
		System.out.println("  Sent azimuth angle: " + azimuth);

	}

	public static void sendZenith(DataOutputStream dos, int zenith)
			throws IOException {

		

		System.out.println("Sending Zenith");

		dos.writeInt(zenith);
		dos.flush();
		System.out.println("  Sent zenith angle: " + zenith);

	}

}
