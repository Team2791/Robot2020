package frc.robot.Autons;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.MoveHopperWall;
import frc.robot.commands.Shooter.CheckHoodWall;
import frc.robot.commands.Shooter.CheckMotorWall;
import frc.robot.commands.Shooter.OpenHopperPiston;
//import frc.robot.commands.Shooter.CheckWheelSpeed;
//import frc.robot.commands.Shooter.RetractHood;
import frc.robot.subsystems.Shooter;

public class ShooterGroupWall extends CommandGroup{

    public ShooterGroupWall(){
        // addSequential(new CheckHoodWall());
        addSequential(new CheckMotorWall());
        // addSequential(new OpenHopperPiston());
        // addSequential(new MoveHopperWall());
    }
}