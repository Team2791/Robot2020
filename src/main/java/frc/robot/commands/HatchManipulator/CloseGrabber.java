package frc.robot.commands.HatchManipulator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

//This command closes the grabber of the hatch manipulator and then ends.
public class CloseGrabber extends Command {
    public CloseGrabber(){
        super("CloseGrabber");
        requires(Robot.hatchManipulator);
    }
    public void execute() {
        Robot.hatchManipulator.setGrabber(false);
    }

    public boolean isFinished() {
        return true;
    }
}