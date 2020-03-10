package frc.robot.commands.tempTest;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class testExtendClimber extends Command {
    public testExtendClimber() {
    super("testExtendClimber");
    requires(Robot.manipulator);
    requires(Robot.hopper);
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
        Robot.climber.setExtended();
        
    }

    //Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return true;
    }

    //Called once after isFinished returns true
    @Override
    protected void end() {
    }

    //Called when another command which requires one or more of the same 
    //subsystems is scheduled to run

    @Override
    protected void interrupted() {
        //Yes
    }
}
