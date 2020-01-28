package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
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
    private final CANSparkMax leftLeader;
    private final CANSparkMax rightLeader;
    private final CANSparkMax[] leftFollower;
    private final CANSparkMax[] rightFollower;
    private final boolean isReversing = false;
    private int[] leftVelocities;
    private int[] rightVelocities;
    private int framecounter=0;
    private AnalogInput lineSensors[];
    private final boolean lineFound = false;

    private double speedMultiplier;
    
    public Drivetrain() {
            leftLeader = new CANSparkMax(Constants.kDeviceID, MotorType.kBrushless); 
            leftLeader.setOpenLoopRampRate(Constants.kNeoRampTime);

            rightLeader = new CANSparkMax(Constants.kDeviceID, MotorType.kBrushless); 
            rightLeader.setInverted(true);
            rightLeader.setOpenLoopRampRate(Constants.kNeoRampTime);

            leftFollower = new CANSparkMax[RobotMap.kleftFollower.length];
            for(int i = 0; i<leftFollower.length; i++){
                leftFollower[i] = new CANSparkMax(RobotMap.kLeftFollowers[i], MotorType.kBrushless);
                leftFollower[i].setIdleMode(IdleMode.kCoast);
                leftFollower[i].setOpenLoopRampRate(Constants.kNeoRampTime);
                leftFollower[i].follow(leftLeader);

                }
                rightFollower = new CANSparkMax[RobotMap.kleftFollower.length];
            for(int i = 0; i<leftFollower.length; i++){
                rightFollower[i] = new CANSparkMax(RobotMap.kLeftFollowers[i], MotorType.kBrushless);
                rightFollower[i].setIdleMode(IdleMode.kCoast);
                rightFollower[i].setOpenLoopRampRate(Constants.kNeoRampTime);
                rightFollower[i].follow(leftLeader);

                
            }
        }

    public void setLimit(int limit) {
            if(pCurrent != limit) {
                setLimit(limit);
            }
            
        }
    private void setCurrentLimit(int limit) {
            leftLeader.setSmartCurrentLimit(limit);
            leftLeader.setSecondaryCurrentLimit(120, 0);
            rightLeader.setSmartCurrentLimit(limit);
            rightLeader.setSecondaryCurrentLimit(120, 0);
            for(int i = 0; i < leftFollower.length; ++i) {
                leftFollower[i].setSmartCurrentLimit(limit);
                leftFollower[i].setSecondaryCurrentLimit(120, 0);
                rightFollower[i].setSmartCurrentLimit(limit);
                rightFollower[i].setSecondaryCurrentLimit(120, 0);
            }
            setDriveSpeed(false);
            setBrakeMode(true);
            setMotors(0, 0);

            setCurrentLimit(Constants.kNeoAmpLimit);
            lineFound = false;

            leftVelocities = new int[] {0, 0, 0, 0, 0};
            rightVelocities = new int[] {0, 0, 0, 0, 0};
            

        }

    public void setMotors(double left, double right) {
            SmartDashboard.putNumber("LeftSideOutput", left);
            SmartDashboard.putNumber("RightSideOutput", right);
    
            if(Math.abs(left - right) <= 0.2 && getLeftVelocity() < -1 && getRightVelocity() < -1 && (left + right) / 2 > 0) {
                // leftLeader.set(Math.max(0, left * speedMultiplier));
                // rightLeader.set(Math.max(0, right * speedMultiplier));
                leftLeader.set(0);
                rightLeader.set(0);
            } else {
                leftLeader.set(left * speedMultiplier);
                rightLeader.set(right * speedMultiplier);
            }
        }
    public void setBrakeMode(boolean isbrake) {
            IdleMode mode = isbrake ? IdleMode.kBrake : IdleMode.kCoast;
            leftLeader.setIdleMode(mode);
            rightLeader.setIdleMode(mode);
        }

    public void setDriveSpeed(boolean isSlow) {
            speedMultiplier = isSlow ? Constants.kSlowDrive : Constants.kFastDrive;
        }
    
    public int getLeftEncoder() {
            return (int)(leftLeader.getEncoder().getPosition() + 0.5); //+1/2 for rounding
        }
    
    public int getRightEncoder() {
            return (int)(rightLeader.getEncoder().getPosition() + 0.5); //+1/2 for rounding
        }
    public void update() {
            int leftEnc = getLeftEncoder();
            int rightEnc = getRightEncoder();
    
            int lVel = leftEnc - pLeftEnc;
            int rVel = rightEnc - pRightEnc;
            pLeftEnc = leftEnc;
            pRightEnc = rightEnc;
            setVelocity(lVel, rVel);
            if(getVelocity() >= 0) {
                isReversing = false;
            } else {
                isReversing = true;
            }
            ++frameCounter;
        }

    public boolean getIsReversing() {
            return isReversing;
        }

    private double getLeftVelocity() {
            int max = 0;
            if(leftVelocities == null) return 0;
            for(int i = 0; i < 5; ++i) {
                if(Math.abs(leftVelocities[i]) > Math.abs(max)){
                    max = leftVelocities[i];
                }
            }
            return max;
        }
    
    private double getRightVelocity() {
            int max = 0;
            if(rightVelocities == null) return 0;
            for(int i = 0; i < 5; ++i) {
                if(Math.abs(rightVelocities[i]) > Math.abs(max)){
                    max = rightVelocities[i];
                }
            }
            return max;
        }
    private void setVelocity(int left, int right) {
            leftVelocities[frameCounter % 5] = left;
            rightVelocities[frameCounter % 5] = right;
        }
    public double getVelocity() {
            return getLeftVelocity() + getRightVelocity() / 2;
        }
    public double getLeftDistance() {
            return leftLeader.getEncoder().getPosition() * ((0.5 * Math.PI)*.12);
        }
    public double getRightDistance() {
            return rightLeader.getEncoder().getPosition() * ((0.5 * Math.PI)*.12);
        }    

    public void setMotors(final double left) {
        drivetrain_neo.set(left);
    }
    public void debug() {
    
        if(Constants.debugMode==true){
            SmartDashboard.putNumber("Left Encoder", getLeftEncoder());
            SmartDashboard.putNumber("Right Encoder", getRightEncoder());
            SmartDashboard.putNumber("Right Drivetrain 1", Robot.pdp.getCurrent(1));
            SmartDashboard.putNumber("Right Drivetrain 2", Robot.pdp.getCurrent(0));
            SmartDashboard.putNumber("Left Drivetrain 1", Robot.pdp.getCurrent(14));
            SmartDashboard.putNumber("Left Drivetrain 2", Robot.pdp.getCurrent(15));
            SmartDashboard.putNumber("Measured Velocity", getVelocity());
        
     
        }
    }
}
