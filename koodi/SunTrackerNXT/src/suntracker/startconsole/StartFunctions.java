/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suntracker.startconsole;

import lejos.nxt.Button;

import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.nxt.addon.*;
import lejos.util.Timer;
import suntracker.controller.BluetoothController;
import suntracker.controller.CompassController;
import suntracker.controller.MovementController;
import suntracker.test.BluetoothTest;
import suntracker.test.CompassTest;
import suntracker.test.MotorTest;
import suntracker.test.Tachotest;
import suntracker.test.UltrasonicTest;

/**
 *
 * @author FunkyO
 */

//A class containing all bigger methods that will be executed by StartUI. Contains methods for running Tests and Starting Main Program.
public class StartFunctions {
    
        
    public void startMainProgram() {
    	Timer timer = new Timer(10000, new StartTimer());
    	timer.start();
    	
    }            
    
    public void testCompass() {
   	 CompassTest ct = new CompassTest();
   	 ct.run();
   	
   }
    
    public void testTacho() {
    	
    	Tachotest t = new Tachotest();
    	t.run();
    }
    
    public void testMotors() {
      	 MotorTest mt = new MotorTest();
      	 mt.run();
      	
      }
    
    public void testUltrasonic() {
    	UltrasonicTest ut = new UltrasonicTest();
    	ut.run();
      	
      }
    
    public void testBluetooth() {
    	
    	BluetoothTest bt = new BluetoothTest();
    	bt.run();
    	
    }
    
    
    
}