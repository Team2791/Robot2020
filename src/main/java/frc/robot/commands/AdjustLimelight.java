package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;

public class AdjustLimelight extends Command {

    public double TURN_FACTOR;

    public AdjustLimelight() {
        super("AdjustLimelight");
        TURN_FACTOR = 0.3;
    }

    public void execute() {
        double thrust = 0;
        
        double turn = Robot.limelight.getHorizontalOffset() * TURN_FACTOR;
        
        double left = Math.max(Math.min(thrust + turn, 1), -1);
        double right = Math.max(Math.min(thrust - turn, 1), -1);
        
        Robot.drivetrain.setLeftRightMotorOutputs(left, right);
    }

    public void end() {
        Robot.drivetrain.setLeftRightMotorOutputs(0, 0);
    }

    public boolean isFinished() {
        return false;
    }
}