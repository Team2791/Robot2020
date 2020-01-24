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
import frc.robot.util.Util;


import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Elevator extends Subsystem {
    private CANSparkMax elevator;

    public Elevator(){
        elevator = new CANSparkMax(RobotMap.ELEVATOR_NEO, MotorType.kBrushless);
        elevator.setOpenLoopRampRate(Constants.kNeoRampTime);
    }

    public void setElevator(double output) {
        elevator.set(output);

    }

    public double getElevatorVelocity(){
         return elevator.getEncoder().getVelocity();
     }

    
    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub

    }

    public void debug(){
        SmartDashboard.putNumber("Elevator Velocity -", getElevatorVelocity());
    }
    
}
