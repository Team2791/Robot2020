package frc.robot.Autons;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.MoveHopperLong;

import frc.robot.commands.Shooter.LongShot;
import frc.robot.commands.Shooter.LongShotHood;
import frc.robot.commands.Shooter.OpenHopperPiston;
//import frc.robot.commands.Shooter.CheckWheelSpeed;
//import frc.robot.commands.Shooter.RetractHood;
import frc.robot.subsystems.Shooter;

public class ShooterGroupLong extends CommandGroup{

    public ShooterGroupLong(){
        addSequential(new LongShotHood());
        addSequential(new LongShot(), 5.0);
        addSequential(new OpenHopperPiston());
        addSequential(new MoveHopperLong());
        //addSequential(new OpenHopperPiston());
        //addSequential(new MoveHopperLong());
    }
}