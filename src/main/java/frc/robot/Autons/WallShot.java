package frc.robot.Autons;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.MoveHopperWall;
import frc.robot.commands.StopHopper;
import frc.robot.commands.Shooter.CheckHoodLong;
import frc.robot.commands.Shooter.CheckHoodWall;
import frc.robot.commands.Shooter.CheckMotorLong;
import frc.robot.commands.Shooter.CheckMotorWall;
import frc.robot.commands.Shooter.CloseHopperPiston;
import frc.robot.commands.Shooter.OpenHopperPiston;
//import frc.robot.commands.Shooter.CheckWheelSpeed;
//import frc.robot.commands.Shooter.RetractHood;
import frc.robot.commands.Shooter.StopShooter;
import frc.robot.subsystems.Shooter;

public class WallShot extends CommandGroup{

    public WallShot(){
        addSequential(new CheckHoodLong());
        addSequential(new CheckMotorLong());
        addParallel(new OpenHopperPiston());
        addParallel(new MoveHopperWall());
    }
}