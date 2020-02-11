/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.constants;
import frc.robot.RobotMap;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Solenoid;
import frc.robot.commands.*;
import frc.robot.commands.ArmClimb.DefaultExtension;

public class Climber {
    private CANSparkMax Winch_Neo;

    public static double bottom_penetrometer;
    public static double top_penetrometer;
    public static double winchSpeed = constants.kCLimberCreep; 


    private Solenoid pinExtender;
    private boolean isextended;

    public Climber(){
        Winch_Neo = new CANSparkMax(RobotMap.WINCH_NEO, MotorType.kBrushless);
        pinExtender = new Solenoid(RobotMap.kPCM, RobotMap.kClimbSolenoid);
        
    }
    public void initDefaultCommand() {
        setDefaultCommand(new DefaultExtension());
        }


    //public boolean getPinExtender(){
      //  return pinExtender.get();
   // }

    public void setPinExtender(boolean extended){
        pinExtender.set(extended);
        isextended = extended;
     }

    public boolean getPinExtender(){
        return pinExtender.get();
    }
    public void debug(){
        SmartDashboard.putBoolean("Pin Extender Status - ", getPinExtender());
     }

    
  

}


//lfdhsouhoeskj;fhdskjfhdsaijfhfassij;fhsd;iufs
 