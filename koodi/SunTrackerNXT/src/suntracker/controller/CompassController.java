/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suntracker.controller;

import lejos.nxt.*;
import lejos.nxt.addon.*;

/**
 *
 * @author User
 */
//Wrapper class for compassSensor 
public class CompassController {
    
    CompassSensor compass;
    
    public CompassController(I2CPort port) {
        this.compass = new CompassSensor(port);
    }
    
    public void startCalibration() {
        this.compass.startCalibration();
    }
    
    public void stopCalibration() {
        this.compass.stopCalibration();
    }
    
    public float getDegrees() {
        return this.compass.getDegrees();
    }
    
    public CompassSensor getCompass() {
    	return this.compass;
    }
}