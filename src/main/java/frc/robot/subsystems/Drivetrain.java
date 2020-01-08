package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;


import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;

import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.commands.DriveWithJoystickLeft;
import frc.robot.commands.StopDrive;
import frc.robot.Robot;
import frc.robot.util.Util;

// import edu.wpi.first.wpilibj.ADXRS450_Gyro;

public class Drivetrain extends Subsystem {
    private CANSparkMax leftLeader;
    private CANSparkMax rightLeader;
    private TalonSRX l;
    private TalonSRX r;
    private TalonSRX[] leftFollowers;
    private TalonSRX[] rightFollowers;
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
    // private ADXRS450_Gyro gyro;
    // private boolean gyroDisabled = false;


    private double speedMultiplier;

    private DriveWithJoystickLeft defaultCommand;

    public Drivetrain() {
        leftLeader = new CANSparkMax(RobotMap.kLeftLeader, MotorType.kBrushless);
        leftLeader.setOpenLoopRampRate(Constants.kNeoRampTime);
        
     //   rightLeader = new CANSparkMax(RobotMap.kRightLeader, MotorType.kBrushless);
      //  rightLeader.setInverted(true);
      //  rightLeader.setOpenLoopRampRate(Constants.kNeoRampTime);

        l = new TalonSRX(24);
        r = new TalonSRX(23);

        // leftFollowers = new CANSparkMax[RobotMap.kLeftFollowers.length];
        // for(int i = 0; i < leftFollowers.length; ++i) {
        //     leftFollowers[i] = new CANSparkMax(RobotMap.kLeftFollowers[i], MotorType.kBrushless);
        //     leftFollowers[i].setIdleMode(IdleMode.kCoast);
        //     leftFollowers[i].setOpenLoopRampRate(Constants.kNeoRampTime);
        //     leftFollowers[i].follow(leftLeader);
        // }

    //     rightFollowers = new CANSparkMax[RobotMap.kRightFollowers.length];
    //     for(int i = 0; i < rightFollowers.length; ++i) {
    //         rightFollowers[i] = new CANSparkMax(RobotMap.kRightFollowers[i], MotorType.kBrushless);
    //         rightFollowers[i].setInverted(true);
    //         rightFollowers[i].setIdleMode(IdleMode.kCoast);
    //         rightFollowers[i].setOpenLoopRampRate(Constants.kNeoRampTime);
    //         rightFollowers[i].follow(rightLeader);
    //     }

    //     lineSensors = new AnalogInput[4];
    //     blueLED = new Solenoid(RobotMap.kPCM, RobotMap.kLEDBlueSolenoid);
    //     greenLED = new Solenoid(RobotMap.kPCM, RobotMap.kLEDGreenSolenoid);
    //     for(int i = 0; i < 4; ++i) {
    //         lineSensors[i] = new AnalogInput(RobotMap.kLineSensors[i]);
    //     }

    //     setDriveSpeed(false);
    //     setBrakeMode(true);
    //     setMotors(0, 0);

    //     setCurrentLimit(Constants.kNeoAmpLimit);
    //     lineFound = false;

    //     leftVelocities = new int[] {0, 0, 0, 0, 0};
    //     rightVelocities = new int[] {0, 0, 0, 0, 0};
    //     frameCounter = 0;
    //     // initGyro();
    // }
    }
    public void setLimit(int limit) {
        if(pCurrent != limit) {
            setLimit(limit);
        }
        // initGyro();
    }

    private void setCurrentLimit(int limit) {
        leftLeader.setSmartCurrentLimit(limit);
        leftLeader.setSecondaryCurrentLimit(120, 0);
        rightLeader.setSmartCurrentLimit(limit);
        rightLeader.setSecondaryCurrentLimit(120, 0);
        // for(int i = 0; i < leftFollowers.length; ++i) {
        //     leftFollowers[i].setSmartCurrentLimit(limit);
        //     leftFollowers[i].setSecondaryCurrentLimit(120, 0);
        //     rightFollowers[i].setSmartCurrentLimit(limit);
        //     rightFollowers[i].setSecondaryCurrentLimit(120, 0);
        // }
    }
    // public void initGyro(){
    //     try {
	// 		gyro = new ADXRS450_Gyro();// SPI.Port.kOnboardCS1
	// 		gyro.calibrate(); // takes 5 seconds
	// 		gyro.reset();
	// 		System.out.println("Gyro is working! :-)");
	// 	} catch (NullPointerException e) {
	// 		gyroDisabled = true;
	// 		System.out.println("Gyro is unplugged, Disabling Gyro");
	// 	}
    // }
    public void initDefaultCommand() {
            defaultCommand = new DriveWithJoystickLeft();
        }
    public int getVelocityr() {
        return r.getSelectedSensorVelocity(0);
    }
    public int getVelocityl() {
        return l.getSelectedSensorVelocity(0);
    }
    public void setMotors(double left) {
        SmartDashboard.putNumber("LeftSideOutput", left);
        System.out.println("This is set motors");

         if(Math.abs(left - right) <= 0.2 && getLeftVelocity() < -1 && getRightVelocity() < -1 && (left + right) / 2 > 0) {
             rightLeader.set(Math.max(0, right * speedMultiplier));
            rightLeader.set(0);
            leftLeader.set(0);
            l.set(ControlMode.PercentOutput, left);

            }else{     rightLeader.set(right * speedMultiplier);
            r.set(ControlMode.PercentOutput, left);
            l.set(ControlMode.PercentOutput, left);
              }

        
    }
    public void setLeftNeo(double left){
        leftLeader.set(left);
    }
    public void setRightNeo(double right){
        rightLeader.set(right);
    }
    public void setLeftTalon(double left){
        l.set(ControlMode.PercentOutput, left);
    }
    public void setRightTalon(double right){
        r.set(ControlMode.PercentOutput, right);
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

    // public void update() {
    //     int leftEnc = getLeftEncoder();
    //     int rightEnc = getRightEncoder();

    //     int lVel = leftEnc - pLeftEnc;
    //     int rVel = rightEnc - pRightEnc;
    //     getLineSensors();
    //     pLeftEnc = leftEnc;
    //     pRightEnc = rightEnc;
    //     setVelocity(lVel, rVel);
    //     if(getVelocity() >= 0) {
    //         isReversing = false;
    //     } else {
    //         isReversing = true;
    //     }
    //     ++frameCounter;
    // }

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

    public double getAverageDist() {
		return Util.average(getLeftDistance(), getRightDistance());
		// right side commented out due to potential wiring issues
		// return getLeftDistance();
    }
    public double getLeftDistance() {
        //Multiply the # of ticks by distance per tick in feet, dont change distance per pulse
        //Note that leftLeader.getEncoder().getPosition()
        //YOU NEED TO FIX THIS FUNCTION TO RETURN THE DISTANCE TRAVELED IN FEET
        //Left side is returning value between 0 and 1 for how complete the rotation is
		return leftLeader.getEncoder().getPosition() * ((0.5 * Math.PI)*.12);
    }
    
    public double getRightDistance() {
		return rightLeader.getEncoder().getPosition() * ((0.5 * Math.PI)*.12);
    }

	// public double getGyroAngle() {
	// 	if (!gyroDisabled)
	// 		return gyro.getAngle();
	// 	System.err.println("Gyro is Disabled, Angle is Incorrect");
	// 	return 0.0;
	// }

	// public double getGyroRate() {
	// 	if (!gyroDisabled)
	// 		return gyro.getRate();
	// 	System.err.println("Gyro is Disabled, Rate is Incorrect");
	// 	return 0.0;
	// }

	// public double getGyroAngleInRadians() {
	// 	return getGyroAngle() * (Math.PI / 180);
    // }

    // public void resetGyro() {
	// 	if (!gyroDisabled) {
	// 		gyro.reset();
	// 	} else {
	// 		System.err.println("Gyro is Disabled, Unable to Reset");
	// 	}
	// }

	// public boolean getGyroDisabled() {
	// 	return gyroDisabled;
	// }

	// public void calibrateGyro() {
	// 	if (!gyroDisabled) {
	// 		System.out.println("Gyro calibrating");
	// 		gyro.calibrate();
	// 		System.out.println("Done calibrating " + " The current rate is " + gyro.getRate());
	// 	}
    // }
    
    // public int getLineSensors() {
    //     int res = 0;
    //     res |= lineSensors[0].getAverageVoltage() > Constants.kLineVoltCutoff ? 1 : 0;
    //     res |= lineSensors[1].getAverageVoltage() > Constants.kLineVoltCutoff ? 2 : 0;
    //     res |= lineSensors[2].getAverageVoltage() > Constants.kLineVoltCutoff ? 4 : 0;
    //     res |= lineSensors[3].getAverageVoltage() > Constants.kLineVoltCutoff ? 8 : 0;
    //     lineFound = (res & 15) > 0;
    //     // setBlueLED(lineFound);
    //     return res;
    // }

    // public boolean isLineFound(){
    //     return lineFound;
    // }

    // public void setGreenLED(boolean state){
    //     greenLED.set(state);
    // }

    // public void setBlueLED(boolean state){
    //     blueLED.set(state);
    // }
    
    // public double getLineSensor(int index)
    // {
    //     return lineSensors[index].getAverageVoltage();
    // }

    public void debug() {
        
        // SmartDashboard.putBoolean("Line Found", lineFound);
        // SmartDashboard.putNumber("DT - Gyro angle", getGyroAngle());
        // SmartDashboard.putNumber("Line Voltage" + 0, getLineSensor(0));
        // SmartDashboard.putNumber("Line Voltage" + 1, getLineSensor(1));
        // SmartDashboard.putNumber("Line Voltage" + 2, getLineSensor(2));
        // SmartDashboard.putNumber("Line Voltage" + 3, getLineSensor(3));
    }
}  