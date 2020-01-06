package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class DriveForwardForTime extends CommandGroup {
    public DriveForwardForTime(double speed, double duration) {
        addSequential(new DriveForward(speed), duration);
    }
}