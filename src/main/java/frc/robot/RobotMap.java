package frc.robot;

import edu.wpi.first.wpilibj.DigitalOutput;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring and makes checking
 * the wiring easier.
 * </br><b> For team members: There is a Google Sheet with all wiring and pneumatic related information.</b>
 * 
 * @author team2791: See Robot.java for contact info
 */
public class RobotMap {
	
	// JOYSTICK PORTS
	public static final int JOYSTICK_DRIVER_PORT = 0;
	public static final int JOYSTICK_OPERATOR_PORT = 1;

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

	public static final int SECONDARY_FOLLOWER_SHOOTER_TALON_PORT = 63;//unused right now (4/7/17)
	
	//PCM Ports--note: due to use of only single solenoids, ALL solenoids will default to false state while disabled
	public static final int INTAKE_CHANNEL = 7;
	public static final int WING_CHANNEL = 4;
	public static final int GEAR_CHANNEL = 6;
	public static final int SHOOTER_CHANNEL = 5;
	
	//PDP Ports
	public static final int POWER_RIGHT_DRIVE_A = 0;
	public static final int POWER_RIGHT_DRIVE_B = 1;
	public static final int POWER_RIGHT_DRIVE_C = 2;

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
