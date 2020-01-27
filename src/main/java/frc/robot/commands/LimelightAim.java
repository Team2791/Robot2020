package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
// import frc.robot.subsystems.Drivetrain;
import frc.robot.util.LedMode;
// import frc.robot.util.Limelight;

public class LimelightFollow extends Command {
    //need to add stuff here
}

public void initialize() {
    Robot.limelight.setLed(LedMode.On);
}
public void execute() {
    double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);
    //tv is 0 if no targets are found, 1 if there are targets
    double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0);
    //tx returns a value between -27 (center of target is left of frame) and 27 (center of target is right of frame)
    double lengthHori = NetworkTableInstance.getDefault().getTable("limelight").getEntry("thor").getDouble(0); //Horizontal sidelength of the rough bounding box (0 - 320 pixels)
    double lengthVert = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tvert").getDouble(0); //Vertical sidelength of the rough bounding box (0 - 320 pixels)
    double turn

}