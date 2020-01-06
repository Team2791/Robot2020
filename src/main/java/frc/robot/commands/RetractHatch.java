package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.subsystems.HatchManipulator;
import frc.robot.*;

public class RetractHatch extends Command {
    public void execute() {
        Robot.hatchManipulator.setExtender(false);
    }

    public boolean isFinished() {
        return true;
    }
}
