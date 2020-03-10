package frc.robot.commands.IntakeToHopper;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class ReverseHopperAtVoltage extends Command {
    Timer reverseTime;
    private boolean reverse = false;
  public ReverseHopperAtVoltage() {
    super("ReverseHopperAtVoltage");
    requires(Robot.hopper);
    reverseTime = new Timer();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
      if(Robot.hopper.getHorizontalCurrent() > Constants.jamCurrent){
          reverseTime.start();
          reverse = true;
      }
    if(reverse && reverseTime.get() < Constants.reverseTime){
        Robot.hopper.setHopper(Constants.REVERSE_HOPPER, Constants.REVERSE_HOPPER);
    }
    else{
        Robot.hopper.setHopper(0, 0);
        reverseTime.stop();
        reverseTime.reset();
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return !reverse;
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