/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Climb;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.Climber;

public class ReleasePin extends Command {
  private boolean lock, lowerIntake;
  private Timer intakeRetractTimer;
  public ReleasePin(boolean extend, boolean lowerIntake) {
      super("ReleasePin");
      requires(Robot.climber);
      requires(Robot.manipulator);
      lock = extend;
      this.lowerIntake = lowerIntake;
      intakeRetractTimer = new Timer();
  }

  
  protected void initialize() {
    intakeRetractTimer.start();
  }

  protected void execute() {
    if (intakeRetractTimer.get() > 0.25) {
      Robot.climber.setPinExtender(lock);
    }

    if (lowerIntake) {
      if (intakeRetractTimer.get() < 2) {
        Robot.manipulator.setExtended();
      } else {
        Robot.manipulator.setRetracted();
      }
    }
  }
  
  protected boolean isFinished() {
    return intakeRetractTimer.get() > 0.1 && Robot.manipulator.getRetracted();
  }

  protected void end() {
  
    // Robot.climber.setPinExtender(true);
  }

  protected void interrupted() {

  }
}
