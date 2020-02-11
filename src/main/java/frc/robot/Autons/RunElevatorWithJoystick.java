/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.Autons;

import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.util.Util;

public class RunElevatorWithJoystick extends Command {
  Button elevator_button;
  public RunElevatorWithJoystick(Button elevator_button) {
    requires(Robot.elevator);
    this.elevator_button = elevator_button;
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
    double output = Util.deadzone(Constants.DEADZONE, OI.operatorStick.getRawAxis(1), 1.0) * Constants.MANUAL_POWER * -1;
    if(Robot.oi.operatorLS.get()) {
      Robot.elevator.setElevator(output); //THIS SHOULD BE SET MANUAL POWER
      
  }
  
  
  
  else{

  }
}

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return !elevator_button.get();
    }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
