package frc.robot.commands.CargoManipulator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

//This command lowers the cargo intake arms and then finishes.
public class DropCargo extends Command {
    public DropCargo(){
        super("DropCargo");
        requires(Robot.cargoManipulator);
        
    }
    public void execute() {
        Robot.cargoManipulator.setRaiser(true);
    }

    public boolean isFinished() {
         return true;
    }
    
}