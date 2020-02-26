/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Constants;

import frc.robot.Robot;
import frc.robot.RobotMap;
// import frc.robot.commands.MoveShooterPassive;
/**
 * Add your docs here.
 */
public class Shooter extends Subsystem {

    private CANSparkMax shooter_leader;
    private CANSparkMax shooter_follower;
    private Solenoid hood_1;
    // private MoveShooterPassive defaultCommand;



    public Shooter(){
        shooter_leader = new CANSparkMax(RobotMap.kShooterLeft, MotorType.kBrushless);
        shooter_follower = new CANSparkMax(RobotMap.kShooterRight, MotorType.kBrushless);
        shooter_leader.setOpenLoopRampRate(Constants.kNeoRampTime);
        shooter_follower.setOpenLoopRampRate(Constants.kNeoRampTime);
        hood_1 = new Solenoid(RobotMap.kPCM, RobotMap.HOOD_SOLENOID);
        
        shooter_leader.getPIDController().setP(Constants.ShooterkP);
        shooter_leader.getPIDController().setFF(Constants.ShooterkFF);
        shooter_leader.getPIDController().setOutputRange(-1, 1);
    }
    public double idealVelocity(double angle, double dist, double height){
        double gravityInches = Constants.kGravity*12;
        angle = Math.toRadians(angle);
        double velocity = Math.sqrt( (gravityInches*Math.pow(dist,2)) / (2*Math.pow(Math.cos(angle),2) * (dist*Math.tan(angle)-height))); //speed in inches/second
        return velocity;
    }
    public double applyDrag(double velocity){
        velocity*=Constants.kDrag;
        return velocity;
    }
    public double applyMagnus(double velocity){
        velocity/=Constants.kMagnus;
        return velocity;
    }
    public double velocityToRPM(double velocity){
        double shooterRotationsPerSecond = velocity/(.5*Constants.ShooterDiameter); //rotation per second
        double shooterRPM = shooterRotationsPerSecond*60;
        double motorRPM = shooterRPM/Constants.ShooterGearing;
        return motorRPM;
    }
    public void setShooter(final double output){
        shooter_leader.set(output);
        shooter_follower.follow(shooter_leader, true);
    }
    public void setShooterPID(final double setpoint){
        shooter_leader.getPIDController().setReference(setpoint, ControlType.kVelocity);
        shooter_follower.follow(shooter_leader, true);
    }
    public double getShooterVelocity(){
        return shooter_leader.getEncoder().getVelocity();
    }

    public double getShooterVoltage(){
        return shooter_leader.getBusVoltage();
    }

    public double getShooterConversionFactor(){
        return shooter_leader.getEncoder().getVelocityConversionFactor();
    }
    public double getShooterVelocity2(){
        double velocity = getShooterVoltage() * getShooterConversionFactor();
        return velocity;
    }
    public boolean isShooterVelocityCorrect(){
        if(getShooterVelocity() != Constants.SHOOTER_VELOCITY){
            return false;
        }
        return true; 
    }
    public double getShooter(){
        return shooter_leader.getEncoder().getCountsPerRevolution();
    }
    public void setHood1(boolean extended) {
        hood_1.set(extended);
    }

    public boolean getHood1(){
        return hood_1.get();
    }
    @Override
    protected void initDefaultCommand() {
        // defaultCommand = new MoveShooterPassive();
        // // TODO Auto-generated method stub
        // defaultCommand.start();

    }
    public boolean checkWheelSpeed_Wall(){
        if(Robot.shooter.getShooterVelocity() >= Constants.SHOOTER_OUTPUT_WALL - .01 && Robot.shooter.getShooterVelocity() <= Constants.SHOOTER_OUTPUT_WALL + .01) {
            return true;
        }
        else{
            return false;
        }
    }
    public boolean checkWheelSpeed_Long() {
        if(Robot.shooter.getShooterVelocity() >= Constants.SHOOTER_OUTPUT_LONG - .01 && Robot.shooter.getShooterVelocity() <= Constants.SHOOTER_OUTPUT_LONG + .01){
            return true;
        }
        else{
            return false;
        }
    }

    public void debug() {
        SmartDashboard.putNumber("Shooter Neo Velocity -", getShooterVelocity());
        SmartDashboard.putNumber("Shooter Neo CPR -", getShooter());
        SmartDashboard.putBoolean("Hood Position", getHood1());
        SmartDashboard.putNumber("Voltage", getShooterVoltage());
        SmartDashboard.putNumber("Calculated Velcoity", getShooterVelocity2());

    }
}
