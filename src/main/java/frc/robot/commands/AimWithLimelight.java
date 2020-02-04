package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AimWithLimelight extends CommandGroup {
  public AimWithLimelight() {
    if(Robot.limelight.limelightAimInner()){
      new AdjustLimelightInner();
    }
    else{
      new AdjustLimelight();
    }
        
  }
}

