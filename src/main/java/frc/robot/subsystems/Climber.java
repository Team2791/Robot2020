/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Gyro;
import frc.robot.Constants;
import frc.robot.RobotMap;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.GyroBase;
import edu.wpi.first.wpilibj.Joystick;
import frc.robot.commands.*;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class Climber extends Subsystem { 
    private CANSparkMax winch_Neo;
    private CANSparkMax selfClimb_Neo;

    public static double bottom_penetrometer;
    public static double top_penetrometer;
    public static double winchSpeed = Constants.CLIMBER_CREEP; 


    private Solenoid Extender;
    public GyroBase climbGyro;
    public double angle;
    private Gyro gyro;

    public static Joystick driverStick; 
    private Button driverA, driverB, driverY, driverX;

    public Climber(){
        winch_Neo = new CANSparkMax(RobotMap.WINCH_NEO, MotorType.kBrushless);
        Extender = new Solenoid(RobotMap.kPCM, RobotMap.CLIMB_SOLENOID);
        selfClimb_Neo= new CANSparkMax(RobotMap.SELFCLIMB_NEO, MotorType.kBrushless);
        driverStick = new Joystick(0);
        driverY = new JoystickButton(driverStick,4);
        driverB = new JoystickButton(driverStick,2);
        driverA = new JoystickButton(driverStick, 1);
        driverX = new JoystickButton(driverStick, 3);

       
        winch_Neo.setIdleMode(IdleMode.kBrake);
        
        climbGyro = new GyroBase(){
    
        
            @Override
            public void close() throws Exception {
                // TODO Auto-generated method stub
                
            }
        
            @Override
            public void reset() {
                // TODO Auto-generated method stub
                
            }
        
            @Override
            public double getRate() {
                // TODO Auto-generated method stub
                return 0;
            }
        
            @Override
            public double getAngle() {
                // TODO Auto-generated method stub
                return 0;
            }
        
            @Override
            public void calibrate() {
                // TODO Auto-generated method stub
                
            }
        
        
    
        
    };

}

    public void initDefaultCommand() {
        //   setDefaultCommand(new DefaultExtension());
        }

    
        public void setPinExtender(boolean extended){
            Extender.set(extended);
        }
        public void setPinExtenderByButton(){
            if(driverY.get()){
                Extender.set(true);
            }
            else if(driverB.get()){
                Extender.set(false);
            }
        }

        public boolean getPinExtender(){
            return Extender.get();
        }
    
        public void setWinchOutput(double outputWinch){
            winch_Neo.set(outputWinch);
        }

        public void setWinchOutputByButton(){
            if (driverA.get()){
                winch_Neo.set(1);
            }
            else if (driverX.get()){
                winch_Neo.set(-1);
            }
            else {
                winch_Neo.set(0);
            }
        }

        public void calibrateGyro(){
            climbGyro.calibrate();
        }

        public void setSelfClimbOutput(double outputSelf){
            selfClimb_Neo.set(outputSelf);
        }

        public double getGyroAngle() {
            return climbGyro.getAngle();        
        }
    
        public void debug(){
            SmartDashboard.putBoolean("Pin Extender Status - ", getPinExtender());
            //SmartDashboard.putNumber("Gyro angle", climbGyro.getAngle());
        }

	// public void setSelfClimbOutput(double turn) {
	// }
}
    