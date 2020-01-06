package frc.robot.commands.Lifter;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class EStopLifters extends Command {
    public EStopLifters() {
        super("EStopLifters");
        requires(Robot.lifters);
    }

    public void execute() {
        Robot.lifters.extendFront(0);
        Robot.lifters.extendBack(0);
    }

    public void end() {
        Robot.lifters.extendFront(0);
        Robot.lifters.extendBack(0);
    }

    public boolean isFinished() {
        return true;
    }
}