package frc.robot.commands.CargoManipulator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Constants;
//This command group sets the cargo controls boolean to true, then raises the cargo intake arms
public class FastShootCargo extends CommandGroup {
    public FastShootCargo() {
        addSequential(new SetCargoControlsTrue());
        addSequential(new RaiseCargo());
        addSequential(new SetIntakeMotor(Constants.kCargoFastShootMotorSpeed));
    }
}