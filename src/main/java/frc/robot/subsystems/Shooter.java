/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.commands.MoveShooter;
import frc.robot.commands.MoveShooterPassive;
import frc.robot.util.Util;

/**
 * Add your docs here.
 */
public class Shooter extends Subsystem {
    private CANSparkMax shooterLeft, shooterRight;
    private MoveShooterPassive defaultCommand;

    public Shooter(){
        shooterLeft = new CANSparkMax(RobotMap.kShooterLeft, MotorType.kBrushless);
        shooterLeft.setOpenLoopRampRate(Constants.kNeoRampTime);
        shooterRight = new CANSparkMax(RobotMap.kShooterRight, MotorType.kBrushless);
        shooterRight.setOpenLoopRampRate(Constants.kNeoRampTime);

        shooterLeft.set(0);
        shooterRight.set(0);

        setBrakeMode(false);
    }

    public void setShooter(final double output){
        
    }

    @Override
    protected void initDefaultCommand() {

    }

    public void setBrakeMode(boolean isbrake) {
        IdleMode mode = isbrake ? IdleMode.kBrake : IdleMode.kCoast;
        shooterLeft.setIdleMode(mode);
        shooterRight.setIdleMode(mode);
    }

    public void debug() {
        // SmartDashboard.putNumber("Shooter Neo Velocity -", getShooterVelocity());
        // SmartDashboard.putNumber("Shooter Neo CPR -", getShooter());
    }
}
