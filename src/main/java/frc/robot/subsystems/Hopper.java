/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.util.IrSensor;

/**
 * Add your docs here.
 */
public class Hopper extends Subsystem {

    public CANSparkMax hopper_horizontal;
    public CANSparkMax hopper_vertical;
    public Solenoid hopper_stopper; 
    public IrSensor irSensor;

    
    public Hopper(){
        hopper_horizontal = new CANSparkMax(RobotMap.HORIZONTAL_HOPPER, MotorType.kBrushless);
        hopper_vertical = new CANSparkMax(RobotMap.VERTICAL_HOPPER, MotorType.kBrushless); 
        hopper_stopper = new Solenoid(RobotMap.kPCM, RobotMap.HOPPER_SOLENOID);
        irSensor = new IrSensor(RobotMap.kPDP);

    }

    public void setHopper(final double output, final double vOutput){
        hopper_horizontal.set(output);
        hopper_vertical.set(-vOutput);
    }
    // public double getHopperVoltage() {
    //     return hopper_neo.ge
    // }
    public boolean isRetracted() {
        return !hopper_stopper.get();
    }
    public void setRetracted(boolean retract) {
        hopper_stopper.set(retract);
    }
    public boolean getRetracted() {
        return hopper_stopper.get();
    }

    public int getIRSensor() {
        return irSensor.getValue();
    }

    public double getHorizontalCurrent() {
        return hopper_horizontal.getOutputCurrent();
    }

    public double getVerticalCurrent() {
        return hopper_vertical.getOutputCurrent();
    }

    public boolean isBall(){
        if(Constants.BALL_VALUE < getIRSensor()){
            return true;
        }
        else{
            return false;
        }
    }

    public void poopBall(){
        if(Constants.BALL_VALUE < getIRSensor()){
            setHopper(Constants.HOPPER_LOADING_HORIZONTAL_OUTPUT, Constants.HOPPER_VERTICAL_OUTPUT);
        } else{
            setHopper(0, 0);
        }
    }

    public void debug(){
        //SmartDashboard.putNumber("Hopper Voltage - ", getHopperVoltage());
        SmartDashboard.putNumber("IR Value", irSensor.getValue());
        SmartDashboard.putBoolean("Got Balls?", isBall());
        SmartDashboard.putNumber("Horizontal Current", getHorizontalCurrent());
        SmartDashboard.putNumber("Vertical Current", getVerticalCurrent());


    }
    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub

    }
}
