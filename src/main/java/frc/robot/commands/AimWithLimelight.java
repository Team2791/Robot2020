package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.CommandGroup;

public class AimWithLimelight extends CommandGroup {
  public AimWithLimelight() {
    addSequential(new AdjustLimelight(true));
    addSequential(new AdjustLimelightInner(true)); 
  }
}

