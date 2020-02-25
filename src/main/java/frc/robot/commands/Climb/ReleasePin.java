/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Climb;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Climber;

public class ReleasePin extends Command {
  public ReleasePin() {
      super("ReleasePin");
      requires(Robot.climber);
  }

  
  protected void initialize() {
  
  }

  protected void execute() {
    Robot.climber.setPinExtender(false);
  }
  
  protected boolean isFinished() {
    return false;
  }

  protected void end() {
  
    Robot.climber.setPinExtender(true);
  }

  protected void interrupted() {

  }
}
