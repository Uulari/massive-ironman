package suntracker.startconsole;

import java.io.*;
import javax.microedition.lcdui.*;
import lejos.nxt.*;
import lejos.nxt.addon.*;
import lejos.nxt.comm.*;
import lejos.nxt.remote.*;
import lejos.robotics.*;

/**
 * Test motor implementations that support the DCMotor interface
 * including NXT motors, RCX motors and PF motors,
 * connected directly, remotely, or via a multiplexer or other device.
 */

//A graphical UI for running specific tests or starting main program
public class StartUI implements CommandListener {
	
  private static final int CMDID_EXIT_APP = 2;
  private static final Command EXIT_COMMAND = new Command(CMDID_EXIT_APP, Command.STOP, 2);
  
  private List mainMenu, testMenu, startMenu, menu;
  private Display display;
  private Ticker mainMenuTicker = new Ticker("Select item from menu");
  private Ticker testMenuTicker = new Ticker("Select test to run");
  private Ticker startMenuTicker = new Ticker("Press enter to start");
  private StartFunctions startFunctions = new StartFunctions(); 
  
  
   
  public void run() {
    mainMenu = new List("Main functions", Choice.IMPLICIT);
    mainMenu.append("Start main", null);
    mainMenu.append("Tests", null);
    mainMenu.addCommand(EXIT_COMMAND);
    mainMenu.setCommandListener(this);
    mainMenu.setTicker(mainMenuTicker);
    
    startMenu = new List("Ready to start", Choice.IMPLICIT);
    startMenu.append("Start main program", null);
    startMenu.addCommand(EXIT_COMMAND);
    startMenu.setCommandListener(this);
    startMenu.setTicker(startMenuTicker);
    
    testMenu = new List("Tests", Choice.IMPLICIT);
    testMenu.append("Compass", null);
    testMenu.append("Motors", null);
    testMenu.append("Ultrasonic", null);
    testMenu.append("Bluetooth", null);
    testMenu.append("Tacho", null);
    testMenu.addCommand(EXIT_COMMAND);
    testMenu.setCommandListener(this);
    testMenu.setTicker(testMenuTicker);
    
    // Make the system active
    menu = mainMenu;
    display = Display.getDisplay();
    display.setCurrent(menu);
    display.show(true);
  }
  
  //method for defining what happens when certain option is chosen
  public void commandAction(Command c, Displayable d) {
    if (c.getCommandId() == CMDID_EXIT_APP) {
      System.exit(0);
    } else if (c.getCommandId() == 0) { //select
      if (menu == mainMenu) {
        if (mainMenu.getSelectedIndex() ==0){
        	menu = startMenu;        	
        } else if (mainMenu.getSelectedIndex()==1) {
        	menu = testMenu;        	
        }
      } else if (menu == testMenu) {
        
        int action = testMenu.getSelectedIndex();
        
        switch (action) {
        case 0: startFunctions.testCompass();break;
        case 1: startFunctions.testMotors();break;
        case 2: startFunctions.testUltrasonic();break;
        case 3: startFunctions.testBluetooth();break;
        case 4: startFunctions.testTacho();break;
        }       		
      } else if (menu == startMenu) {
      	if(startMenu.getSelectedIndex()==0) {
      		LCD.clear();
      		LCD.refresh();
    		startFunctions.startMainProgram();        	
        	
        }
      }
      display.setCurrent(menu);
    }
  }
}

