package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.util.Util;

public class Drivetrain extends Subsystem {
    private CANSparkMax leftLeader;
    private CANSparkMax rightLeader;
    private CANSparkMax[] leftFollower;
    private CANSparkMax[] rightFollower;
    private final boolean isReversing = false;
    private int[] leftVelocities;
    private int[] rightVelocities;
    private int framecounter;
    private AnalogInput lineSensors[];
    private final boolean lineFound = false;

    private double speedMultiplier;
    
    public Drivetrain() {
            leftLeader = new CANSparkMax(Constants.kDeviceID, MotorType.kBrushless); 
            leftLeader.setOpenLoopRampRate(Constants.kNeoRampTime);

            rightLeader = new CANSparkMax(Constants.kDeviceID, MotorType.kBrushless); 
            rightLeader.setInverted(true);
            rightLeader.setOpenLoopRampRate(Constants.kNeoRampTime);
        }


    public void setMotors(final double left) {
        drivetrain_neo.set(left);
    }
    public void debug() {
    }

    privat
}  