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
    public TalonSRX elevator_talon;



    public Elevator(){
        elevator_talon = new TalonSRX(RobotMap.ELEVATOR_TALON);
    }

    public void setElevator(double output) {
        elevator_talon.set(ControlMode.PercentOutput, output);

    }

    public double getElevatorVoltage(){
        return elevator_talon.getMotorOutputVoltage();
    }

    @Override
    protected void initDefaultCommand() {
        // TODO Auto-generated method stub

    }

    public void debug(){
        SmartDashboard.putNumber("Elevator Voltage -", getElevatorVoltage());
    }
    
}
