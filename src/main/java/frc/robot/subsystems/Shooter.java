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
        shooter_leader = new CANSparkMax(RobotMap.SHOOTER_LEADER, MotorType.kBrushless);
        shooter_leader.setOpenLoopRampRate(Constants.kNeoRampTime);
        shooter_follower= new CANSparkMax(RobotMap.SHOOTER_FOLLOWER, MotorType.kBrushless);
        shooter_follower.setOpenLoopRampRate(Constants.kNeoRampTime);
    }

    public void setShooter(final double output){
        shooter_leader.set(output);
        shooter_follower.set(output*-1);
    }
    public double getShooterVelocity1(){
        return shooter_leader.getEncoder().getVelocity();
    }
    public double getShooterVelocity2(){
        return shooter_follower.getEncoder().getVelocity();
    }
    public double getShooter1(){
        return shooter_leader.getEncoder().getCountsPerRevolution();
    }
    public double getShooter2(){
        return shooter_follower.getEncoder().getCountsPerRevolution();
    }
    @Override
    protected void initDefaultCommand() {
        // defaultCommand = new MoveShooterPassive();
        // // TODO Auto-generated method stub
        // defaultCommand.start();

    }

    public void debug() {
        SmartDashboard.putNumber("Shooter Neo Velocity 1-", getShooterVelocity1());
        SmartDashboard.putNumber("Shooter Neo Velocity 2-", getShooterVelocity2());
        SmartDashboard.putNumber("Shooter Neo CPR 1-", getShooter1());
        SmartDashboard.putNumber("Shooter Neo CPR 2-", getShooter2());
        SmartDashboard.putNumber("Shooter Neo ramp rate-",Constants.kNeoRampTime);
    }
}
