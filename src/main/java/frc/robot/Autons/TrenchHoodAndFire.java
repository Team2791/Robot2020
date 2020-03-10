package frc.robot.Autons;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.IntakeToHopper.*;

import frc.robot.commands.Shooter.LongShotHood;
import frc.robot.commands.Shooter.OpenHopperPiston;

public class TrenchHoodAndFire extends CommandGroup{

    public TrenchHoodAndFire(){
        addSequential(new LongShotHood(), 0.21);
        addSequential(new OpenPistonsAndMoveHopper());
        // addSequential(new OpenHopperPiston());
        // addSequential(new MoveHopperLong());
    }
}