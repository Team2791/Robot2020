package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class SetLimit extends Command {
    private int limits;

    public SetLimit(int limit) {
        this.limits = limit;
    }

    public void execute() {
        Robot.drivetrain.setLimit(limits);
    }

    public boolean isFinished() {
        return true;
    }

    @Override
    protected void end(){}
}