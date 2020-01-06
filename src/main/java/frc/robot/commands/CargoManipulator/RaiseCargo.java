package frc.robot.commands.CargoManipulator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

//This command raises the cargo intake arms, and then finishes
public class RaiseCargo extends Command {
    public RaiseCargo(){
        super("RaiseCargo");
        requires(Robot.cargoManipulator);
    }
    public void execute() {
        Robot.cargoManipulator.setRaiser(false);
    }

    public boolean isFinished() {
         return true;
    }
    
}