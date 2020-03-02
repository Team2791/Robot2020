/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.IntakeToHopper;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class OpenPistonsAndMoveHopper extends Command {
  Timer timeForHopper;
  public OpenPistonsAndMoveHopper() {
    super("OpenPistonsAndMoveHopper");
    requires(Robot.hopper);
    timeForHopper = new Timer();
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    timeForHopper.start();
    Robot.hopper.setExtended();

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(timeForHopper.get() > .25){
      Robot.hopper.setHopper(Constants.HOPPER_WALL_HORIZONTAL_OUTPUT, Constants.HOPPER_VERTICAL_OUTPUT);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.hopper.setHopper(0, 0);
    Robot.hopper.setRetracted();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
