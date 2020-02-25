package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.util.Camera_Switch.*;

public class Camera extends Subsystem{
    public CameraSwitch cam_switch; 
    public Camera() {

    //Initialize Cameron Switch
    cam_switch = new CameraSwitch(0, 1);
    }
    public void initDefaultCommand() {

    }
    public void setCameraNum(int camNum) {
        cam_switch.select(camNum);
    }

    public void debug (){}

}