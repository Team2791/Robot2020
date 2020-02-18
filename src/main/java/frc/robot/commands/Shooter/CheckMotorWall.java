/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class CheckMotorWall extends Command {
  public CheckMotorWall() {
    super("CheckMotorWall");
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
    if(Robot.shooter.checkWheelSpeed_Wall() != true) {
        Robot.shooter.setShooter(Constants.SHOOTER_OUTPUT_WALL);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if(Robot.shooter.checkWheelSpeed_Wall() == true) {
        return true; 
    }
    return false; 
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    // Robot.shooter.setShooter(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
