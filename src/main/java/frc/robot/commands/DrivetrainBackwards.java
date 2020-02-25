package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class DrivetrainBackwards extends Command {
    private Joystick stick;
    private boolean isSquaredTurn;
    private double deadzone;

    public DrivetrainBackwards() {
        super("DrivetrainBackwards");
        requires(Robot.drivetrain);
    }

    public void execute() {
        Robot.drivetrain.setMotors(-1,-1);
    }

    public void end() {
        Robot.drivetrain.setMotors(-1,-1);
    }

    public boolean isFinished() {
        return false;
    }
}