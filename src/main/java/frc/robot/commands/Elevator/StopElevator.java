package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class StopElevator extends Command {

    public StopElevator() {
    	requires(Robot.elevator);
    }

    protected void initialize() {

    }

    @Override
    protected void execute() {
        Robot.elevator.setPowerUnsafe(0);
        Robot.elevator.setBreak(true);
        Robot.elevator.command = "StopElevator";
}
    
    @Override
    public boolean isFinished() {
        return true;
    }
    @Override
    protected void end () {
        Robot.elevator.setPowerUnsafe(0);
        Robot.elevator.setBreak(true);

    }
    protected void interrupted () {

    }
}