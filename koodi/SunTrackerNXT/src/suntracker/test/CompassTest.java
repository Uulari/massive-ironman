/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suntracker.test;

import lejos.nxt.Button;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.addon.CompassSensor;
import suntracker.controller.CompassController;

/**
 *
 * @author FunkyO
 */
public class CompassTest implements StartupTest {

    CompassController compass;
    
    //Test method for instantiating CompassController and checking return value from compassSensor
    @Override
    public void run() {

        try {
            this.compass = new CompassController(SensorPort.S1);
        } catch (Exception e) {
            e.printStackTrace();
            testFailed();
        }
        if (compass.getDegrees() >= 0 && compass.getDegrees() <= 359) {
            testSuccess();
        } else {
            testFailed();
        }
    }

    @Override
    public void testSuccess() {
        Sound.beep();
    }

    @Override
    public void testFailed() {
        Sound.beep();
    }

    public CompassController getCompass() {
        return this.compass;

    }
    
    public void displayReading() {
    	while(Button.ESCAPE.isPressed()){
    	System.out.println(this.compass.getDegrees());    	
    	}
    }

}