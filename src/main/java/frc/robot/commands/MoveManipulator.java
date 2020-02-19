package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class MoveManipulator extends Command {
    public MoveManipulator() {
    super("MoveManipulator");
    requires(Robot.manipulator);
        //Use requires() here to declare subsystem dependencies
        //eg. requires(chassis);
    }

    //Called just before this Command runs the first time
    @Override
    protected void initialize() {
    
    }

    //Called repeatedly when this Command is scheduled to run 
    @Override
    protected void execute() {
        Robot.manipulator.setRetracted(true);
        Robot.manipulator.setManipulator(0);
    }

    //Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    //Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.manipulator.setManipulator(0); 
    }

    //Called when another command which requires one or more of the same 
    //subsystems is scheduled to run

    @Override
    protected void interrupted() {
        //Yes
    }
}
