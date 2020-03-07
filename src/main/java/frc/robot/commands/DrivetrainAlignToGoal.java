/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.controller.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Limelight.CamMode;
import frc.robot.subsystems.Limelight.LedMode;

public class DrivetrainAlignToGoal extends Command {

  PIDController pid;

  public DrivetrainAlignToGoal() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.drivetrain);
    requires(Robot.limelight);
    pid = new PIDController(Constants.DrivekP, Constants.DrivekI, Constants.DrivekD);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.limelight.setCamMode(CamMode.VISION_CAM);
    Robot.limelight.setLedMode(LedMode.PIPELINE);
    pid.setSetpoint(Constants.LimelightSetpoint);
    pid.setTolerance(0.1);  //0.3 old
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double output = pid.calculate(Robot.limelight.getHorizontalOffset());
    Robot.drivetrain.setMotors(-output, output);
    SmartDashboard.putBoolean("Drivetrain Align Complete", false);
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
    SmartDashboard.putBoolean("Drivetrain Align Complete", true);
    Robot.drivetrain.setMotors(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
