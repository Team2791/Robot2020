package frc.robot.util;

import java.lang.Math;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Robot;

//import frc.robot.util.Limelight.CameraMode;

public class Limelight {

    NetworkTable table;
    private NetworkTableEntry camMode, ledMode, horizontalOffset, verticalOffset, targetArea, validTargets, targetSkew, pipelineLatency;
    private NetworkTableEntry lengthShort, lengthLong, lengthHori, lengthVert, indexPipe;
    private NetworkTableEntry camMode1, ledMode1, horizontalOffset1, verticalOffset1, targetArea1, validTargets1, targetSkew1, pipelineLatency1;
    private NetworkTableEntry lengthShort1, lengthLong1, lengthHori1, lengthVert1, indexPipe1;
    //  private static double kProportion = 1.56;
    private static double kcameraHeight = 13.5; // height of the camera IN INCHES
    private static double kobjectHeight = 97.5; // height of the shooting target IN INCHES
    private static double kmountingAngle = 28.7; // mounting angle of the camera IN DEGREES
    private static double kverticalDistance = kobjectHeight - kcameraHeight; 



    public Limelight() {
        // Set table to limelight
        table = NetworkTableInstance.getDefault().getTable("limelight");

        // Get stats
        horizontalOffset1 = table.getEntry("tx"); //Horizontal Offset From Crosshair To Target (-27 degrees to 27 degrees)
        verticalOffset1 = table.getEntry("ty"); //Vertical Offset From Crosshair To Target (-20.5 degrees to 20.5 degrees)
        targetArea1 = table.getEntry("ta"); //Target Area (0% of image to 100% of image)
        validTargets1 = table.getEntry("tv"); //Whether the limelight has any valid targets (0 or 1)
        targetSkew1 = table.getEntry("ts"); //Skew or rotation (-90 degrees to 0 degrees)
        pipelineLatency1 = table.getEntry("tl"); //The pipeline’s latency contribution (ms) Add at least 11ms for image capture latency.
        lengthShort1 = table.getEntry("tshort"); //Sidelength of shortest side of the fitted bounding box (pixels)
        lengthLong1 = table.getEntry("tlong"); //Sidelength of longest side of the fitted bounding box (pixels)
        lengthHori1 = table.getEntry("thor"); //Horizontal sidelength of the rough bounding box (0 - 320 pixels)
        lengthVert1 = table.getEntry("tvert"); //Vertical sidelength of the rough bounding box (0 - 320 pixels)
        indexPipe1 = table.getEntry("getpipe"); //True active pipeline index of the camera (0 .. 9)

        horizontalOffset=horizontalOffset1;
        verticalOffset=verticalOffset1;
        targetArea=targetArea1;
        validTargets=validTargets1;
        targetSkew=targetSkew1;
        pipelineLatency=pipelineLatency1;
        lengthShort=lengthShort1;
        lengthHori=lengthHori1;
        lengthVert=lengthVert1;
        indexPipe=indexPipe1;
        lengthLong=lengthLong1;



        ledMode = table.getEntry("ledMode");
        camMode = table.getEntry("camMode");

    }


    // Methods to get information
    public double getHorizontalOffset() {
        return horizontalOffset.getDouble(0.0);
    }

    public double getVerticalOffset() {
        return verticalOffset.getDouble(0.0);
    }

    public double getDiagonalOffset() {
        return Math.sqrt(Math.pow(kverticalDistance,2)+Math.pow(getDistance(),2));
    }
    public double getTargetArea() {
        return targetArea.getDouble(0.0);
    }

    public boolean targetValid() {
        if (validTargets.getDouble(0.0) == 1.0) {
            return true;
        } else {
            return false;
        }
    }

    public double getTargetSkew() {
        return targetSkew.getDouble(0.0);
    }

    public double getPipelineLatency() {
        return pipelineLatency.getDouble(0.0);
    }

    public double getLengthShort() {
        return lengthShort.getDouble(0.0);
    }

    public double getLengthLong() {
        return lengthLong.getDouble(0.0);
    }

    public double getLengthHori() {
        return lengthHori.getDouble(0.0);
    }

    public double getLengthVert() {
        return lengthVert.getDouble(0.0);
    }

    public double getIndexPipe() {
        return indexPipe.getDouble(0.0);
    }

    public double getDistance() {
        // returns distance in inches from the robot
        double distance = (kverticalDistance)/Math.tan(Math.toRadians(kmountingAngle)+Math.toRadians(getVerticalOffset()));
        return distance;
    }


    // Methods to set Camera settings

    // Controls Leds
    // String mode must be either "on" or "off" or "blink"
    public void setLed(LedMode mode) {
        if (mode == LedMode.On) {
            ledMode.setNumber(0);

        } else if (mode == LedMode.Off) {
            ledMode.setNumber(1);
        } else if (mode == LedMode.Blink) {
            ledMode.setNumber(2);
        }

    }
    // Sets the camera to a operation mode
    // String mode must be either "vision" or "driver"
    public void setCameraMode(CameraMode mode) {
        if (mode == CameraMode.Vision) {
            camMode.setNumber(0);
        } else if (mode == CameraMode.Driver) {
            camMode.setNumber(1);
        }


    }

    public void debug() {
        SmartDashboard.putString("Limelight Pipeline Index", Double.toString(getIndexPipe()));
        SmartDashboard.putString("Limelight Calculated Distance", Double.toString(getDistance()));
        SmartDashboard.putString("Limelight Distance from Object", Double.toString(getDistance()));
        SmartDashboard.putString("Limelight Sidelength of shortest side", Double.toString(getLengthShort()));
        SmartDashboard.putString("Limelight Sidelength of longest side", Double.toString(getLengthLong()));
        SmartDashboard.putString("Limelight Area", Double.toString(getTargetArea()));
        SmartDashboard.putString("Limelight Skew", Double.toString(getTargetSkew()));
        SmartDashboard.putString("Limelight Horizontal", Double.toString(getHorizontalOffset()));
        SmartDashboard.putString("Limelight Vertical", Double.toString(getVerticalOffset()));
        SmartDashboard.putString("Limelight Valid", Boolean.toString(targetValid()));
        SmartDashboard.putString("Limelight Horizontal sidelength", Double.toString(getLengthHori()));
        SmartDashboard.putString("Limelight Vertical sidelength", Double.toString(getLengthVert()));
        SmartDashboard.putString("Limelight Diagonal" , Double.toString(getDiagonalOffset()));
    }
}
