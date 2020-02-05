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
    private CANSparkMax shooter_leader;
    private CANSparkMax shooter_follower;
    private MoveShooterPassive defaultCommand;



    public Shooter(){
        shooter_leader = new CANSparkMax(RobotMap.SHOOTER_NEO, MotorType.kBrushless);
        shooter_follower = new CANSparkMax(RobotMap.SHOOTER_NEO, MotorType.kBrushless);
        shooter_leader.setOpenLoopRampRate(Constants.kNeoRampTime);
        shooter_follower.setOpenLoopRampRate(Constants.kNeoRampTime);
    }

    public void setShooter(final double output){
        shooter_leader.set(output);
        shooter_follower.follow(shooter_leader,true);
    }
    public double getShooterVelocity(){
        return shooter_leader.getEncoder().getVelocity();
    }
    public double getShooter(){
        return shooter_leader.getEncoder().getCountsPerRevolution();
    }
    @Override
    protected void initDefaultCommand() {
        // defaultCommand = new MoveShooterPassive();
        // // TODO Auto-generated method stub
        // defaultCommand.start();

    }

    public void debug() {
        SmartDashboard.putNumber("Shooter Neo Velocity -", getShooterVelocity());
        SmartDashboard.putNumber("Shooter Neo CPR -", getShooter());
    }
}