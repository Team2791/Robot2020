package frc.robot.Autons;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.IntakeToHopper.MoveHopperWall;
import frc.robot.commands.Shooter.OpenHopperPiston;
import frc.robot.commands.Shooter.WallShot;
import frc.robot.commands.Shooter.WallShotHood;

public class ShooterGroupWall extends CommandGroup{

    public ShooterGroupWall(){
        addSequential(new WallShotHood());
        addSequential(new WallShot());
        addSequential(new OpenHopperPiston());
        addSequential(new MoveHopperWall());
    }
}