package frc.robot.Autons;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.MoveHopper;
import frc.robot.commands.StopHopper;
//import frc.robot.commands.Shooter.CheckWheelSpeed;
//import frc.robot.commands.Shooter.RetractHood;
import frc.robot.commands.Shooter.RetractHopperPiston;
import frc.robot.commands.Shooter.StopShooter;
import frc.robot.subsystems.Shooter;

public class StopShooterGroup extends CommandGroup{

    //in theory this redoes everything the shooterGroup() command group changed
    public StopShooterGroup(){
        //addParallel(new RetractHood()); 
        addParallel(new StopShooter());
        addParallel(new StopHopper());
        addParallel(new RetractHopperPiston());

        
    }
}