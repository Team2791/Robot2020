package frc.robot.commands.Lifter;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.util.DelayedBoolean;

public class DriveLifterWheelBackIR extends Command {
    DelayedBoolean backOverLedge;
    public DriveLifterWheelBackIR() {
        backOverLedge = new DelayedBoolean(1);
    }

    @Override
    protected void initialize() {
        backOverLedge.update(false);
    }

    protected void execute() {
        
        if(Robot.lifters.isBackOverLedge(true)) {
            Robot.lifters.driveMotor(Constants.kLifterDrivePowerOverLedge);
        } else {
            Robot.lifters.driveMotor(Constants.kLifterDrivePower);
        }
        
        // if (Robot.lifters.getBackIR() >= Constants.kBackPlatformCutoff) {
        //     Robot.lifters.driveMotor(0);
        // }
        // else{
        //     Robot.lifters.driveMotor(Constants.kLifterDrivePower);
        // }
    }

    protected boolean isFinished() {
        return backOverLedge.update(Robot.lifters.isBackOverLedge(true));
    }

    protected void end() {
        Robot.lifters.driveMotor(0);
    }
}
