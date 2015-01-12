package suntracker.main;

import java.io.PrintStream;
import java.util.Random;

import suntracker.startconsole.StartUI;

import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.util.KalmanFilter;
import lejos.util.Matrix;
import lejos.util.PilotProps;
import lejos.nxt.*;
import lejos.nxt.comm.RConsole;
import suntracker.startconsole.*;



public class Main {  

	public static void main(String[] args) {
		
		//Create and start User Interface class
        StartUI startInterface = new StartUI();
        startInterface.run();
    }	
}