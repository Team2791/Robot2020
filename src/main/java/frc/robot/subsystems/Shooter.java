/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
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
    private CANSparkMax shooter_neo;
    private MoveShooterPassive defaultCommand;



    public Shooter(){
        shooter_neo = new CANSparkMax(RobotMap.SHOOTER_NEO, MotorType.kBrushless);
        shooter_neo.setOpenLoopRampRate(Constants.kNeoRampTime);
    }

    public void setShooter(final double output){
        shooter_neo.set(output);
    }
    public double getShooterVelocity(){
        return shooter_neo.getEncoder().getVelocity();
    }
    public double getShooter(){
        return shooter_neo.getEncoder().getCountsPerRevolution();
    }
    @Override
    protected void initDefaultCommand() {
        defaultCommand = new MoveShooterPassive();
        // TODO Auto-generated method stub
        defaultCommand.start();

    }

    public void debug() {
        SmartDashboard.putNumber("Shooter Neo Velocity -", getShooterVelocity());
        SmartDashboard.putNumber("Shooter Neo CPR -", getShooter());
    }
}