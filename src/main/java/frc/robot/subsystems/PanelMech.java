package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Solenoid;

import frc.robot.Constants;
import frc.robot.RobotMap;

public class PanelMech extends Subsystem {
    private CANSparkMax panelMotor;
    private Solenoid mechNeck; //It's an inside joke please don't delete

    public PanelMech() {
        panelMotor = new CANSparkMax(RobotMap.PANELMECH_NEO, MotorType.kBrushless);
        panelMotor.setOpenLoopRampRate(Constants.kNeoRampTime);
        mechNeck = new Solenoid(RobotMap.kPCM, RobotMap.PANELMECH_SOLENOID);
    }
    public void setPanelMech(final double output) {
        panelMotor.set(output);
    }
    public double getPanelMechVelocity(){
        return panelMotor.getEncoder().getVelocity();
    }
    public double getPanelMech() {
        return panelMotor.getEncoder().getCountsPerRevolution();
    }
    public void setMechNeck(boolean extended) {
        mechNeck.set(extended);
    }
    public boolean getMechNeck() {
        return mechNeck.get(); 
    }
    @Override 
    protected void initDefaultCommand() {
        //defaultCommand.start();
    }

    public void debug() {
        SmartDashboard.putNumber("Control Panel Mech NEO Velocity -", getPanelMechVelocity());
        SmartDashboard.putNumber("Control Panel Mech NEO CPR -", getPanelMechVelocity());
        SmartDashboard.putBoolean("Neck Position", getMechNeck());
    }
}