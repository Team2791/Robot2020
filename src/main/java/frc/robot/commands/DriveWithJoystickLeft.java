package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class DriveWithJoystickLeft extends Command {
    private Joystick stick;
    private boolean isSquaredTurn;
    private double deadzone;

    public DriveWithJoystickLeft() {
        super("DriveWithJoystickLeft");
        requires(Robot.drivetrain);
    }

    public void execute() {
        System.out.println("This is left Neo");
        Robot.drivetrain.setMotors(-1);
    }

    public void end() {
        Robot.drivetrain.setMotors(-1);
    }

    public boolean isFinished() {
        return false;
    }
}