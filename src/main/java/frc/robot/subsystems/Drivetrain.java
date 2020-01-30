package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.analog.adis16470.frc.ADIS16470_IMU;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.util.Util;

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

    public double getAngle(){
        return imu.getAngle();
    }

    public void setMotors(final double left, final double right) {
        drivetrain_drive.tankDrive(left, right);
    }
    public void debug() {
    }
}  