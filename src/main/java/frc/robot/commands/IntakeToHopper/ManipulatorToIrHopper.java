package frc.robot.commands.IntakeToHopper;

import edu.wpi.first.wpilibj.command.CommandGroup;
// import frc.robot.Robot;
// import frc.robot.commands.IntakeToHopper.*;
import frc.robot.commands.StopHopper;

public class ManipulatorToIrHopper extends CommandGroup{
    public ManipulatorToIrHopper(){
        addSequential(new MoveManipulator());
        addSequential(new IrHopper());
        addSequential(new StopHopper());
    }
}