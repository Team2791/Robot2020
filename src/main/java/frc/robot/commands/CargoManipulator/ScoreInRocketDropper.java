package frc.robot.commands.CargoManipulator;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Constants;
import frc.robot.commands.DoNothing;
// import frc.robot.commands.HatchManipulator.RetractHatch;
import frc.robot.commands.auto.DriveForwardForTime;
public class ScoreInRocketDropper extends CommandGroup {
    public ScoreInRocketDropper() {
        addSequential(new DriveForwardForTime(-.65, 0.10));
        addSequential(new DropCargo());
        addSequential(new DoNothing(),0.1);
        addSequential(new SetCargoControlsTrue());
        addSequential(new SetIntakeMotor(Constants.kCargoFastShootMotorSpeed));
        addSequential(new DoNothing(),0.55);
        addSequential(new SetCargoControlsFalse());
        addSequential(new RaiseCargo());
    }
}