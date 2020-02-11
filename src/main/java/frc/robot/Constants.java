package frc.robot;


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
    
    public static final double ShooterkP = 6e-4;
    public static final double ShooterkI = 0;
    public static final double ShooterkD = 1e-5;
    public static final double ShooterkIz = 0;
    public static final double ShooterkFF = 0;
    public static final double ShooterMaxOutput = 1;
    public static final double ShooterMinOutput = -1;   //  max and min outputs that pidcontroller can send to the sparkmax
    public static final double ShootermaxRPM = 4500;
    public static final double kGravity = 32.1741;  //acceleration due to gravity in ft/s/s
    public static final double ShooterDiameter = 6; //inches
    public static final double ShooterGearing = 2; //Shooter spins twice for every one time motor spins
    public static final double kDrag = 1;
    public static final double kMagnus = 1;

    public static final double DrivekP = 6e-4;
    public static final double DrivekI = 0;
    public static final double DrivekD = 1e-5;
    public static final double DriveIz = 0;
    public static final double DrivekFF = 0;
    public static final double DriveMaxOutput = 1;
    public static final double DriveMinOutput = -1;
    public static final double DrivemaxRPM = 4500;

    public static final double kDistInnerOuter = 8;

    // public static final double kCamOffset = 0;
    //Joystick constant
    public static final double DEADZONE = 0.4;


	public static final double ELEVATOR_OUTPUT = 1; //was at 1


	public static final double HOPPER_OUTPUT = -.17; //35% is sweet spot      slowed down to 17%


    public static final double SHOOTER_OUTPUT = 0.1; //89% for near trench      40% for wall shot       100% too short for long shot


	public static final double DRIVETRAIN_OUTPUT = -.5;


	public static final double SHOOTER_OUTPUT_PASSIVE = -.1;


    public static final double MANUAL_POWER = .2;

}