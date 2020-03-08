package frc.robot.Autons;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.IntakeToHopper.*;
import frc.robot.commands.Shooter.OpenHopperPiston;

public class ManipulatorToLimitHopper extends CommandGroup{
    public ManipulatorToLimitHopper(){
        addSequential(new MoveManipulator());
        addSequential(new BeamBreak());
        //addSequential(new OpenHopperPiston());
    }
}