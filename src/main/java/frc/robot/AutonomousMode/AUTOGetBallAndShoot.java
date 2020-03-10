package frc.robot.AutonomousMode;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Autons.TrenchHoodAndFire;
import frc.robot.commands.AutoCommands.*;
import frc.robot.commands.DrivetrainAlignToGoal;
import frc.robot.commands.IntakeToHopper.*;

public class AUTOGetBallAndShoot extends CommandGroup{
    public AUTOGetBallAndShoot(){
        addSequential(new MoveManipulator());
        addParallel(new FourBallTrench(), 5.0);
        addParallel(new BeamBreak());
        addSequential(new StopManipulator());
        addSequential(new StopHopper()); 
        addSequential(new DrivetrainAlignToGoal());
        addSequential(new TrenchHoodAndFire());
    }
}