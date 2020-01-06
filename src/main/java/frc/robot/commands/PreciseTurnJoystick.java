package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
// import frc.robot.Constants;
import frc.robot.Robot;

public class PreciseTurnJoystick extends Command {
    private Joystick stick;
    private double deadzone;

    public PreciseTurnJoystick(Joystick stick, double deadzone) {
        super("PreciseTurnJoystick");
        requires(Robot.drivetrain);
        this.stick = stick;
        this.deadzone = deadzone;
    }

    public void execute() {
        double turn = stick.getRawAxis(4);

        if(turn < deadzone && turn > -deadzone) {
            turn = 0;
        }

        double left = turn / 2;
        double right = -turn / 2;
        
        Robot.drivetrain.setMotors(left, right);
    }

    public void end() {
        Robot.drivetrain.setMotors(0, 0);
    }

    public boolean isFinished() {
        return false;
    }
}