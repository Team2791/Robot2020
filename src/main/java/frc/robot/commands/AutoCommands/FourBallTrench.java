package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Robot;


public class FourBallTrench extends Command {

  PIDController pid;
  double setpoint;

  public FourBallTrench() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.drivetrain);
    pid = new PIDController(Constants.DrivekP, Constants.DrivekI, Constants.DrivekD);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    setpoint = Constants.TrenchBallOneDist;
    setpoint += Robot.drivetrain.getAverageDistance();
    pid.setSetpoint(setpoint);
    pid.setTolerance(0.3);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double output = pid.calculate(Robot.drivetrain.getAverageDistance());
    Robot.drivetrain.setMotors(output, output);
    SmartDashboard.putBoolean("Drivetrain Distance Complete", false);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return pid.atSetpoint();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    // Robot.limelight.setCamMode(CamMode.DRIVER_CAM);
    SmartDashboard.putBoolean("Drivetrain Distance Complete", true);
    Robot.drivetrain.setMotors(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
