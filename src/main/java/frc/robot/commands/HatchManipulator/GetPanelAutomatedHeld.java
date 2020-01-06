package frc.robot.commands.HatchManipulator;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.CargoManipulator.RaiseCargo;

//This command group raises the cargo intake arms, then drops the hatch panel aligner, then closes the hatch manipulator grabbers, and then extends the hatch mainpulator outwards.
public class GetPanelAutomatedHeld extends CommandGroup {
    public GetPanelAutomatedHeld (){
        addSequential(new RaiseCargo()); //This needs to be in here to ensure that there is no collision
        addSequential(new DropAligner());
        addSequential(new CloseGrabber());
        addSequential(new ExtendHatch());
    }
}