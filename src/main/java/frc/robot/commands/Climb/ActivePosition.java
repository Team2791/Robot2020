/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.Climb;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.Climber;
import edu.wpi.first.wpilibj.GyroBase;
import frc.robot.Constants;

public class ActivePosition extends Command {
  private GyroBase climbGyro;
  double angle, turn;
  public ActivePosition() {
    super("ActivePosition");
   // requires(Robot.Climber);
  }

  public double getGyroAngle() {

    angle=climbGyro.getAngle();
      return climbGyro.getAngle();
       

}

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
     angle=climbGyro.getAngle();
     
     
      if(angle==0){
      turn=0;
      
     }
      else if(angle/360==0){  

      turn=0;
    }
      else if(angle>270&&angle<360){

        turn=Constants.kSelfClimbGoRight;
      }

      else if(angle<90&&angle>0){ 
        turn=Constants.kSelfClimbGoLeft;
      }


      Robot.climber.setSelfClimbOutput(turn);
   }
    

  

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.climber.setSelfClimbOutput(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
