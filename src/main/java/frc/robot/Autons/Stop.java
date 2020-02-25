package frc.robot.Autons;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.MoveHopperWall;
import frc.robot.commands.StopHopper;
import frc.robot.commands.Shooter.CloseHopperPiston;
import frc.robot.commands.Shooter.OpenHopperPiston;
//import frc.robot.commands.Shooter.CheckWheelSpeed;
//import frc.robot.commands.Shooter.RetractHood;
import frc.robot.commands.Shooter.StopShooter;
import frc.robot.subsystems.Shooter;

public class Stop extends CommandGroup {

    public Stop() {
        addSequential(new StopShooter());
        addSequential(new CloseHopperPiston());
        addSequential(new StopHopper());
    }
}