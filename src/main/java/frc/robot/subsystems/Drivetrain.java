package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.CAN;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Constants;
import frc.robot.RobotMap;
import frc.robot.util.Util;

public class Drivetrain extends Subsystem {

    private CANSparkMax leftLeader;
    private CANSparkMax rightLeader;
    private CANSparkMax [] leftFollowers;
    private CANSparkMax [] rightFollowers;



    public Drivetrain() {
        leftLeader  = new CANSparkMax(RobotMap.DRIVETRAIN_NEO, MotorType.kBrushless);
        leftLeader.setOpenLoopRampRate(Constants.kNeoRampTime);
        rightLeader  = new CANSparkMax(RobotMap.DRIVETRAIN_NEO, MotorType.kBrushless);
        rightLeader.setOpenLoopRampRate(Constants.kNeoRampTime);

        leftFollowers = new CANSparkMax[RobotMap.kLeftFollowers.length];
        for(int i = 0; i < leftFollowers.length; ++i) {
            leftFollowers[i] = new CANSparkMax(RobotMap.kLeftFollowers[i], MotorType.kBrushless);
            leftFollowers[i].setOpenLoopRampRate(Constants.kNeoRampTime);
            leftFollowers[i].follow(leftLeader);
        }
        rightFollowers = new CANSparkMax[RobotMap.kLeftFollowers.length];
        for(int i = 0; i < leftFollowers.length; ++i) {
            rightFollowers[i] = new CANSparkMax(RobotMap.kLeftFollowers[i], MotorType.kBrushless);
            rightFollowers[i].setOpenLoopRampRate(Constants.kNeoRampTime);
            rightFollowers[i].follow(rightLeader);
        }
    }

    public void initDefaultCommand() {

        }


    public void setMotors(double left, double right) {
        leftLeader.set(left);
        rightLeader.set(right);
        }
    public void debug() {
    }
}  