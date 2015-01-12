package suntracker.test;

import lejos.util.Delay;
import suntracker.controller.MovementController;

public class Tachotest {
	
	MovementController mv;
	
	public Tachotest() {
		this.mv = new MovementController();
	}
	public void run() {
		this.mv.initiate();
		this.mv.dropPanel(94*22);	
		Delay.msDelay(3000);		
	}
}
