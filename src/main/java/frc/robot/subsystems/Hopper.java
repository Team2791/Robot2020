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
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class Hopper extends Subsystem {
    private CANSparkMax hopper;

    
    public Hopper(){
        hopper = new CANSparkMax(RobotMap.HOPPER_NEO, MotorType.kBrushless);
    }

    public void setHopper(final double output){
        hopper.set(output);
    }
    public double getHopperVelocity() {
         return hopper.getEncoder().getVelocity();
         }

    public void debug(){
        SmartDashboard.putNumber("Hopper Velocity - ", getHopperVelocity());

    }
    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub

    }
}
