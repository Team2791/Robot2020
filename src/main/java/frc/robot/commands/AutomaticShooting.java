package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;5
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.util.Limelight;

public class AutomaticShooting extends Command {
  public AutomaticShooting() {
    super("AutomaticShooting");
    requires(Robot.shooter);

    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
    Robot.shooter.setShooter(idealVelocity(2.0, Constants.WALL_SHOT, Constants.HEIGHT));
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.shooter.setShooter(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
