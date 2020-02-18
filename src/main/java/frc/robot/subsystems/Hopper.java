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
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;


/**
 * Add your docs here.
 */
public class Hopper extends Subsystem {

    public CANSparkMax hopper_horizontal;
    public CANSparkMax hopper_vertical;
    public Solenoid hopper_stopper; 
    public IrSensor irSensor = new IrSensor(RobotMap.kPDP);

    
    public Hopper(){
        hopper_horizontal = new CANSparkMax(RobotMap.HORIZONTAL_HOPPER, MotorType.kBrushless);
        hopper_vertical = new CANSparkMax(RobotMap.VERTICAL_HOPPER, MotorType.kBrushless); 
        hopper_stopper = new Solenoid(RobotMap.kPCM, RobotMap.HOPPER_SOLENOID);
    }

    public void setHopper(final double output){
        hopper_horizontal.set(output);
        hopper_vertical.set(output);
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

    public void debug(){
        //SmartDashboard.putNumber("Hopper Voltage - ", getHopperVoltage());
        SmartDashboard.putNumber("IR Value", irSensor.getValue());
    }
    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub

    }
}
