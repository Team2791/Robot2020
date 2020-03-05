package frc.robot.commands.IntakeToHopper;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.IntakeToHopper.MoveManipulator;
import edu.wpi.first.wpilibj.Timer;

public class IrHopper extends Command {
  public int count;
  public IrHopper() {
    super("IrHopper");
    requires(Robot.hopper);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    // count = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
      Robot.hopper.loadingWithIR();
      
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override

  protected boolean isFinished() {
    return !Robot.hopper.isBall() || Robot.hopper.isUpperSensorTripped();
    
    
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    // count++;
    Robot.hopper.setHopper(0, 0);
    SmartDashboard.putBoolean("Ir Hopper Running", false);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }

}
