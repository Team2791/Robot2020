package frc.robot.commands.HatchManipulator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Constants;
import frc.robot.commands.DoNothing;
import frc.robot.commands.CargoManipulator.RaiseCargo;

//This command group raises the cargo intake arms, then closes the grabber on the hatch panel maniuplator, then waits for kScorePanelDelayGrabberCloseAndHatchRetraction
//Then retracts the hatch panel manipulator, then waits for kScorePanelDelayHatchRetractionAndAlignerRaise, then raises the hatch manipulator aligner
public class ScorePanelAutomatedRelease extends CommandGroup {
    public ScorePanelAutomatedRelease (){
        addSequential(new RaiseCargo()); //This needs to be in here to ensure that there is no collision
        addSequential(new CloseGrabber());
        addSequential(new DoNothing(), Constants.kScorePanelDelayGrabberCloseAndHatchRetraction);
        addSequential(new RetractHatch());
        addSequential(new DoNothing(), Constants.kScorePanelDelayHatchRetractionAndAlignerRaise);
        addSequential(new RaiseAligner());
    }
}