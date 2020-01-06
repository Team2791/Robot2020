package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;
import frc.robot.util.LedMode;
import frc.robot.util.Limelight;

public class LimelightFollow extends Command {
    public LimelightFollow() {
        super("LimelightFollow");
        requires(Robot.drivetrain);
    }

    public void initialize() {
        Robot.limelight.setLed(LedMode.On);
    }
    public void execute() {
        double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
        //tv is 0 if no targets are found, 1 if there are targets
        double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
        //tx returns a value between -27 (center of target is left of frame) and 27 (center of target is right of frame)
        
        double turn = Constants.kCamTurn;
        double thrust = Constants.kCamStraight;
        
        if(tv < 1) {
            Robot.drivetrain.setMotors(thrust, thrust); //if I dont see a target, drive forward
            Robot.drivetrain.setGreenLED(false);
            return;
        }
        Robot.drivetrain.setGreenLED(true);
        turn *= (tx/27); //sets "turn" = to kCamTurn * the percentage of how off the target is

        double left = Math.max(Math.min(thrust + turn, 1), -1);
        double right = Math.max(Math.min(thrust - turn, 1), -1);
        Robot.drivetrain.setMotors(left, right);
    }

    public void end() {
        Robot.drivetrain.setMotors(0, 0);
        Robot.limelight.setLed(LedMode.Off);
    }

    public boolean isFinished() {
        return Robot.drivetrain.getLineSensors() > 0;
    }
}