package frc.robot.Autons;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.IntakeToHopper.*;

import frc.robot.commands.Shooter.LongShot;
import frc.robot.commands.Shooter.LongShotHood;
import frc.robot.commands.Shooter.OpenHopperPiston;

public class ShooterGroupLong extends CommandGroup{

    public ShooterGroupLong(){
        addSequential(new LongShotHood());
        addSequential(new LongShot(), 2.0);
        addSequential(new OpenHopperPiston());
        addSequential(new MoveHopperLong());
    }
}