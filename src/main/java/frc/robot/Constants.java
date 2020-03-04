package frc.robot;

import edu.wpi.first.wpilibj.Timer;

public final class Constants {
    public static final boolean debugMode = false;


    // Drivetrain constants
    public static final double kFastDrive = 1.0;
    public static final double kSlowDrive = 0.5;

    public static final double kCreep = 0.2;
    public static final double kSlowish = 0.5; //Testing should be 0.5
    public static final double kLineFollowStraight = 0.19; //0.27;
    public static final double kLineFollowTurn = 0.2; //0.5;
    public static final double kLineVoltCutoff = 1.0; //1.4
    public static final double kNeoRampTime = .05;
    public static final int kNeoAmpLimit = 48;
    public static final double kInchTime = 0.1;
    public static final double kCamTurn = 0.5;
    public static final double kCamStraightFast = 0.6;
    public static final double kCamStraightSuperFast = 0.75;
    public static final double kCamStraightMedium = 0.45;
    public static final double kCamStraightSlow = 0.25;
    

    public static final double ShooterTrenchkP = 0.002525;
    public static final double ShooterTrenchkD = 0.22;
    public static final double ShooterTrenchkFF = 0.0002;
    public static final double ShooterWallkP = 0.001500;
    public static final double ShooterWallkD = 0.1;
    public static final double ShooterWallkF = 0.000180;
    public static final double ShooterkIz = 0;
    public static final double ShooterMaxOutput = 1;
    public static final double ShooterMinOutput = -1;   //  max and min outputs that pidcontroller can send to the sparkmax
    public static final double kGravity = 32.1741;  //acceleration due to gravity in ft/s/s
    public static final double ShooterDiameter = 6; //inches
    public static final double ShooterGearing = 2; //Shooter spins twice for every one time motor spins
    public static final double kDrag = 1;
    public static final double kMagnus = 1;

    public static final double jamCurrent = 20;
    public static final double fixSpaceTime = 0.2;

    public static final double DrivekP = 0.025;
    public static final double DrivekI = 0;
    public static final double DrivekD = 0;
    public static final double DriveIz = 0;
    public static final double DrivekFF = 0;
    public static final double DriveMaxOutput = 1;
    public static final double DriveMinOutput = -1;
    public static final double DrivemaxRPM = 4500;

    public static final double LimelightkP = 0.35;
    public static final double LimelightkI = 0.0;
    public static final double LimelightkD = 0.0;

    public static final double kDistInnerOuter = 8;

    public static final double kIrSensorVal = 20.0; //what the value of the ir sensor should read w/o a ball

    // public static final double kCamOffset = 0;
    //Joystick constant
    public static final double DEADZONE = 0.4;


    public static final double ELEVATOR_OUTPUT = -.05;
    

    public static final double HOPPER_LOADING_HORIZONTAL_OUTPUT = .5; 


    public static final double HOPPER_WALL_HORIZONTAL_OUTPUT = 0.86;   // 0.30;   0.70; 


    public static final double HOPPER_VERTICAL_OUTPUT = 1.0;    // .35;    0.80;[\]


    public static final double HOPPER_LOADING_VERTICAL_OUTPUT = .15;
    

    public static final double REVERSE_HOPPER = -.40;


	public static final double SHOOTER_OUTPUT = -.8;


	public static final double DRIVETRAIN_OUTPUT = -1.0;



	public static final double SHOOTER_OUTPUT_PASSIVE = -.1;



    public static final double MANUAL_POWER = .2;


    public static final double INTAKE_MOTORSPEED = 0.37; //Temp value please test it out and do stuff yes

    
    public static final double SHOOTER_VELOCITY = 100; //Temporary value


    public static final double SHOOTER_OUTPUT_LONG = -1;


	public static final double SHOOTER_OUTPUT_WALL = -.4; //changed from .47 //definitely make negative though
    public static final double SHOOTER_OUTPUT_WALL_RPM = -3500;

	public static final double TURN_FACTOR = 0.7;


	public static final int BALL_VALUE = 2425;




     //climber
     public static final double CLIMBER_CREEP = 0.25;
    // public static final double kSelfClimbGoRight = 0.1;
    // public static final double kSelfClimbGoLeft= -0.1;

     //Global
     public static final boolean kToExtendArm = true;

     //Panel Mech
     public static final double PANEL_MECH_CREEP = 0.1;


	public static final double kSelfClimbGoRight = 0; //change


	public static final double kSelfClimbGoLeft = 0; //change
     public static final double PANEL_MECH_FAST = 0.45;


	public static final double HOPPER_LONG_HORIZONTAL_OUTPUT = 0.25;


	public static final double kHopperTimer = 0.12;

}