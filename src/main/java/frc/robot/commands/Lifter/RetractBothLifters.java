package frc.robot.commands.Lifter;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class RetractBothLifters extends Command {
    private double output;
    public RetractBothLifters(double output) {
        super("RetractBothLifters");
        requires(Robot.lifters);
        this.output = output;
    }

    public void execute() {
        Robot.lifters.stopBackFollow();
        Robot.lifters.ExtendBoth(output);
    }

    public void end() {
        Robot.lifters.startBackFollow();
        Robot.lifters.extendFront(0);
        Robot.lifters.extendBack(0);
    }

    public boolean isFinished() {
        return Robot.lifters.isFrontRetracted() && Robot.lifters.isBackRetracted();
    }
}