package frc.robot.Autons;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.MoveHopper;
import frc.robot.commands.Shooter.CheckHoodLong;
import frc.robot.commands.Shooter.CheckHoodWall;
import frc.robot.commands.Shooter.CheckMotorLong;
import frc.robot.commands.Shooter.CheckMotorWall;
import frc.robot.commands.Shooter.OpenHopperPiston;
//import frc.robot.commands.Shooter.CheckWheelSpeed;
//import frc.robot.commands.Shooter.RetractHood;
import frc.robot.commands.Shooter.RetractHopperPiston;
import frc.robot.subsystems.Shooter;

public class ShooterGroupLong extends CommandGroup{

    public ShooterGroupLong(){
        addSequential(new CheckHoodLong());
        addSequential(new CheckMotorLong());
        addSequential(new OpenHopperPiston());
        addSequential(new MoveHopper());
    }
}