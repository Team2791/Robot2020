package frc.robot.Autons;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.IntakeToHopper.*;

public class ManipulatorToIrHopper extends CommandGroup{
    public ManipulatorToIrHopper(){
        addSequential(new MoveManipulator());
        addSequential(new IrHopper());
    }
}