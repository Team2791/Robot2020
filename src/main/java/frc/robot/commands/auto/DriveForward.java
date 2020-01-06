package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DriveForward extends Command {
    private double speed;

    public DriveForward(double speed) {
        super("DriveForward");
        requires(Robot.drivetrain);
        this.speed = speed;
    }

    public void execute() {
        Robot.drivetrain.setMotors(speed, speed);
    }

    public boolean isFinished() {
        return false;
    }

    @Override
    protected void end() {
        Robot.drivetrain.setMotors(0, 0);
    }
}