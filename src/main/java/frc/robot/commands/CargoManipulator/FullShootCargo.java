package frc.robot.commands.CargoManipulator;

import edu.wpi.first.wpilibj.command.CommandGroup;
//This command group sets the cargo controls boolean to true, then raises the cargo intake arms
public class FullShootCargo extends CommandGroup {
    public FullShootCargo() {
        addSequential(new SetCargoControlsTrue());
        addSequential(new RaiseCargo());
        addSequential(new SetIntakeMotor(-1.0));
    }
}