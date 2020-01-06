package frc.robot.commands.HatchManipulator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

//This command extends the hatch arm and then finishes.
public class ExtendHatch extends Command {
    public ExtendHatch(){
        super("ExtendHatch");
        requires(Robot.hatchManipulator);
    }
    public void execute() {
        Robot.hatchManipulator.setExtender(true);
    }

    public boolean isFinished() {
        return true;
    }
}
