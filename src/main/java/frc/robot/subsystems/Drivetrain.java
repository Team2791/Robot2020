package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import com.revrobotics.CANEncoder;
import com.revrobotics.AlternateEncoderType;

import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.util.Util;

public class Drivetrain extends Subsystem {

    private CANSparkMax leftLeader;
    private CANSparkMax rightLeader;
    private CANSparkMax [] leftFollowers;
    private CANSparkMax [] rightFollowers;
    
    private static final AlternateEncoderType kAltEncType = AlternateEncoderType.kQuadrature;
    private CANEncoder m_leftAlternateEncoder;
    private CANEncoder m_rightAlternateEncoder;
    


    public Drivetrain() {
        leftLeader  = new CANSparkMax(RobotMap.kLeftLeader, MotorType.kBrushless);
        leftLeader.setOpenLoopRampRate(Constants.kNeoRampTime);
        rightLeader  = new CANSparkMax(RobotMap.kRightLeader, MotorType.kBrushless);
        rightLeader.setOpenLoopRampRate(Constants.kNeoRampTime);
        leftFollowers = new CANSparkMax[RobotMap.kLeftFollowers.length];

        m_leftAlternateEncoder = leftLeader.getAlternateEncoder(kAltEncType, 1024);
        m_rightAlternateEncoder = rightLeader.getAlternateEncoder(kAltEncType, 1024);

        for(int i = 0; i < leftFollowers.length; ++i) {
            leftFollowers[i] = new CANSparkMax(RobotMap.kLeftFollowers[i], MotorType.kBrushless);
            leftFollowers[i].setOpenLoopRampRate(Constants.kNeoRampTime);
            leftFollowers[i].follow(leftLeader);
        }
        rightFollowers = new CANSparkMax[RobotMap.kRightFollowers.length];
        for(int i = 0; i < rightFollowers.length; ++i) {
            rightFollowers[i] = new CANSparkMax(RobotMap.kRightFollowers[i], MotorType.kBrushless);
            rightFollowers[i].setOpenLoopRampRate(Constants.kNeoRampTime);
            rightFollowers[i].follow(rightLeader);
        }
    }

    public void initDefaultCommand() {

        }


    public void setMotors(double left, double right) {
        leftLeader.set(-left);
        rightLeader.set(right);
    }

    public double getLeftMotor(){
        // return leftLeader.getEncoder().getVelocity();
        return m_leftAlternateEncoder.getVelocity();
    }

    public double getRightMotor(){
        // return rightLeader.getEncoder().getVelocity();
        return m_rightAlternateEncoder.getVelocity();
    }

    public double getRightPosition(){
        // return leftLeader.getAlternateEncoder().getPosition();
        return m_leftAlternateEncoder.getPosition();
    }

    public double getLeftPosition(){
        // return rightLeader.getAlternateEncoder().getPosition();
        return m_leftAlternateEncoder.getPosition();
    }
    public double getLeftCPR(){
        // return leftLeader.getAlternateEncoder().getCountsPerRevolution();
        return m_leftAlternateEncoder.getCountsPerRevolution();
    }

    public double getRightCPR(){
        // return rightLeader.getAlternateEncoder().getCountsPerRevolution();
        return m_leftAlternateEncoder.getCountsPerRevolution();
    }
    public void debug() {
        SmartDashboard.putNumber("Get Left Velocity", getLeftMotor());
        SmartDashboard.putNumber("Get Right Velocity", getRightMotor());
        SmartDashboard.putNumber("Get Right Position", getRightPosition());
        SmartDashboard.putNumber("Get Left Position", getLeftPosition());
        SmartDashboard.putNumber("Get Right CPR", getRightCPR());
        SmartDashboard.putNumber("Get Left CPR", getLeftCPR());
    }
}  