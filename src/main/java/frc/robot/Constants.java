package frc.robot;


public final class Constants {
    public static final boolean debugMode = false;


    // Drivetrain constants
    public static final double kFastDrive = 1.0;
    public static final double kSlowDrive = 0.5;
    public static final double kCreep = .3;
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
    
    // public static final double kCamOffset = 0;
    //Joystick constant
    public static final double DEADZONE = 0.4;


	public static final double ELEVATOR_OUTPUT = -.05;


	public static final double HOPPER_OUTPUT = -.20; // 20% is sweet spot


	public static final double SHOOTER_OUTPUT = -.8;


	public static final double DRIVETRAIN_OUTPUT = -1.0;


	public static final double SHOOTER_OUTPUT_PASSIVE = -.1;


	public static final double MANUAL_POWER = .2;


	public static final double TURN_FACTOR = 0.16;

}