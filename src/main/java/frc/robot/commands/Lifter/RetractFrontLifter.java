package frc.robot.commands.Lifter;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RetractFrontLifter extends Command {
    private double output;
    public RetractFrontLifter(double output) {
        super("RetractFrontLifter");
        requires(Robot.lifters);
        this.output = output;
    }

    public void execute() {
        Robot.lifters.extendFront(output);
    }

    public void end() {
        Robot.lifters.extendFront(0);
    }

    public boolean isFinished() {
        return Robot.lifters.isFrontRetracted();
    }
}