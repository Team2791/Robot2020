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
    public CANSparkMax vertHopper;
    public CANSparkMax horiHopper;

    
    public Hopper(){
        vertHopper = new CANSparkMax(RobotMap.kVertHopper, MotorType.kBrushless);
        vertHopper.setOpenLoopRampRate(Constants.kNeoRampTime);
        horiHopper = new CANSparkMax(RobotMap.kHoriHopper, MotorType.kBrushless);
        horiHopper.setOpenLoopRampRate(Constants.kNeoRampTime);

        vertHopper.set(0);
        horiHopper.set(0);
    }

    public void setHopper(final double output){
        // hopper_talon.set(ControlMode.PercentOutput, output);
    }
    // public double getHopperVoltage() {
    //     // return hopper_talon.getMotorOutputVoltage();
    //     }

    public void debug(){
        // SmartDashboard.putNumber("Hopper Voltage - ", getHopperVoltage());

    }
    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub

    }
}
