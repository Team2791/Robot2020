package frc.robot.Autons;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.IntakeToHopper.*;

import frc.robot.commands.Shooter.WallShotHood;
import frc.robot.commands.Shooter.OpenHopperPiston;

public class WallHoodAndFire extends CommandGroup{

    public WallHoodAndFire(){
        addSequential(new WallShotHood(), 0.2);
        addSequential(new OpenPistonsAndMoveHopper());
        // addSequential(new OpenHopperPiston());
        // addSequential(new MoveHopperLong());
    }
}