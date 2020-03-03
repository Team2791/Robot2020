/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.IntakeToHopper;

import java.util.concurrent.DelayQueue;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class FixHopperSpacing extends Command {
  Timer timeToFixSpace;
  public FixHopperSpacing() {
    super("FixHopperSpacing");
    requires(Robot.hopper);
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
    if(!Robot.hopper.isUpperSensorTripped()){
        Robot.hopper.setHopper(Constants.HOPPER_LOADING_HORIZONTAL_OUTPUT, Constants.HOPPER_LOADING_VERTICAL_OUTPUT);
    }
    else if(Robot.hopper.isUpperSensorTripped()){
        Robot.hopper.setHopper(Constants.HOPPER_LOADING_HORIZONTAL_OUTPUT, 0);
        Timer.delay(Constants.fixSpaceTime);
        Robot.hopper.setHopper(-Constants.HOPPER_LONG_HORIZONTAL_OUTPUT, -Constants.HOPPER_VERTICAL_OUTPUT);
    }
    //Pulls balls as far up as possible
    //only runs the horizontal belt (not the vertical) for some time to decrease the space between balls 2 and 3
    //pulls balls back to first ir sensor
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return Robot.hopper.isBall();
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.hopper.setHopper(0, 0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
