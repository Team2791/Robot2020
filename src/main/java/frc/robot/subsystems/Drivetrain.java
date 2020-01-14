package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;


import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.util.Util;

public class Drivetrain extends Subsystem {

    public CANSparkMax drivetrain_neo;

    public Drivetrain() {
        drivetrain_neo  = new CANSparkMax(RobotMap.DRIVETRAIN_NEO, MotorType.kBrushless);
        drivetrain_neo.setOpenLoopRampRate(Constants.kNeoRampTime);
    }

    public void initDefaultCommand() {

        }


    public void setMotors(final double left) {
        drivetrain_neo.set(left);
    }
    public void debug() {
    }
}  