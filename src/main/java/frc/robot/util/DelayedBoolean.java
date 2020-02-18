package frc.robot.util;

import edu.wpi.first.wpilibj.Timer;

public class DelayedBoolean {

	private double trueStartTime = 0;
	private boolean counting = false;
	private double delay;
	
	public DelayedBoolean(double delay) {
		this.delay = delay;
	}
	
	public boolean update(boolean input) {
		if(input) {
			if(counting) {
				return getOutputValue();
			} else {
				trueStartTime = Timer.getFPGATimestamp();
				counting = true;
				// This will only return true if the delay is 0
				return getOutputValue();
			}
		} else {
			counting = false;
			return false;
		}
	}
	
	public boolean getOutputValue() {
		return Timer.getFPGATimestamp() - trueStartTime > delay; 
	}

}