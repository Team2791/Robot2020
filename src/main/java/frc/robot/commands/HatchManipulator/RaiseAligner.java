package frc.robot.commands.HatchManipulator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

//This command raises the aligner for the hatch panel manipulator, and then finishes
public class RaiseAligner extends Command {
    public RaiseAligner(){
        super("RaiseAligner");
        requires(Robot.hatchManipulator);
    }
    public void execute() {
        Robot.hatchManipulator.setAligner(false);
    }

    public boolean isFinished() {
        return true;
    }
}