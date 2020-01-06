package frc.robot.commands.HatchManipulator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.CargoManipulator.RaiseCargo;

//This command group raises the cargo intake arms, then opens the hatch manipulator grabbers, then drops the hatch manipulator aligners
//And then extends the hatch manipulator outwards towards the robot.
public class ScorePanelAutomatedHeld extends CommandGroup {
    public ScorePanelAutomatedHeld (){
        addSequential(new RaiseCargo()); //This needs to be in here to ensure that there is no collision
        addSequential(new OpenGrabber());
        addSequential(new DropAligner());
        addSequential(new ExtendHatch());
    }
}