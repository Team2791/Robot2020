package frc.robot.Autons;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.MoveHopper;
import frc.robot.commands.StopHopper;
import frc.robot.commands.Shooter.CheckHoodWall;
import frc.robot.commands.Shooter.CheckMotorWall;
import frc.robot.commands.Shooter.CloseHopperPiston;
import frc.robot.commands.Shooter.OpenHopperPiston;
//import frc.robot.commands.Shooter.CheckWheelSpeed;
//import frc.robot.commands.Shooter.RetractHood;
import frc.robot.commands.Shooter.StopShooter;
import frc.robot.subsystems.Shooter;

public class StopShooterGroupWall extends CommandGroup{

    public StopShooterGroupWall(){
        addParallel(new StopShooter());
        addParallel(new StopHopper());
        addParallel(new CloseHopperPiston());
    }
}