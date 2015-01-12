/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suntracker.controller;
import lejos.nxt.*;
import lejos.nxt.addon.*;
import lejos.robotics.navigation.*;
import lejos.util.Delay;

/**
 *
 * @author FunkyO //
 */

//Class for handling all kinds of movement. Some methods use input from compass and ultrasonic sensors. Mostly a class for controlling motors.
public class MovementController {

    CompassController compass;
    UltrasonicController ultrasonic;
    DifferentialPilot pilot;
    NXTRegulatedMotor panelMotor;
    
    public MovementController() {   	
    	
    }
    

    public MovementController(CompassController compassy) {
        this.compass = compassy;
    }
    
    
    public MovementController(CompassController compassCont, UltrasonicController usCont) {
        this.compass = compassCont;
        this.ultrasonic = usCont;
    }

    public void initiate() {
        this.pilot = new DifferentialPilot(4.0f, 97.5f, Motor.A, Motor.B, true);
        this.panelMotor = Motor.C;
    }
    
    public int getCurrentAngle() {
    	return (int)panelMotor.getTachoCount();    	    	
    }

    public void turnRight(int degrees) {
        pilot.rotate(degrees);
    }

    public void turnLeft(int degrees) {
        pilot.rotate(-(degrees));
    }

    public void moveForward(int centimeters) {
        pilot.travel(-(centimeters));
    }

    public void moveBackward(int centimeters) {
        pilot.travel(centimeters);
    }

    public void endMovement() {
        pilot.stop();
    }

    public void liftPanel(int degrees) {
        panelMotor.setSpeed(720);
        panelMotor.rotate(degrees);                
    }

    public void dropPanel(int degrees) {
    	
        panelMotor.setSpeed(720);
        panelMotor.rotate(-(degrees));        
        
    }
    
    //turns to face north, uses compasssensor reading
    public void turnNorth() {    	
        if (this.compass.getDegrees() < 180 && this.compass.getDegrees() > 0) {
            turnLeft((int)this.compass.getDegrees());
        } else if (this.compass.getDegrees() == 180) {
            turnRight(180);
        } else if (this.compass.getDegrees() == 0) {
        
        } else {
            turnRight(360 - (int)this.compass.getDegrees());
        }
    }
    
    
    
    // method which optimizes ST position for best possible angle to harvest sunlight, uses method setVerticalAngle and setHorizontalAngle
    public void optimizePosition(int horizontalReading) {
        
        setHorizontalAngle(horizontalReading);
    }
    
    // method which gets current compass reading and optimizes to reach target reading
    public void setVerticalAngle(int degrees) {    	
        liftPanel(degrees*22);
    }
    
    public void resetPanel() {
    	panelMotor.rotate(-(panelMotor.getTachoCount()));
    }
    
    // method which gets current panelMotor reading and adjusts angle to reach target reading
    public void setHorizontalAngle(int targetDegrees) {        
        int targetReading = targetDegrees;
        int currentReading = (int)this.compass.getDegrees();
        if (currentReading >= 270 && targetReading <= 90) {
        	turnRight(360 - currentReading + targetReading);        	
        } else if (targetReading > currentReading) {
        	turnRight(targetReading - currentReading);
        } else if (targetReading < currentReading) {
        	turnLeft(currentReading - targetReading);
        }
        
    }
    
    //method by which, robot finds new position from right, left, or behind existing one depending if there are no obstacles.
    public void findNewPosition(int horizontal) {
    	
    	turnRight(90);
    	
    	if (ultrasonic.getNearestObjectRange() < 120) {
    		turnLeft(180);
    		
    		if (ultrasonic.getNearestObjectRange() >= 120) {
    			moveForward(100);
    			optimizePosition(horizontal);
    			
    		} else {
    			turnLeft(90);
    			if (ultrasonic.getNearestObjectRange() > 120) {
    				moveForward(100);
    				optimizePosition(horizontal);
    			} else {
    				optimizePosition(horizontal);
    			}
    		}
    	} else {
    		moveForward(100);
    		optimizePosition(horizontal);
    		
    	}
    
    }
    
    
    
}