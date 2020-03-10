package frc.robot.commands.IntakeToHopper;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.IntakeToHopper.MoveManipulator;
import edu.wpi.first.wpilibj.Timer;

public class DoubleBeamBreakHopper extends Command {
  public boolean firstBeamBroken = false;
  public DoubleBeamBreakHopper() {
    super("DoubleBeamBreakHopper");
    requires(Robot.hopper);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

      SmartDashboard.putBoolean("Beam Break Hopper Running", true);
      if(!Robot.hopper.beamBreak1.get()){
        Robot.hopper.setHopper(Constants.HOPPER_LOADING_HORIZONTAL_OUTPUT,
        Constants.HOPPER_LOADING_VERTICAL_OUTPUT);
        firstBeamBroken = true;
      }
      if(!Robot.hopper.beamBreak2.get() && firstBeamBroken){
          Robot.hopper.setHopper(0,0);
          firstBeamBroken = false;
    }

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override

  protected boolean isFinished() {
    // if (Robot.hopper.isHopperFull()==true){
    //   return true;
    // }
    // else{
    //   return false;
    // }
    return Robot.hopper.isUpperSensorTripped();
    
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    // count++;
    Robot.hopper.setHopper(0, 0);
    SmartDashboard.putBoolean("Ir Hopper Running", false);
    // Robot.hopper.counter.reset();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }

}
