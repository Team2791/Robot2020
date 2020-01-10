package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;


import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.commands.StopDrive;
import frc.robot.util.Util;

public class Drivetrain extends Subsystem {
    private CANSparkMax leftLeader;
    private CANSparkMax rightLeader;
    private TalonSRX l;
    private TalonSRX r;
    private final boolean isReversing = false;
    private int[] leftVelocities;
    private int[] rightVelocities;
    private StopDrive defaultCommand;

    public Drivetrain() {
        leftLeader = new CANSparkMax(RobotMap.kLeftLeader, MotorType.kBrushless);
        leftLeader.setOpenLoopRampRate(Constants.kNeoRampTime);
        
        rightLeader = new CANSparkMax(RobotMap.kRightLeader, MotorType.kBrushless);
        rightLeader.setOpenLoopRampRate(Constants.kNeoRampTime);

        l = new TalonSRX(24);
        r = new TalonSRX(23);
    }

    public void initDefaultCommand() {
        if (defaultCommand == null) {
            defaultCommand = new StopDrive();
        }
        defaultCommand.start();
    }

    public int getVelocityr() {
        return r.getSelectedSensorVelocity(0);
    }

    public int getVelocityl() {
        return l.getSelectedSensorVelocity(0);
    }

    public void setMotors(final double left) {
        leftLeader.set(-1);
    }

    public void setLeftNeo(final double left) {
        leftLeader.set(left);
    }

    public void setRightNeo(final double right) {
        rightLeader.set(right);
    }

    public void setLeftTalon(final double left) {
        l.set(ControlMode.PercentOutput, left);
    }

    public void setRightTalon(final double right) {
        r.set(ControlMode.PercentOutput, right);
    }

    public boolean isLeftTalonForward() {
        if (l.getMotorOutputVoltage() > 0) {
            return true;
        } else if (l.getMotorOutputVoltage() == 0) {
            return false;
        } else {
            return false;
        }
    }

    public boolean isLeftTalonBackwards() {
        if (l.getMotorOutputVoltage() < 0) {
            return true;
        } else if (l.getMotorOutputVoltage() == 0) {
            return false;
        } else {
            return false;
        }
    }

    public boolean isRightTalonForward() {
        if (r.getMotorOutputVoltage() > 0) {
            return true;
        } else if (r.getMotorOutputVoltage() == 0) {
            return false;
        } else {
            return false;
        }
    }

    public boolean isRightTalonBackward() {
        if (r.getMotorOutputVoltage() < 0) {
            return true;
        } else if (r.getMotorOutputVoltage() == 0) {
            return false;
        } else {
            return false;
        }
    }

    public boolean isLeftNeoForward() {
        if (leftLeader.getEncoder().getVelocity() > 0) {
            return true;
        } else if (r.getMotorOutputVoltage() == 0) {
            return false;
        } else {
            return false;
        }
    }

    public boolean isLeftNeoBackwards() {
        if (leftLeader.getEncoder().getVelocity() < 0) {
            return true;
        } else if (r.getMotorOutputVoltage() == 0) {
            return false;
        } else {
            return false;
        }
    }

    public int getLeftEncoder() {
        return (int)(leftLeader.getEncoder().getPosition() + 0.5); //+1/2 for rounding
    }

    public int getRightEncoder() {
        return (int)(rightLeader.getEncoder().getPosition() + 0.5); //+1/2 for rounding
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
    public double getAverageDist() {
		return Util.average(getLeftDistance(), getRightDistance());
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

	
    public void debug() {
        SmartDashboard.putBoolean("Left Talon - Backwards", isLeftTalonBackwards());
        SmartDashboard.putBoolean("Left Talon - Forwards", isLeftTalonForward());
        SmartDashboard.putBoolean("Right Talon - Backwards", isRightTalonBackward());
        SmartDashboard.putBoolean("Right Talon - Forwards", isRightTalonForward());
        SmartDashboard.putBoolean("Left Neo - Forwards", isLeftNeoForward());
        SmartDashboard.putBoolean("Left Neo - Backwards", isLeftNeoBackwards());
    }
}  