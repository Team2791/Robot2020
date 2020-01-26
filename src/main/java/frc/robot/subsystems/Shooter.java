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
import com.revrobotics.ControlType;
import com.revrobotics.CANEncoder;
import com.revrobotics.CANPIDController;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.commands.MoveShooter;
import frc.robot.util.Util;

/**
 * Add your docs here.
 */
public class Shooter extends Subsystem {
    private CANSparkMax shooter_leader;
    private CANSparkMax shooter_follower;
    private CANPIDController shooter_pid;
    private double speed;


    public Shooter(){
        shooter_leader = new CANSparkMax(RobotMap.SHOOTER_LEADER, MotorType.kBrushless);
        shooter_leader.setOpenLoopRampRate(Constants.kNeoRampTime);
        shooter_follower= new CANSparkMax(RobotMap.SHOOTER_FOLLOWER, MotorType.kBrushless);
        shooter_follower.setOpenLoopRampRate(Constants.kNeoRampTime);
    }

    public void setShooter(final double output){
        shooter_leader.set(output);
        shooter_follower.follow(shooter_leader, true);
    }
    public void setShooterPid(final double velocity){
        shooter_pid = shooter_leader.getPIDController();
        
        shooter_pid.setP(Constants.kP);
        shooter_pid.setI(Constants.kI);
        shooter_pid.setD(Constants.kD);
        shooter_pid.setIZone(Constants.kIz);
        shooter_pid.setFF(Constants.kFF);
        shooter_pid.setOutputRange(Constants.MinOutput, Constants.MaxOutput);

        shooter_pid.setReference(velocity, ControlType.kVelocity);
        shooter_follower.follow(shooter_leader);

        speed = velocity;
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
    public double getPIDSetpoint(){
        return speed;
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
        SmartDashboard.putNumber("Shooter Speed Set Point", getPIDSetpoint());
        SmartDashboard.putNumber("Shooter Neo CPR 1-", getShooter1());
        SmartDashboard.putNumber("Shooter Neo CPR 2-", getShooter2());
        SmartDashboard.putNumber("Shooter Neo ramp rate-",Constants.kNeoRampTime);
    }
}
