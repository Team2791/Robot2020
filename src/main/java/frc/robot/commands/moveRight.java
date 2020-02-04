package frc.robot.commands;

import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;


//This command does nothing and is used as a delay
public class moveRight extends Command {
    public moveRight() {
        super("moveRight");
    }

    public void execute() {
        Robot.drivetrain.setMotors(0.7, 0.5);
    }

    public void end() {
        Robot.drivetrain.setMotors(0.0, 0.0);
    }

    public boolean isFinished() {
        return false;
    }
}