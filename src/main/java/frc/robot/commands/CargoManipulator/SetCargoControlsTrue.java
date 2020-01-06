package frc.robot.commands.CargoManipulator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

//This command lowers the cargo intake arms and then finishes.
public class SetCargoControlsTrue extends Command {

    public void execute() {
        Robot.cargoManipulator.setCargoControls(true);
    }

    public boolean isFinished() {
         return true;
    }
    
}