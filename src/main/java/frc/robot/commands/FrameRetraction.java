package frc.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.HatchManipulator.RaiseAligner;
import frc.robot.commands.HatchManipulator.RetractHatch;
import frc.robot.commands.CargoManipulator.RaiseCargo;

//This command group raises the cargo intake arms
public class FrameRetraction extends CommandGroup {
    public FrameRetraction() {
        addSequential(new RaiseCargo());
        addSequential(new RaiseAligner());
        addSequential(new RetractHatch());
    }
}