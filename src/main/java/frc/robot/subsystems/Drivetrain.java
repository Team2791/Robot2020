package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import com.revrobotics.CANEncoder;
import com.revrobotics.AlternateEncoderType;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Constants;
import frc.robot.RobotMap;
// import frc.robot.util.Camera_Switch.*;

public class Drivetrain extends Subsystem {

    private CANSparkMax leftLeader;
    private CANSparkMax rightLeader;
    private CANSparkMax [] leftFollowers;
    private CANSparkMax [] rightFollowers;
    
    private static final AlternateEncoderType kAltEncType = AlternateEncoderType.kQuadrature;
    private CANEncoder m_leftAlternateEncoder;
    private CANEncoder m_rightAlternateEncoder;
    // private CameraSwitch cam_switch;
    
    


    public Drivetrain() {
        
        // Init Camera Switch
        // cam_switch = new CameraSwitch(0, 1);

        // Init Left Leader
        leftLeader  = new CANSparkMax(RobotMap.kLeftLeader, MotorType.kBrushless);
        leftLeader.setOpenLoopRampRate(Constants.kNeoRampTime);


        // Init Right Leader
        rightLeader  = new CANSparkMax(RobotMap.kRightLeader, MotorType.kBrushless);
        rightLeader.setOpenLoopRampRate(Constants.kNeoRampTime);

        // encoders
        m_leftAlternateEncoder = leftLeader.getAlternateEncoder(kAltEncType, 1024);
        m_rightAlternateEncoder = rightLeader.getAlternateEncoder(kAltEncType, 1024);

        // init left followers
        leftFollowers = new CANSparkMax[RobotMap.kLeftFollowers.length];
        for(int i = 0; i < leftFollowers.length; ++i) {
            leftFollowers[i] = new CANSparkMax(RobotMap.kLeftFollowers[i], MotorType.kBrushless);
            leftFollowers[i].setOpenLoopRampRate(Constants.kNeoRampTime);
            leftFollowers[i].follow(leftLeader);
        }

        //init right followers
        rightFollowers = new CANSparkMax[RobotMap.kRightFollowers.length];
        for(int i = 0; i < rightFollowers.length; ++i) {
            rightFollowers[i] = new CANSparkMax(RobotMap.kRightFollowers[i], MotorType.kBrushless);
            rightFollowers[i].setOpenLoopRampRate(Constants.kNeoRampTime);
            rightFollowers[i].follow(rightLeader);
        }

        setBrakeMode(true);
    }

    public void initDefaultCommand() {}


    // public void setCameraNum(int camNum) {
    //     cam_switch.select(camNum);
    // }


    public void setMotors(double left, double right) {
        leftLeader.set(-left);
        rightLeader.set(right);
    }


    public double getLeftMotor(){
        return m_leftAlternateEncoder.getVelocity();
    }


    public double getRightMotor(){
        return m_rightAlternateEncoder.getVelocity();
    }

    
    public double getRightPosition(){
        return m_leftAlternateEncoder.getPosition();
    }

    public double getLeftPosition(){
        return m_leftAlternateEncoder.getPosition();
    }


    public double getLeftCPR(){
        return m_leftAlternateEncoder.getCountsPerRevolution();
    }

    public double getRightCPR(){
        return m_leftAlternateEncoder.getCountsPerRevolution();
    }

    public void setBrakeMode(boolean isbrake) {
        IdleMode mode = isbrake ? IdleMode.kBrake : IdleMode.kCoast;
        leftLeader.setIdleMode(mode);
        rightLeader.setIdleMode(mode);
        for(int i = 0; i < leftFollowers.length; ++i) {
            leftFollowers[i].setIdleMode(mode);
        }

        for(int i = 0; i < rightFollowers.length; ++i) {
            rightFollowers[i].setIdleMode(mode);
        }

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