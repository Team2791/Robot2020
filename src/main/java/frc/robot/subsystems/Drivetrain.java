package frc.robot.subsystems;

import com.analog.adis16470.frc.ADIS16470_IMU;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.RobotMap;

public class Drivetrain extends Subsystem {

    public Talon drivetrain_talonleft;
    public Talon drivetrain_talonright;
    public DifferentialDrive drivetrain_drive;
    public ADIS16470_IMU imu;

    public Drivetrain() {
        drivetrain_talonleft = new Talon(RobotMap.DRIVETRAIN_TALON_LEFT);
        drivetrain_talonright = new Talon(RobotMap.DRIVETRAIN_TALON_RIGHT);

        drivetrain_drive = new DifferentialDrive(drivetrain_talonleft, drivetrain_talonright);
        imu = new ADIS16470_IMU();
    }

    public void initDefaultCommand() {

        }

    
    public void resetAccelerometer(){
        imu.reset();
    }

    public double getAngleZ(){
        return imu.getGyroInstantZ();
    }

    public double getAngleX(){
        return imu.getGyroInstantX();
    }

    public double getAngleY(){
        return imu.getGyroInstantY();
    }

    public double getAngle(){
        return imu.getAngle();
    }

    public double getError(){
        double error = Constants.ANGLE_SETPOINT - imu.getAngle();
        return error;
    }

    public double getTurningValue(){
        double error = Constants.ANGLE_SETPOINT - imu.getAngle();
        double turning = error*Constants.P;
        return turning;
    }

    public void setMotors(final double left, final double right) {
        drivetrain_drive.tankDrive(left, right);
    }
    public void debug() {
        SmartDashboard.putNumber("Angle Z - ", getAngleZ());
        SmartDashboard.putNumber("Angle X - ", getAngleX());
        SmartDashboard.putNumber("Angle Y - ", getAngleY());
        SmartDashboard.putNumber("Angle  - ", getAngle());
        SmartDashboard.putNumber("Error  - ", getError());
        SmartDashboard.putNumber("Turning Value  - ", getTurningValue());




    }
}  