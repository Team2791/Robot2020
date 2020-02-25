
/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
//IMPORTED FROM CLIMBER-MAAZ BRANCH!!!

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Constants;
import frc.robot.RobotMap;
// import frc.robot.commands.MoveShooter;
// import frc.robot.commands.MoveShooterPassive;
import frc.robot.util.Util;

/**
 * Add your docs here.
 */
public class PanelMech extends Subsystem {
    private CANSparkMax panelMech_motor;
    private Solenoid panelMech_solenoid;
    //private MoveShooterPassive defaultCommand;

    public PanelMech(){
        panelMech_motor = new CANSparkMax(RobotMap.PANEL_NEO, MotorType.kBrushless);
        panelMech_motor.setOpenLoopRampRate(Constants.kNeoRampTime);
        panelMech_motor.set(0);

        panelMech_solenoid= new Solenoid(RobotMap.kPCM, RobotMap.PANEL_SOLENOID);
    }
    

    protected void initDefaultCommand() {

    }

    public void setPanelMech(final double velocity) {
        panelMech_motor.set(velocity);
    
    }
    
    public void extendPanelMech(boolean extend) {
        panelMech_solenoid.set(extend);
    
    }
    
    public boolean getRetracted(){
        return panelMech_solenoid.get();
    }
    
    public void debug() {
    }
}