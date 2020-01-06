package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.Lifter.EStopLifters;
import frc.robot.commands.StopDrive;
import frc.robot.commands.CargoManipulator.StopCargoMotor;
import frc.robot.commands.Elevator.StopElevator;

public class StopTotal extends CommandGroup {
    public StopTotal (){
        addSequential(new EStopLifters());
        addSequential(new StopCargoMotor());
        addSequential(new StopDrive());
        addSequential(new StopElevator());

    }
}

