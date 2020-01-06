package frc.robot.subsystems;

import frc.robot.Constants;
import frc.robot.RobotMap;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.Elevator.StopElevator;

public class Elevator extends Subsystem {
    int loopCounter = 0;

    private Solenoid breakSolenoid;
    private TalonSRX driveTalon;
    public boolean magic = false;
    public String command = "";

    //The potentiometer we are using goes from -1023 to 0 full range.
    //There is a bicycle disk break attached to a piston that allows us to stop the lift at the right height without needing stall current and other fancy shit
    //The elevator has one vex pro 775 connected to a talon srx
    //The goal of using magic motion is to be able to travel to a set potentiometer value upon a button press
    //For example, when the operator presses the "Y" button, we want to travel to potentiometer value X, where X equals the proper lift height to score in the top goal of the rocketship
    //Elevator 775 gear ration is a two stage box, with gear ratio 12:80 and then 18:54
    
    public Elevator() {
        super("ShakerLift");


        driveTalon = new TalonSRX(RobotMap.kElevatorTalon); //This initializes the TalonSRX for the elevator called driveTalon
        breakSolenoid = new Solenoid(RobotMap.kPCM, RobotMap.kBreakSolenoid); // RobotMap ids need to be changed
        driveTalon.configSelectedFeedbackSensor(FeedbackDevice.Analog); //This sets up which sensor we want to use, we set this to analog because we using a pot
        driveTalon.setSensorPhase(true); //This should be true, to keep sensor in phase POSITIVE IS UP
        driveTalon.setInverted(true); //This should be true, as green lights (Positive direction) = lift going UP
        driveTalon.setNeutralMode(NeutralMode.Brake);

        /*
            Enable the current limit such that when it exceeds 45A for 40 msec, limit the current draw to 30A
        */
        //NOTE COMMENTING THIS OUT WILL NOT DISABLE IT, YOU NEED TO SET THE NEXT 3 VALUES TO 0 and the last to false
        driveTalon.configContinuousCurrentLimit(30); //30
        driveTalon.configPeakCurrentLimit(45); //45
        driveTalon.configPeakCurrentDuration(10); //10
        driveTalon.enableCurrentLimit(true); //true

        driveTalon.overrideLimitSwitchesEnable(false);

        driveTalon.configNominalOutputForward(Constants.kLIFT_HOLD_VOLTAGE, 0); //kLIFT_HOLD_VOLTAGE is a little bit of stall current to keep lift from falling back down before the break enables
        driveTalon.configNominalOutputReverse(0.0, 0);

        driveTalon.configPeakOutputForward(1.0,0);
        driveTalon.configPeakOutputReverse(-0.75,0);

        driveTalon.selectProfileSlot(0,0); //Just set this to 0,0

        driveTalon.config_kF(0, Constants.kELEVATOR_F_VALUE, 0);
		driveTalon.config_kP(0, Constants.kELEVATOR_P_VALUE, 0);
		driveTalon.config_kI(0, Constants.kELEVATOR_I_VALUE, 0);
        driveTalon.config_kD(0, Constants.kELEVATOR_D_VALUE, 0);
        driveTalon.config_IntegralZone(0, Constants.kELEVATOR_I_ZONE_VALUE);
    }

    @Override
    protected void initDefaultCommand() {
        setDefaultCommand(new StopElevator());
    }

    public double getSensorPosition() {
        return driveTalon.getSelectedSensorPosition(0);
    }

    public void setBreak(boolean breakBool){
        breakSolenoid.set(!breakBool);
    }

    public double getElevatorHeight(){
        return getSensorPosition() - Constants.kPotOffset;
    }

    public int getVelocity() {
        return driveTalon.getSelectedSensorVelocity(0);
    }
    
    public boolean atBottom() {
        return driveTalon.getSensorCollection().isRevLimitSwitchClosed() || getSensorPosition() < Constants.kElevatorMinHeight + 1;
    }
        
    public boolean closeToBottom() {
        return getSensorPosition() < Constants.kElevatorMinHeight + Constants.kElevatorBottomSafetyDistance;
    }
        
    public boolean atTop() {
        return driveTalon.getSensorCollection().isFwdLimitSwitchClosed() || getSensorPosition() > Constants.kElevatorMaxHeight - 1;
    }
        
    public boolean closeToTop() {
        return getSensorPosition() > Constants.kElevatorMaxHeight - Constants.kElevatorTopSafetyDistance;
    }

    public void setPowerUnsafe(double power) {
		driveTalon.set(ControlMode.PercentOutput, power);
    }

    public void setTargetMagicMotion(double targetHeight) {
        driveTalon.set(ControlMode.Position, targetHeight);
    }
    
    public int getMagicMotionInstantError() { //This does not return what you would expect FYI
    	return driveTalon.getClosedLoopError(0);
    }

    public void debug() {
        SmartDashboard.putBoolean("Elevator - Top Limit Switch value", atTop());
        SmartDashboard.putBoolean("Elevator - Bottom Limit Switch value", atBottom());
        SmartDashboard.putNumber("Elevator - Height", getElevatorHeight());
        SmartDashboard.putNumber("Elevator - Velocity", getVelocity());
        SmartDashboard.putBoolean("Elevator - Close to top", closeToTop());
        SmartDashboard.putBoolean("Elevator - Close to bottom", closeToBottom());
        SmartDashboard.putNumber("Elevator - RAW HEIGHT", getSensorPosition());
        SmartDashboard.putString("Elevator Command", command);
    }

}