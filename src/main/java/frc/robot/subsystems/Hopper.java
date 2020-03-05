/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Constants;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.util.IrSensor;
import edu.wpi.first.wpilibj.Counter;
import edu.wpi.first.wpilibj.DigitalInput;;
/**
 * Add your docs here.
 */
public class Hopper extends Subsystem {

    public CANSparkMax hopper_horizontal;
    public CANSparkMax hopper_vertical;
    public Solenoid hopper_stopper; 
    public IrSensor entrySensor, upperSensor;
    public DigitalInput limitSwitch = new DigitalInput(9);
    public Counter counter= new Counter(limitSwitch);

    public Hopper(){
        hopper_horizontal = new CANSparkMax(RobotMap.HORIZONTAL_HOPPER, MotorType.kBrushless);
        hopper_vertical = new CANSparkMax(RobotMap.VERTICAL_HOPPER, MotorType.kBrushless); 
        hopper_stopper = new Solenoid(RobotMap.kPCM, RobotMap.HOPPER_SOLENOID);
        // entrySensor = new IrSensor(RobotMap.kPDP);
        // upperSensor = new IrSensor(2);
        
        hopper_horizontal.setIdleMode(IdleMode.kBrake);
        hopper_vertical.setIdleMode(IdleMode.kBrake);

        hopper_horizontal.setSmartCurrentLimit(20);
        hopper_vertical.setSmartCurrentLimit(20);

        counter.clearDownSource();
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

    public void setExtended() {
        hopper_stopper.set(true);
    }
    
    public void setRetracted() {
        hopper_stopper.set(false);
    }
    
    public boolean getRetracted() {
        return hopper_stopper.get();
    }
    
    public int getIRSensor() {
        return entrySensor.getValue();
    }

    public double getHorizontalCurrent() {
        return hopper_horizontal.getOutputCurrent();
    }

    public double getVerticalCurrent() {
        return hopper_vertical.getOutputCurrent();
    }

    public boolean checkJammed(){
        if(getHorizontalCurrent()>Constants.jamCurrent)
            return true;
        else
            return false;
    }

    public boolean isBall(){
        if(Constants.BALL_VALUE < getIRSensor()){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean isSwitchSet() {
        return counter.get() > 0;
    }
    public boolean isUpperSensorTripped() {
        return 745 <= upperSensor.getValue();
    }

    public void loadingWithIR(){
        if(Constants.BALL_VALUE < getIRSensor()){
            if (isUpperSensorTripped()) {
                setHopper(
                Constants.HOPPER_LOADING_HORIZONTAL_OUTPUT,
                0);
            } else {
                setHopper(
                    Constants.HOPPER_LOADING_HORIZONTAL_OUTPUT,
                    Constants.HOPPER_LOADING_VERTICAL_OUTPUT);
            }
        } else{
            setHopper(0, 0);
        }
    }

    public boolean isHopperFull(){
            if (counter.get()==5){
                return true;
            }
            return false;
    }

    

    public void debug(){
        //SmartDashboard.putNumber("Hopper Voltage - ", getHopperVoltage());
        SmartDashboard.putNumber("Entry IR Value", entrySensor.getValue());
        SmartDashboard.putBoolean("Got Balls?", isBall());
        SmartDashboard.putNumber("Horizontal Current", getHorizontalCurrent());
        SmartDashboard.putNumber("Vertical Current", getVerticalCurrent());
        SmartDashboard.putBoolean("Hopper Stopper Out", isRetracted());
        SmartDashboard.putNumber("Upper IR Value", upperSensor.getValue());
        SmartDashboard.putBoolean("Jammed", checkJammed());
        SmartDashboard.putNumber("Counter Value", counter.get());
        SmartDashboard.putBoolean("Limit Switch", limitSwitch.get());
    }
    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub

    }
}
