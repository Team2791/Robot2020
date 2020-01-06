package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj.DigitalOutput;
// import edu.wpi.first.wpilibj.PWM;
import edu.wpi.first.wpilibj.Solenoid;

import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.commands.StopDrive;
import frc.robot.Robot;
// import edu.wpi.first.wpilibj.ADXRS450_Gyro;

public class Drivetrain extends Subsystem {
    private CANSparkMax leftLeader;
    private CANSparkMax rightLeader;
    private CANSparkMax[] leftFollowers;
    private CANSparkMax[] rightFollowers;
    private int pCurrent;
    private boolean isReversing = false;
    private int pLeftEnc;
    private int pRightEnc;
    private int[] leftVelocities;
    private int[] rightVelocities;
    private int frameCounter;    

    private AnalogInput lineSensors[];
    private boolean lineFound = false;
    private Solenoid blueLED;
    private Solenoid greenLED;
    // private DigitalOutput pin0;
    // private PWM lineLight;
    //private ADXRS450_Gyro gyro;
    // private boolean gyroDisabled = false;


    private double speedMultiplier;

    private StopDrive defaultCommand;

    public Drivetrain() {
        leftLeader = new CANSparkMax(RobotMap.kLeftLeader, MotorType.kBrushless);
        leftLeader.setOpenLoopRampRate(Constants.kNeoRampTime);
        
        rightLeader = new CANSparkMax(RobotMap.kRightLeader, MotorType.kBrushless);
        rightLeader.setInverted(true);
        rightLeader.setOpenLoopRampRate(Constants.kNeoRampTime);

        leftFollowers = new CANSparkMax[RobotMap.kLeftFollowers.length];
        for(int i = 0; i < leftFollowers.length; ++i) {
            leftFollowers[i] = new CANSparkMax(RobotMap.kLeftFollowers[i], MotorType.kBrushless);
            leftFollowers[i].setIdleMode(IdleMode.kCoast);
            leftFollowers[i].setOpenLoopRampRate(Constants.kNeoRampTime);
            leftFollowers[i].follow(leftLeader);
        }

        rightFollowers = new CANSparkMax[RobotMap.kRightFollowers.length];
        for(int i = 0; i < rightFollowers.length; ++i) {
            rightFollowers[i] = new CANSparkMax(RobotMap.kRightFollowers[i], MotorType.kBrushless);
            rightFollowers[i].setInverted(true);
            rightFollowers[i].setIdleMode(IdleMode.kCoast);
            rightFollowers[i].setOpenLoopRampRate(Constants.kNeoRampTime);
            rightFollowers[i].follow(rightLeader);
        }

        lineSensors = new AnalogInput[4];
        blueLED = new Solenoid(RobotMap.kPCM, RobotMap.kLEDBlueSolenoid);
        greenLED = new Solenoid(RobotMap.kPCM, RobotMap.kLEDGreenSolenoid);
        for(int i = 0; i < 4; ++i) {
            lineSensors[i] = new AnalogInput(RobotMap.kLineSensors[i]);
        }

        setDriveSpeed(false);
        setBrakeMode(true);
        setMotors(0, 0);

        setCurrentLimit(Constants.kNeoAmpLimit);
        lineFound = false;

        leftVelocities = new int[] {0, 0, 0, 0, 0};
        rightVelocities = new int[] {0, 0, 0, 0, 0};
        frameCounter = 0;
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
        for(int i = 0; i < leftFollowers.length; ++i) {
            leftFollowers[i].setSmartCurrentLimit(limit);
            leftFollowers[i].setSecondaryCurrentLimit(120, 0);
            rightFollowers[i].setSmartCurrentLimit(limit);
            rightFollowers[i].setSecondaryCurrentLimit(120, 0);
        }
    }

    public void initDefaultCommand() {
        if(defaultCommand == null) {
            defaultCommand = new StopDrive();
        }
        defaultCommand.start();
    }

    public void setMotors(double left, double right) {
        SmartDashboard.putNumber("LeftSideOutput", left);
        SmartDashboard.putNumber("RightSideOutput", right);
        leftLeader.set(left * speedMultiplier);
        rightLeader.set(right * speedMultiplier);
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


    /*
    This updates the velocity of the robot.
    Records the last 5 recorded velocities.
    Utilized in getVelocity() which eventually gets printed on SmartDashboard.
    */
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

    //In Encoder pulses per second
    public double getVelocity() {
        return getLeftVelocity() + getRightVelocity() / 2;
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

    public void debug(){
        SmartDashboard.putNumber("Left Encoder", getLeftEncoder());
        SmartDashboard.putNumber("Right Encoder", getLeftEncoder());
        SmartDashboard.putNumber("Right Drivetrain 1", Robot.pdp.getCurrent(1));
        SmartDashboard.putNumber("Right Drivetrain 2", Robot.pdp.getCurrent(0));
        SmartDashboard.putNumber("Left Drivetrain 1", Robot.pdp.getCurrent(14));
        SmartDashboard.putNumber("Left Drivetrain 2", Robot.pdp.getCurrent(15));
        SmartDashboard.putNumber("Measured Velocity", getVelocity());
    }
}  