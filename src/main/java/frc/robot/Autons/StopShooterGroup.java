package frc.robot.Autons;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.IntakeToHopper.StopHopper;
import frc.robot.commands.Shooter.CloseHopperPiston;
import frc.robot.commands.Shooter.StopShooter;

public class StopShooterGroup extends CommandGroup {

    public StopShooterGroup() {
        addSequential(new StopShooter());
        addSequential(new CloseHopperPiston());
        addSequential(new StopHopper());
    }
}