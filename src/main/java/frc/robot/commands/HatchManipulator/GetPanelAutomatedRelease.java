package frc.robot.commands.HatchManipulator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.DoNothing;
import frc.robot.Constants;
import frc.robot.commands.CargoManipulator.RaiseCargo;
//This command group raises the cargo intake arms, then opens the hatch panel grabbers, then waits for kGetPanelAutomatedReleaseRetractionDelay seconds,
//Then retracts the hatch manipulator inwards, then delays kGetPanelAutomatedReleaseAlignerRetractionDelay seconds, then raises the hatch panel aligner
public class GetPanelAutomatedRelease extends CommandGroup {
    public GetPanelAutomatedRelease() {
        addSequential(new RaiseCargo()); //This needs to be in here to ensure that there is no collision
        addSequential(new OpenGrabber());
        addSequential(new DoNothing(), Constants.kGetPanelAutomatedReleaseRetractionDelay);
        addSequential(new RetractHatch());
        addSequential(new DoNothing(), Constants.kGetPanelAutomatedReleaseAlignerRetractionDelay);
        addSequential(new RaiseAligner());
    }
}