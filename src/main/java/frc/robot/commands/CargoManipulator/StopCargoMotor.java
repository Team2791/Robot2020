package frc.robot.commands.CargoManipulator;

import edu.wpi.first.wpilibj.command.CommandGroup;
//import frc.robot.Constants;
//import frc.robot.commands.DoNothing;
//This command group raises the cargo intake arms, and then sets the intake motor speed to kCargoIntakeMotorStallSpeed
public class StopCargoMotor extends CommandGroup {
    public StopCargoMotor() {
        addSequential(new SetCargoControlsFalse());
        addSequential(new RaiseCargo());
        addSequential(new SetIntakeMotor(0.0));
    }
}