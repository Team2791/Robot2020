package frc.robot.commands.CargoManipulator;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Constants;
//import frc.robot.commands.DoNothing;
//This command group raises the cargo intake arms
public class SlowShootCargo extends CommandGroup {
    public SlowShootCargo() {
        addSequential(new SetCargoControlsTrue());
        addSequential(new RaiseCargo());
        addSequential(new SetIntakeMotor(Constants.kCargoSlowShootMotorSpeed));
    }
}