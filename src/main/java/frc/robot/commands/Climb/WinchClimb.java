/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Climb;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class WinchClimb extends Command {
  private boolean direction;
  private Timer waitTimer;
  public WinchClimb(boolean down) {
    super("WinchClimb");
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    direction = down;
    waitTimer = new Timer();
    requires(Robot.climber);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    waitTimer.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    Robot.climber.setExtended();
    if (waitTimer.get() > 0.25) {
      if (direction){
        Robot.climber.setWinchOutput(-1);
      }
      else if (!direction){
        Robot.climber.setWinchOutput(1);
      }
    }
    Robot.manipulator.setRetracted();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return waitTimer.get() > 0.27;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    // Robot.climber.setWinchOutput(0);

  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
