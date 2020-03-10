package frc.robot.commands.IntakeToHopper;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class ReverseUntilBeamBreak extends Command {
  public ReverseUntilBeamBreak() {
    super("ReverseUntilBeamBreak");
    requires(Robot.hopper);
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(!Robot.hopper.beamBreak1.get()){
        Robot.hopper.setHopper(0, 0);
      }
    else{
        Robot.hopper.setHopper(Constants.REVERSE_HOPPER, Constants.REVERSE_HOPPER);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return true;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    // Robot.hopper.setHopper(0, 0);
  }

  @Override
  protected void interrupted() {
  }
}