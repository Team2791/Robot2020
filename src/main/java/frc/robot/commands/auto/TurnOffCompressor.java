package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class TurnOffCompressor extends Command {

    public TurnOffCompressor() {
        super("TurnOffCompressor");
    }

    public void execute() {
        Robot.compressor.stop();
    }

    public boolean isFinished() {
        return true;
    }

    protected void end() {
        Robot.compressor.stop();
    }
}