package frc.robot.commands.HatchManipulator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

//This command drops the aligner of the hatch manipulator and then finishes.
public class DropAligner extends Command {
    public DropAligner(){
        super("DropAligner");
        requires(Robot.hatchManipulator);
    }
    public void execute() {
        Robot.hatchManipulator.setAligner(true);
    }

    public boolean isFinished() {
        return true;
    }
}