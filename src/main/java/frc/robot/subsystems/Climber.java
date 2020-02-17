/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.interfaces.Accelerometer;
import edu.wpi.first.wpilibj.interfaces.Accelerometer.Range;
import frc.robot.Constants;
import frc.robot.RobotMap;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.AnalogAccelerometer;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.GyroBase;
import frc.robot.commands.*;
import frc.robot.commands.Climb.ActivePosition;
import frc.robot.commands.Climb.DefaultExtension;

public class Climber extends Subsystem { 
    private CANSparkMax winch_Neo;
    private CANSparkMax selfClimb_Neo;


    public static double bottom_penetrometer;
    public static double top_penetrometer;
    public static double winchSpeed = Constants.kCLimberCreep; 


    private Solenoid Extender;
    private boolean isextended;
    private GyroBase climbGyro;
    public double angle;
    
    public Climber(){
        winch_Neo = new CANSparkMax(RobotMap.WINCH_NEO, MotorType.kBrushless);
        Extender = new Solenoid(RobotMap.kPCM, RobotMap.kClimbSolenoid);
        selfClimb_Neo= new CANSparkMax(RobotMap.SELFCLIMB_NEO, MotorType.kBrushless);
     }
    
        

    public void initDefaultCommand() {
     //   setDefaultCommand(new DefaultExtension());
        }

    
    public void setPinExtender(boolean extended){
        Extender.set(extended);
     }

    public boolean getPinExtender(){
        return Extender.get();
    }
    public void setWinchOutput(double outputWinch){
        winch_Neo.set(outputWinch);
    }

    public void setSelfClimbOutput(double outputWinch){
        winch_Neo.set(outputWinch);
    }

    public double getGyroAngle() {

          angle=climbGyro.getAngle();
            return climbGyro.getAngle();
             

    }
    
    public void debug(){
        SmartDashboard.putBoolean("Pin Extender Status - ", getPinExtender());
        SmartDashboard.putNumber("Gyro angle", climbGyro.getAngle());
     }

    
  

}


//lfdhsouhoeskj;fhdskjfhdsaijfhfassij;fhsd;iufs
 