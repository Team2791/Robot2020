package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
public class PauseLift extends Command {
	
    public PauseLift(double time) {
    	requires(Robot.elevator);
    	setTimeout(time);
    }


    protected void initialize() {
    }

    protected void execute() {
        Robot.elevator.command = "PauseLift";
    	Robot.elevator.setPowerUnsafe(0);
    }

    protected boolean isFinished() {
    	return isTimedOut();
    }

    protected void end() {
    }

    protected void interrupted() {
    }
}