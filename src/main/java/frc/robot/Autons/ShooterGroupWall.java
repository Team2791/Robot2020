package frc.robot.Autons;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.MoveHopperWall;
import frc.robot.commands.Shooter.OpenHopperPiston;
import frc.robot.commands.Shooter.WallShot;
import frc.robot.commands.Shooter.WallShotHood;
//import frc.robot.commands.Shooter.CheckWheelSpeed;
//import frc.robot.commands.Shooter.RetractHood;
import frc.robot.subsystems.Shooter;

public class ShooterGroupWall extends CommandGroup{

    public ShooterGroupWall(){
        addSequential(new WallShotHood());
        addSequential(new WallShot());
        addSequential(new OpenHopperPiston());
        addSequential(new MoveHopperWall());
    }
}