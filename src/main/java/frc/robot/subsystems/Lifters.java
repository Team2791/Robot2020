package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.motorcontrol.NeutralMode;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.util.IrSensor;

public class Lifters extends Subsystem {
    private TalonSRX frontLifter;
    private TalonSRX backLifter;
    private VictorSPX lifterDrive;
    private VictorSPX backHelper;
    private IrSensor frontIR;
    private IrSensor backIR;
    private int frontPotZero;// = 191;
    private int backPotZero;// = 342;
    private double proportional;
    private double feedForward;
    private double pid;
    private double proportionalSPEEDY;

    public Lifters() {
        frontLifter = new TalonSRX(RobotMap.kFrontLiftTalon);
        backLifter = new TalonSRX(RobotMap.kBackLiftTalon);
        lifterDrive = new VictorSPX(RobotMap.kRollerVictor);
        backHelper = new VictorSPX(RobotMap.kLifterHelperVictor);
        frontLifter.setNeutralMode(NeutralMode.Brake);
        backLifter.setNeutralMode(NeutralMode.Brake);
        backHelper.setNeutralMode(NeutralMode.Coast);
        lifterDrive.setNeutralMode(NeutralMode.Brake);
        frontLifter.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 0);
        frontLifter.setSensorPhase(false);

        backLifter.configSelectedFeedbackSensor(FeedbackDevice.Analog, 0, 0);
        backLifter.setSensorPhase(false);
        backHelper.follow(backLifter);

        frontIR = new IrSensor(RobotMap.kFrontIrReadout);
        backIR = new IrSensor(RobotMap.kBackIrReadout);
        frontPotZero = Constants.kFrontLifterPotMin;
        backPotZero = Constants.kBackLifterPotMin;
        proportional = Constants.kLifterP;
        proportionalSPEEDY = Constants.kLifterP * 2;
        feedForward = Constants.kLifterF;
        SmartDashboard.putNumber("LifterKP", proportional);
        SmartDashboard.putNumber("LifterKF", feedForward);
    }

    @Override
    protected void initDefaultCommand() {
    }

    public void setLifterstoCoastMode() {
        //sets Lifters to coast as a method for command
        lifterDrive.setNeutralMode(NeutralMode.Coast);
    }

    public void extendFront(double output) {
        //SmartDashboard.putNumber("LIFTER - front output", output);
        if(output < 0 && isFrontRetracted()) {
            frontLifter.set(ControlMode.PercentOutput, 0);
        } 
        else if(output > 0 && isFrontExtended()) {
            frontLifter.set(ControlMode.PercentOutput, 0);
        }
        else {
            frontLifter.set(ControlMode.PercentOutput, output);
        }

    }

    public void extendBack(double output) {
        SmartDashboard.putNumber("LIFTER - back output", output);
        if(output < 0 && isBackRetracted()) { // stop driving once we're fully back
            backLifter.set(ControlMode.PercentOutput, 0);
        } else if(output > 0 && isBackExtended()) { // stop driving once we're fully out
            backLifter.set(ControlMode.PercentOutput, 0);
        } 
        else {
            backLifter.set(ControlMode.PercentOutput, output);
        }

    }

    public void resetSystem() {
        lifterDrive.setNeutralMode(NeutralMode.Brake);
    }

    public void ExtendBoth(double output) {

        if(Math.abs(output) < 0.01) {
            extendFront(0);
            extendBack(0);
            return;
        }

        int backHeight = getBackLifterHeight();
        int frontHeight = getFrontLifterHeight();
        double diff = (double)(frontHeight - backHeight)/Constants.kLifetPotRange;
        double pTerm = diff * proportional;
        double feedback = pTerm + feedForward;
        pid = feedback;
        
        //Motor speed should not go above 1 or below -1, so if we are close to either,
        //we should only decrease the absolute value of motor outputs.
        if(Math.abs(output) < 0.5) {
            // IDK what this is for
            extendFront(output - feedback / 2);
            extendBack(output + feedback / 2);
        } else {
            extendFront(output - feedback);
            extendBack(output + feedback);
        }

    }

    public void ExtendBothSPEEDY(double output) {

        if(Math.abs(output) < 0.01) {
            extendFront(0);
            extendBack(0);
            return;
        }

        int backHeight = getBackLifterHeight();
        int frontHeight = getFrontLifterHeight();
        double diff = (double)(frontHeight - backHeight)/Constants.kLifetPotRange;
        double pTerm = diff * proportionalSPEEDY;
        double feedback = pTerm + feedForward;
        pid = feedback;
        
        //Motor speed should not go above 1 or below -1, so if we are close to either,
        //we should only decrease the absolute value of motor outputs.
        if(Math.abs(output) < 0.5) {
            extendFront(output - feedback / 2);
            extendBack(output + feedback / 2);
        } else if(output > 0) {
            if(feedback > 0) {
                extendFront(output - feedback);
                extendBack(output);
            } else {
                extendFront(output);
                extendBack(output + feedback);
            }
        } else {
            if(feedback > 0) {
                extendFront(output);
                extendBack(output + feedback);
            } else {
                extendFront(output - feedback);
                extendBack(output);
            }
        }
    }

    public void stopBackFollow() {
        backHelper.set(ControlMode.PercentOutput, 0);
    }

    public void startBackFollow() {
        backHelper.follow(backLifter);
    }

    public void zeroPots()
    {
        if(isFrontRetracted()) {
            frontPotZero = getFrontHeightRAW();
        }

        if(isBackRetracted()) {
            backPotZero = getBackHeightRAW();
        }
    }

    public boolean isFrontRetracted() {
        return frontLifter.getSensorCollection().isRevLimitSwitchClosed();
    }

    public boolean isBackRetracted() {
        return backLifter.getSensorCollection().isRevLimitSwitchClosed();
    }

    public boolean isFrontExtended() {
        if(frontLifter.getSensorCollection().isFwdLimitSwitchClosed() == true || getFrontLifterHeight() > Constants.kLifterFrontPotTopTravel){
            return true;
        }
        return false;
    }

    public boolean isFrontAtPotValue(int potValue){
        if(getFrontLifterHeight() > potValue){
            return true;
        }
        return false;
    }

    public boolean isBackAtPotValue(int potValue){
        if(getBackLifterHeight() > potValue){
            return true;
        }
        return false;
    }
     
    public boolean isBackExtended() {
        if(backLifter.getSensorCollection().isFwdLimitSwitchClosed() == true || getBackLifterHeight() > Constants.kLifterBackPotTopTravel){
            return true;
        }
        return false;
    }

    public void driveMotor(double output) {
        lifterDrive.set(ControlMode.PercentOutput, output);
    }

    public int getFrontHeightRAW() {
        return 1023 - frontLifter.getSensorCollection().getAnalogIn();
    }

    public int getBackHeightRAW() {
        return 1023 - backLifter.getSensorCollection().getAnalogIn();
    }

    public int getFrontLifterHeight() {
        return getFrontHeightRAW() - frontPotZero;
    }

    public int getBackLifterHeight() {
        return getBackHeightRAW() - backPotZero;
    }

    public boolean isFrontOverLedge(boolean isHigh) {
        return frontIR.getValue() > Constants.k_IR_SENSOR_THREASHOLD;
    }

    public boolean isBackOverLedge(boolean isHigh) {
        return backIR.getValue() > Constants.k_IR_SENSOR_THREASHOLD;
    }

    public double getFrontCurrent() {
        return Robot.pdp.getCurrent(RobotMap.kPowerFrontLift);
    }

    public double getBackCurrent() {
        return Robot.pdp.getCurrent(RobotMap.kPowerBackLift);
    }

    public void debug() {
        SmartDashboard.putBoolean("LFT - Front Lifter Retracted", isFrontRetracted());
        SmartDashboard.putBoolean("LFT - Front Lifter Extended", isFrontExtended());
        SmartDashboard.putBoolean("LFT - Back Lifter Retracted", isBackRetracted());
        SmartDashboard.putBoolean("LFT - Back Lifter Extended", isBackExtended());
        SmartDashboard.putNumber("LFT - Pot Front value raw", getFrontHeightRAW());
        SmartDashboard.putNumber("LFT - Pot Back value raw", getBackHeightRAW());
        SmartDashboard.putNumber("LFT - Front IR raw", frontIR.getValue());
        SmartDashboard.putNumber("LFT - Back IR raw", backIR.getValue());
        SmartDashboard.putNumber("LFT - Adjusted Front Pot", getFrontLifterHeight());
        SmartDashboard.putNumber("LFT - Adjusted Back Pot", getBackLifterHeight());
        SmartDashboard.putNumber("LFT - Adjusted Pot Diff", getFrontLifterHeight() - getBackLifterHeight());
        SmartDashboard.putNumber("LFT - PID Output", pid);
        SmartDashboard.putNumber("LFT - Front Lift Current", getFrontCurrent());
        SmartDashboard.putNumber("LFT - Back Lift Current", getBackCurrent());

        //proportional = SmartDashboard.getNumber("LifterKP", Constants.kLifterP);
        //feedForward = SmartDashboard.getNumber("LifterKF", Constants.kLifterF);
    }
}
