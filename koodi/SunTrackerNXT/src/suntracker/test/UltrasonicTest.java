/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suntracker.test;

import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.nxt.UltrasonicSensor;

/**
 *
 * @author FunkyO
 */
public class UltrasonicTest implements StartupTest {
    
    UltrasonicSensor sonic;
    
    //Method for instantiating and testing out ultrasonic sensor input
    
    @Override
    public void run() {
       try {
           this.sonic = new UltrasonicSensor(SensorPort.S2);
       
       } catch (Exception e) {
           e.printStackTrace();
           testFailed();
       }
       try {
       if (this.sonic.getDistance() >= 0 && this.sonic.getDistance() <= 255) {
           testSuccess();
           System.out.println(this.sonic.getDistance());
           Thread.sleep(4000);
       } else {
           testFailed();
       }
       } catch (Exception e) {
    	   e.printStackTrace();    	   
       }
    }

    @Override
    public void testSuccess() {
        Sound.beep();
    }

    @Override
    public void testFailed() {
        Sound.buzz();
    }
    
}