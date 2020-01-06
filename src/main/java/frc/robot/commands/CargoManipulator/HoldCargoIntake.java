package frc.robot.commands.CargoManipulator;

import frc.robot.commands.HatchManipulator.CloseGrabber;
import frc.robot.commands.HatchManipulator.RetractHatch;
import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Constants;

//This command group drops the cargo intake arms, then sets the intake motor speed to kCargoIntakeMotorSpeed, then waits until the cargo limit switch is pressed
//Then waits kRaiseCargoArmsDelayAfterButtonPressed, then reduces the intake motor speed to kCargoIntakeMotorStallSpeed, then raises the cargo intake arms
public class HoldCargoIntake extends CommandGroup {
    public HoldCargoIntake() {
        addSequential(new CloseGrabber());
        addSequential(new RetractHatch());
        addSequential(new SetCargoControlsTrue());
        addSequential(new DropCargo());
        addSequential(new SetIntakeMotor(Constants.kCargoIntakeMotorSpeed));
        // addSequential(new CheckForBall());
        // addSequential(new DoNothing(), Constants.kRaiseCargoArmsDelayAfterButtonPressed);
        // addSequential(new SetIntakeMotor(Constants.kCargoIntakeMotorStallSpeed));
        // addSequential(new RaiseCargo());
    }
}