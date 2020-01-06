package frc.robot.commands.HatchManipulator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

//This command retracts the hatch manipulator inwards to the robot, and then finishes;
public class RetractHatch extends Command {
    public RetractHatch(){
        super("RetractHatch");
        requires(Robot.hatchManipulator);
    }
    public void execute() {
        Robot.hatchManipulator.setExtender(false);
    }

    public boolean isFinished() {
        return true;
    }
}