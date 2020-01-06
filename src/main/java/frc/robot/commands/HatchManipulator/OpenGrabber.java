package frc.robot.commands.HatchManipulator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

//This command opens the grabber on the hatch panel manipulator, and then finishes.
public class OpenGrabber extends Command {

    public OpenGrabber(){
        super("OpenGrabber");
        requires(Robot.hatchManipulator);
    }
    public void execute() {
        Robot.hatchManipulator.setGrabber(true);
    }

    public boolean isFinished() {
        return true;
    }
}