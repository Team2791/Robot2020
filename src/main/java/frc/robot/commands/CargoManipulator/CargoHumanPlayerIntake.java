package frc.robot.commands.CargoManipulator;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Constants;
import frc.robot.commands.DoNothing;
import frc.robot.commands.HatchManipulator.RetractHatch;
//This command group drops the cargo intake arms, then sets the intake motor speed to kCargoIntakeMotorSpeed, then waits until the cargo limit switch is pressed
//Then waits kRaiseCargoArmsDelayAfterButtonPressed, then reduces the intake motor speed to kCargoIntakeMotorStallSpeed, then raises the cargo intake arms
public class CargoHumanPlayerIntake extends CommandGroup {
    public CargoHumanPlayerIntake() {
        addSequential(new RetractHatch());
        addSequential(new SetCargoControlsTrue());
        addSequential(new SetIntakeMotor(Constants.kCargoIntakeHumanSpeed));
        addSequential(new CheckForBall());
        addSequential(new DoNothing(), Constants.kRaiseCargoArmsDelayAfterButtonPressed);
        addSequential(new SetIntakeMotor(Constants.kCargoIntakeMotorStallSpeed));
        //addSequential(new SetIntakeMotor(Constants.kCargoIntakeMotorStallSpeed));
    }
}