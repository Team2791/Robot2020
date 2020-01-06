package frc.robot.commands.CargoManipulator;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Constants;
import frc.robot.commands.DoNothing;
// import frc.robot.commands.HatchManipulator.RetractHatch;
// import frc.robot.commands.auto.DriveForwardForTime;
public class ScoreCargoShip extends CommandGroup {
    public ScoreCargoShip() {
        addSequential(new DropCargo());
        addSequential(new DoNothing(),0.2);
        addSequential(new SetCargoControlsTrue());
        addSequential(new SetIntakeMotor(Constants.kCargoFastShootMotorSpeed));
        addSequential(new DoNothing(),0.55);
        addSequential(new SetCargoControlsFalse());
        addSequential(new RaiseCargo());
    }
}