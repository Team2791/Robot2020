/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
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
        shooter_leader = new CANSparkMax(RobotMap.SHOOTER_NEO, MotorType.kBrushless);
        shooter_follower = new CANSparkMax(RobotMap.SHOOTER_NEO, MotorType.kBrushless);
        shooter_leader.setOpenLoopRampRate(Constants.kNeoRampTime);
        shooter_follower.setOpenLoopRampRate(Constants.kNeoRampTime);
        hood_1 = new Solenoid(RobotMap.kPCM, RobotMap.HOOD_1);
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
    public double getShooterVelocity(){
        return shooter_leader.getEncoder().getVelocity();
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
    protected boolean checkWheelSpeed(){
        if(Robot.shooter.getShooterVelocity() >= Constants.SHOOTER_OUTPUT - .01 && Robot.shooter.getShooterVelocity() <= Constants.SHOOTER_OUTPUT + .01){
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
    }
}
