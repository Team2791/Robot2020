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
    // public static final double kCamOffset = 0;

    //TODO Basically left dpad has a different funciton after we climb, if you hold left dpad after climb then it lifts us even higher

    // Lifters constants
    public static final int kLifterPotTicksInOneLoopForShutdown = 50;
    public static final int kLiftersLevel2PotValue = 120;
    public static final double kLifterFrontSlowHeightLevel2 = 90;
    // public static final double kLifterCurrentProtectionSpikeTimer = 1.0; //Might need to be higher
    // public static final double kLifterCurrentKiller = 100; //If motor crosses 60 amps after current spike
    public static final double kLifterBackPotTopTravel = 800;
    public static final double kLifterFrontPotTopTravel = 800;
    public static final int kFrontLifterPotMin = 40;
    public static final int kBackLifterPotMin = 50;
    public static final int kLifetPotRange = 354;
    // public static final int kFrontPlatformCutoff = 1100;
    // public static final int kBackPlatformCutoff = 1100;
    public static final double kLifterExtensionSpeed = .85; //Was .8
    public static final double kLifterRetractionSpeed = -1;
    public static final double kLifterDrivePower = .60; //was .4;
    public static final double kLifterDrivePowerOverLedge = .07; //was .15;
    public static final double kDrivetrainLifterCrawlSpeedFrontLifter = -0.07;
    public static final double kDrivetrainLifterCrawlSpeedEndOfSequence = -0.18;
    public static final double kDrivetrainLifterEndOfSequenceTime = 0.75;
    public static final double kLifterAutoTimerDelay = 0;
    // public static final double kFullDangerCurrent = 134;
    // public static final int kDangerTimeout = 100;
    public static final double kLifterDriveBackDelay = 0.1;
    public static final double kLifterDriveForwardDelay = 0.1;
    public static final double kLifterF = -0.04;
    public static final double kLifterP = 4; //16 reduced bc doubled output
    public static final double k_IR_SENSOR_THREASHOLD = 1800;
    public static final double kLifterFrontSlowHeight = 329; //was 300, 300 is more safe
    public static final double kLifterFrontSlowSpeed = -0.3; 

    //Elevator constants
    public static double kLIFT_HOLD_VOLTAGE = 0.25;
    public static double kELEVATOR_F_VALUE = 0;
    public static double kELEVATOR_P_VALUE = 5; //15 //The P value of the lift's PID loop
    public static double kELEVATOR_I_VALUE = 0; //0.01; //The I value of the lift's PID loop
    public static double kELEVATOR_D_VALUE = 150;//5000; //500.0; //The D value of the lift's PID loop
    public static int kELEVATOR_I_ZONE_VALUE = 50;

    public static final int kPotOffset = 73; //was 23 //Comp //19.0 for practice
    // this should be set so that all of the distances assume the bottom of the lift is 0
    // eg: if the robot reads 14 at the bottom this should be 14

    public static double kELEVATOR_ERROR_LEVEL = 10; // tuned with 15
    public static double kElevatorShootOverHeight = 900 + kPotOffset;


    public static double kELEVATOR_PANEL_ONE = 67.0 + kPotOffset;
    public static double kELEVATOR_PANEL_TWO = 518 + kPotOffset;
    public static double kELEVATOR_PANEL_THREE = 919 + kPotOffset;

    public static double kELEVATOR_BALL_ONE = 160 + kPotOffset; //THIS IS THE CARGO SHIP
    public static double kELEVATOR_BALL_SLAM_SHIP = 244 + kPotOffset;
    public static double kELEVATOR_BALL_TWO = 408 + kPotOffset;
    public static double kELEVATOR_BALL_THREE = 844 + kPotOffset;

    public static final int kElevatorMaxHeight = 920 + kPotOffset; //This is the maximum height of the elevator based on potentiometer values 
    public static final int kElevatorMinHeight = 0 + kPotOffset; //This is the minimum height of the elevator based on potentiometer values 
    public static final int kElevatorBottomSafetyDistance = 100;
    public static final int kElevatorTopSafetyDistance = 100;
    public static final double MANUAL_POWER = 0.75;
    
    //Joystick constant
    public static final double DEADZONE = 0.05;

    //Hatch Manipulator Values
	public static final double kGetPanelAutomatedReleaseRetractionDelay = 0.25;
	public static final double kGetPanelAutomatedReleaseAlignerRetractionDelay = 1.0;
	public static final double kScorePanelDelayGrabberCloseAndHatchRetraction = 0.15; //This is the delay between the grabber closing and the hatch retraction in the automated hatch scoring
	public static final double kScorePanelDelayHatchRetractionAndAlignerRaise = 1.0;

	//Cargo Intake Values
	public static final double kCargoIntakeMotorSpeed = 0.75; //0.75;//0.65; //0.75 //This is the intake speed for the cargo
	public static final double kRaiseCargoArmsDelayAfterButtonPressed = 0.4; //This is the delay between when the ball presses the switch on the intake and when the intake is raised
	public static final double kCargoIntakeMotorStallSpeed = 0.1; //was 0.15 //This is the speed at which the intake will apply a constant stall on the ball when the switch is pressed
	public static final double kCargoSlowShootMotorSpeed = -0.25;
	public static final double kCargoFastShootMotorSpeed = -0.5;
    public static final double kCargoIntakeHumanSpeed = 0.65;
    public static final double kCargoIntakeCurrentThreshold = 14; //This seems to work pretty well lol
    public static final double kCargoCurrentSpikeDelay = 0.6; //This seems to work pretty well lol

    //Pathing
    public static final double kFarRocketPathDelay = 1.0; //just to test individual path
    public static final double kCargoshipBay1Delay = 1.0; //just to test individual path
}