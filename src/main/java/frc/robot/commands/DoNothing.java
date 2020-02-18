package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

//This command does nothing and is used as a delay
public class DoNothing extends Command {
    public DoNothing() {
        super("DoNothing");
    }

    public boolean isFinished() {
        return false;
    }
}