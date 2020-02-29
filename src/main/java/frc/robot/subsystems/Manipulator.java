package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Solenoid;

import frc.robot.Constants;
import frc.robot.RobotMap;

public class Manipulator extends Subsystem {

    private CANSparkMax manipulator_neo;
    private Solenoid extender;

    public Manipulator() {
        manipulator_neo  = new CANSparkMax(RobotMap.MANIPULATOR_NEO, MotorType.kBrushless);
        manipulator_neo.setOpenLoopRampRate(Constants.kNeoRampTime);
        extender = new Solenoid(RobotMap.kPCM, RobotMap.MANIPULATOR_SOLENOID);
    }

    public void initDefaultCommand() {
            //TODO: Find out what goes in here
        }

    public void setManipulator(final double velocity) {
        manipulator_neo.set(velocity);
    }
    public boolean isRetracted() {
        //I think extender.get() returns false when it's extended
        return !extender.get();
    }
    public void setExtended() {
        extender.set(true);
    }
    public void setRetracted() {
        extender.set(false);
    }
    public boolean getRetracted() {
        return !extender.get();
    }
    
    public void debug() {
        SmartDashboard.putBoolean("Manipulator Extender Solenoid", extender.get());
    }
}  