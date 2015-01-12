/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suntracker.test;

import lejos.nxt.SensorPort;
import lejos.nxt.Sound;
import lejos.util.Delay;
import suntracker.controller.CompassController;
import suntracker.controller.MovementController;
import suntracker.controller.UltrasonicController;

/**
 *
 * @author FunkyO
 */
public class MotorTest implements StartupTest {

    MovementController testMover;

    @Override
    public void run() {

        //Method for instantiating MovementController         
        try {
            testMover = new MovementController(new CompassController(SensorPort.S1), new UltrasonicController(SensorPort.S2));
            Thread.sleep(1500);
        } catch (Exception e) {
            e.printStackTrace();
            testFailed();
        }

        //Method for testing out main methods of MovementController
        try {
            testMover.initiate();
            testMover.moveBackward(50);
            testMover.moveForward(50);
            testMover.turnLeft(90);
            testMover.turnRight(180);            
            testMover.dropPanel(720);
            Delay.msDelay(2000);
            testMover.liftPanel(720);
            testMover.findNewPosition(45);
            Sound.beepSequenceUp();            
            testMover.endMovement();
        } catch (Exception e) {
            e.printStackTrace();
            testFailed();
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