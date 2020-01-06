package frc.robot.commands.auto;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Constants;
import frc.robot.commands.Lifter.DriveLifterWheelBackIR;
import frc.robot.commands.Lifter.DriveLifterWheelFrontIR;
import frc.robot.commands.Lifter.ExtendBothLifters;
import frc.robot.commands.Lifter.RetractBackLifter;
import frc.robot.commands.Lifter.RetractFrontLifterNoShock;
import frc.robot.commands.DoNothing;
import frc.robot.commands.auto.DriveForwardForTime;
import frc.robot.commands.auto.TurnOffCompressor;
// import edu.wpi.first.wpilibj.Joystick;

public class PlatformAuto2 extends CommandGroup {
    // public Joystick sticky;
    public PlatformAuto2 (){
        // this.sticky = stick;
        addSequential(new TurnOffCompressor());
        addSequential(new AutoSetLifterPots());
        addSequential(new ExtendBothLifters(Constants.kLifterExtensionSpeed,true));
        addSequential(new DoNothing(),Constants.kLifterAutoTimerDelay);
        addSequential(new DriveLifterWheelBackIR());
        addSequential(new DoNothing(),Constants.kLifterAutoTimerDelay);
        addSequential(new RetractBackLifter(Constants.kLifterRetractionSpeed));
        addSequential(new DoNothing(),Constants.kLifterAutoTimerDelay);
        addSequential(new DriveLifterWheelFrontIR());
        addSequential(new DoNothing(),Constants.kLifterAutoTimerDelay);
        // addSequential(new DriveForwardForTime(Constants.kCreep, Constants.kInchTime));
        addSequential(new RetractFrontLifterNoShock(Constants.kLifterRetractionSpeed, true));
        addSequential(new DoNothing(),Constants.kLifterAutoTimerDelay);
        addSequential(new DriveForwardForTime(Constants.kDrivetrainLifterCrawlSpeedEndOfSequence,Constants.kDrivetrainLifterEndOfSequenceTime));
    }
}

