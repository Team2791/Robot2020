package frc.robot.commands.Lifter;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RetractBackLifter extends Command {
    private double output;
    public RetractBackLifter(double output) {
        super("RetractBackLifter");
        requires(Robot.lifters);
        this.output = output;
    }

    public void execute() {
        Robot.lifters.stopBackFollow();
        Robot.lifters.extendBack(output);
    }

    public void end() {
        Robot.lifters.extendBack(0);
    }

    public boolean isFinished() {
        return Robot.lifters.isBackRetracted();
    }
}