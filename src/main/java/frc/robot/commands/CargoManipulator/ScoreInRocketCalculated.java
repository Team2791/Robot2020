package frc.robot.commands.CargoManipulator;
import edu.wpi.first.wpilibj.command.CommandGroup;
// import frc.robot.Constants;
import frc.robot.commands.DoNothing;
// import frc.robot.commands.HatchManipulator.RetractHatch;
import frc.robot.commands.auto.DriveForwardForTime;
public class ScoreInRocketCalculated extends CommandGroup {
    public ScoreInRocketCalculated() {
        addSequential(new DriveForwardForTime(-.33,.2));
        addSequential(new SetCargoControlsTrue());
        addSequential(new FastShootCargo());
        addSequential(new DoNothing(),0.15);
        addSequential(new DriveForwardForTime(.33,.2));
        addSequential(new SetCargoControlsFalse());
    }
}