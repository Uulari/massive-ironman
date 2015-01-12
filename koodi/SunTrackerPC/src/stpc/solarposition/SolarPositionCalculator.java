package stpc.solarposition;

import java.util.GregorianCalendar;

public class SolarPositionCalculator {
	
	private double latitude;
	private double longitude;
	final GregorianCalendar dateTime;
	
	public SolarPositionCalculator(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	    this.dateTime = new GregorianCalendar();

	}
	  

	public AzimuthZenithAngle getSolarPosition() {
 
	     AzimuthZenithAngle position = SPA.calculateSolarPosition(dateTime,
	                                          latitude,
	                                          longitude,
	                                          190, // elevation
	                                          67, // delta T
	                                          1010, // avg. air pressure
	                                          11); // avg. air temperature
//	    System.out.println("SPA: " + position);
	    return position;
	  }
	}