package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class StopDrive extends Command {
    public StopDrive() {
        super("StopDrive");
        requires(Robot.drivetrain);
    }

    public void execute() {
        Robot.drivetrain.setMotors(0, 0);
    }

    public void end() {
        Robot.drivetrain.setMotors(0, 0);
    }

    public boolean isFinished() {
        return false;
    }
}