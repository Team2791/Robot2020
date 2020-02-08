/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.DigitalOutput;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {

    //Global
    public static final int kPCM = 1;
    public static final int kPDP = 0;

    //Drivetrain
    public static final int kLeftLeader = 1;
    public static final int kRightLeader = 3;
    public static final int[] kLeftFollowers = {24};// check if these are the right ids
    public static final int[] kRightFollowers = {23}; // check if these are the right ids
  //  public static final int[] kLineSensors = {7, 6, 5, 4};
    public static final int[] kLineSensors = {4,5,6,7};
    public static final int kLEDBlueSolenoid = 7;
    public static final int kLEDGreenSolenoid = 6;


    //Lifters
    public static final int kFrontLiftTalon = 23;
    public static final int kBackLiftTalon = 22;
    public static final int kRollerVictor = 34;
    public static final int kLifterHelperVictor = 36;
    public static final int kFrontIrReadout = 1;
    public static final int kBackIrReadout = 0;

    //Elevator
    public static final int kElevatorTalon = 24;
    public static final int kElevatorVictor = 33;
    public static final int kElevatorLimitTop = 0;
    public static final int kElevatorLimitBottom = 0;

    public static final int kGrabberOpen = 0;
    public static final int kGrabberClose = 1;
    public static final int kBreakSolenoid = 4;

    //PDP IDs
    public static final int kPowerFrontLift = 3;
    public static final int kPowerBackLift = 12;
    public static final int kCargoIntakeVictorPDP = 4;

    //HatchManipulator
    public static final int kExtenderHatchSolenoid = 3;
    public static final int kGrabberHatchSolenoid = 1;
    public static final int kAlignerHatchSolenoid = 2;

    //CargoManipulator
    public static final int kRaiseCargoSolenoid = 0;
    public static final int kIntakeVictor = 35;
    public static final int kCargoLimitSwitch = 0;
  
  // Shoooter
    public static final int SHOOTER_NEO = 2;
  
    // Elevator
    public static final int ELEVATOR_TALON = 23;

    //Hopper

    public static final int HOPPER_TALON = 24;
    //Drivetrain
  public static final int DRIVETRAIN_NEO = 3;
  
}
