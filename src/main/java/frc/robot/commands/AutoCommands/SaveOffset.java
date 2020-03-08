package frc.robot.commands.AutoCommands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class SaveOffset extends Command {
    //public double tempOffset;

    public SaveOffset() {
        super("SaveOffset");
        requires(Robot.limelight);
    }

    public void execute() {
        Constants.tempOffset = Robot.limelight.getHorizontalOffset(); 
    }

    public void end() {
    }

    public boolean isFinished() {
        return true;
    }
}