/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class Turn45WithAccelerometer extends Command {
  
  public Turn45WithAccelerometer() {
    super("Turn45WithAccelerometer");
    requires(Robot.drivetrain);
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

    if(Constants.ANGLE_SETPOINT > Robot.drivetrain.getAngle()){
      System.out.println("Angle too low");
      double error = Constants.ANGLE_SETPOINT - Robot.drivetrain.imu.getAngle();
      double turning = error*Constants.P;
      Robot.drivetrain.setMotors(-turning, turning);
       if(error> -10 && error < 0){
         Robot.drivetrain.setMotors(- Constants.DEADZONE_SPEED, Constants.DEADZONE_SPEED);
        } else if( -0.5 < error && error < 0.5) {
          Robot.drivetrain.setMotors(0, 0);
          
        } else {
         
       }
       if(error < 10 && error > 0){
        Robot.drivetrain.setMotors( Constants.DEADZONE_SPEED, - Constants.DEADZONE_SPEED);
      } else if( -0.5 < error && error < 0.5) {
        Robot.drivetrain.setMotors(0, 0);
        
      } else {
        
      }

    } else if (Constants.ANGLE_SETPOINT < Robot.drivetrain.getAngle()){
      System.out.println("Angle too high");
      double error = Constants.ANGLE_SETPOINT - Robot.drivetrain.getAngle();
      double turning = error*Constants.P;
      Robot.drivetrain.setMotors(-turning, turning);
      if(error> -10 && error < 0){
        Robot.drivetrain.setMotors(- Constants.DEADZONE_SPEED, Constants.DEADZONE_SPEED);
      } else if( -0.5 < error && error < 0.5) {
        Robot.drivetrain.setMotors(0, 0);
        
      }
      if(error < 10 && error > 0){
       Robot.drivetrain.setMotors( Constants.DEADZONE_SPEED, - Constants.DEADZONE_SPEED);
     } else if ( -0.5 < error && error < 0.5) {
       Robot.drivetrain.setMotors(0, 0);
     }else{

     }

    } else if (Constants.ANGLE_SETPOINT == Robot.drivetrain.getAngle()){
      System.out.println("Just right");
      double turningValue = 0;
      final double left = turningValue;
      final double right = - turningValue;
      Robot.drivetrain.setMotors(0, 0);

    } else{
      System.out.println("Its fine");
    }
    }

 
    // final double left = Math.max(Math.min(thrust + turningValue, 1), -1);
    // final double right = Math.max(Math.min(thrust - turningValue, 1), -1);
        

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.drivetrain.setMotors(0,0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
