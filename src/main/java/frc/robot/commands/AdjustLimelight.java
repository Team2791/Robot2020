package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
/*
public class AdjustLimelight extends Command {
    private Joystick stick;
    private boolean isSquaredTurn;
    private double deadzone;

    public AdjustLimelight() {
        super("DriveWithJoystick");
        requires(Robot.drivetrain);
    }

    public void execute() {
        double thrust = 0;
        if(stick.getRawButton(6)) {
            thrust = Constants.kCreep;
        }
        else if(stick.getRawButton(5)) {
            thrust = -Constants.kCreep;
        } else {
            thrust = stick.getRawAxis(3) - stick.getRawAxis(2);
            if(Math.abs(thrust) < deadzone) {
                thrust = 0;
            }
        }
        
        double turn = stick.getRawAxis(0);

        if(turn < deadzone && turn > -deadzone) {
            turn = 0;
        }

        if(isSquaredTurn) {
            turn *= Math.abs(turn);
        }

        if(stick.getRawButton(2)) {
            turn /= 3;
        }

        // This causes the robot to crash.  It is the ideal solution to wheelies.
        // if(Math.abs(turn) > 0.05) {
        //     Robot.drivetrain.setLimit(Constants.kNeoAmpLimitTurn);
        // }
        // else {
        //     Robot.drivetrain.setLimit(Constants.kNeoAmpLimit);
        // }

        double left = Math.max(Math.min(thrust + turn, 1), -1);
        double right = Math.max(Math.min(thrust - turn, 1), -1);
        
        Robot.drivetrain.setMotors(left, right);
    }

    public void end() {
        Robot.drivetrain.setMotors(0, 0);
    }

    public boolean isFinished() {
        return false;
    }
}

*/ //heh nice