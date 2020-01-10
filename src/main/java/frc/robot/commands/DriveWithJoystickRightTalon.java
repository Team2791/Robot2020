package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class DriveWithJoystickRightTalon extends Command {
    private Joystick stick;
    private boolean isSquaredTurn;
    private double deadzone;

    public DriveWithJoystickRightTalon() {
        super("DriveWithJoystickRightTalon");
        requires(Robot.drivetrain);
    }

    public void execute() {
    
        
        Robot.drivetrain.setRightTalon(-1);
    }

    public void end() {
        Robot.drivetrain.setRightTalon(0);
    }

    public boolean isFinished() {
        return false;
    }
}