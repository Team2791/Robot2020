package frc.robot.commands.Lifter;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class DriveLifterWheel extends Command {
  public DriveLifterWheel() {
    super("DriveLifterWheel");
    requires(Robot.lifters);
  }

  protected void execute() {
      Robot.lifters.driveMotor(Constants.kLifterDrivePower);
  }

  protected boolean isFinished() {
    return false;
  }

  protected void end() {
    Robot.lifters.driveMotor(0);
  }
}
