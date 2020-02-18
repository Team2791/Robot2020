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
import frc.robot.commands.MoveShooter;
import frc.robot.commands.MoveShooterPassive;
import frc.robot.util.Util;

/**
 * Add your docs here.
 */
public class Intake extends Subsystem {
    private CANSparkMax Intake_Motor;
    private MoveShooterPassive defaultCommand;

    public Intake(){
        Intake_Motor = new CANSparkMax(RobotMap.kIntake, MotorType.kBrushless);
        Intake_Motor.setOpenLoopRampRate(Constants.kNeoRampTime);

        Intake_Motor.set(0);
    }

    @Override
    protected void initDefaultCommand() {

    }

    public void debug() {
    }
}
