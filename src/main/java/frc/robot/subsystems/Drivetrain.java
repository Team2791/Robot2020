package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import com.revrobotics.CANEncoder;
import com.revrobotics.AlternateEncoderType;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Constants;
import frc.robot.Robot;
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

    public Timer driveTime;
    
    


    public Drivetrain() {

        // Init Left Leader
        leftLeader  = new CANSparkMax(RobotMap.kLeftLeader, MotorType.kBrushless);
        leftLeader.setOpenLoopRampRate(Constants.kNeoRampTime);
        leftLeader.setSmartCurrentLimit(60);


        // Init Right Leader
        rightLeader  = new CANSparkMax(RobotMap.kRightLeader, MotorType.kBrushless);
        rightLeader.setOpenLoopRampRate(Constants.kNeoRampTime);
        rightLeader.setSmartCurrentLimit(60);

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

        driveTime = new Timer();
        setBrakeMode(true);
    }

    public void initDefaultCommand() {}



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

    public double getAverageDistance(){
        return Util.average(getDistanceLeft(), getDistanceRight());
    }
    
    public double getDistanceLeft(){
        return Math.PI*Constants.wheelDiameterInches*(getLeftCPR()/1024)/12; // returns distance in feet
    }
    
    public double getDistanceRight(){
        return Math.PI*Constants.wheelDiameterInches*(getLeftCPR()/1024)/12; // returns distance in feet
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
    public void stopCompletely() {
        leftLeader.set(0);
        rightLeader.set(0);
    }


    public void BackupAndShoot(){
        SmartDashboard.putString("AUTO_LOADED", "BackupAndShoot");
        //driveTime.start();
        if(driveTime.get() < 1.85){
            setMotors(-0.5,-0.5);
            Robot.shooter.setShooterPID(-3200);
        }
        if (driveTime.get() >= 1.85){
            Robot.shooter.setShooterPID(-3200);
            Robot.shooter.setHood1(true);
            Robot.hopper.setExtended();
            setMotors(0, 0);
            if(driveTime.get() > 6.45){
                Robot.hopper.setHopper(Constants.HOPPER_AUTO_HORIZONTAL, Constants.HOPPER_AUTO_VERTICAL);
            } 
        }
    }
    public void BackAndShoot2(){
        SmartDashboard.putString("AUTO_LOADED", "BackAndShoot2");
        //driveTime.start();
        if(driveTime.get() < 1.5){
            setMotors(-0.5,-0.5);
            Robot.shooter.setShooterPID(-2900);
        }
        if (driveTime.get() >= 1.5){
            Robot.shooter.setShooterPID(-2900);
            Robot.shooter.setHood1(true);
            Robot.hopper.setExtended();
            setMotors(0, 0);
            if(driveTime.get() > 6.3){
                Robot.hopper.setHopper(Constants.HOPPER_AUTO_HORIZONTAL, Constants.HOPPER_AUTO_VERTICAL);
            }
            
        }




    }
    public void BackAndShoot3(){
        SmartDashboard.putString("AUTO_LOADED", "BackAndShoot3");
        //driveTime.start();
        if(driveTime.get() < 1.5){
            setMotors(-0.5,-0.5);
            Robot.shooter.setShooterPID(-2900);
        }
        if (driveTime.get() >= 1.5){
            Robot.shooter.setShooterPID(-2900);
            Robot.shooter.setHood1(true);
            Robot.hopper.setExtended();
            setMotors(0, 0);
            if(driveTime.get() > 6.3){
                Robot.hopper.setHopper(Constants.HOPPER_AUTO_HORIZONTAL, Constants.HOPPER_AUTO_VERTICAL);
            }  
        }
    }



    public void driveBackAndShoot_angled(){
        SmartDashboard.putString("AUTO_LOADED", "DriveBackAndShoot_angled");
    
        if(driveTime.get() < 2.25){
            setMotors(-0.5,-0.5);
            Robot.shooter.setShooterPID(-2900);
        }
        if (driveTime.get() >= 2.25){
            setMotors(0, 0);
            Robot.shooter.setShooterPID(-2900);
            Robot.shooter.setHood1(true);
            Robot.hopper.setExtended();
            if(driveTime.get() > 6.3){
                Robot.hopper.setHopper(Constants.HOPPER_AUTO_HORIZONTAL, Constants.HOPPER_AUTO_VERTICAL);
            }
            
        }

    }


    public void debug() {
        SmartDashboard.putNumber("Get Left Velocity", getLeftMotor());
        SmartDashboard.putNumber("Get Right Velocity", getRightMotor());
        SmartDashboard.putNumber("Get Right Position", getRightPosition());
        SmartDashboard.putNumber("Get Left Position", getLeftPosition());
        SmartDashboard.putNumber("Get Right CPR", getRightCPR());
        SmartDashboard.putNumber("Get Left CPR", getLeftCPR());
        SmartDashboard.putNumber("Drivetimer", driveTime.get());
    }
    
}  