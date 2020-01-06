package frc.robot.commands.Lifter;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.Constants;
// import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

public class ExtendBothLifters extends Command {
    private double output;
    // private boolean inFullAutoMode;
    // private Joystick sticky;

    private double lastFrontPotValue;
    private double currentFrontPotValue;
    private double lastBackPotValue;
    private double currentBackPotValue;
    private double frontDiff;
    private double rearDiff;
    private boolean level2;
    private Timer startupCounter = new Timer();


    public ExtendBothLifters(double output, boolean useLevel2) {
        super("ExtendBothLifters");
        requires(Robot.lifters);
        this.output = output;
        // this.sticky = stick;
        // this.inFullAutoMode = fullAutoMode;
        this.level2 = useLevel2;
    }

    public void initialize(){
        lastBackPotValue = Robot.lifters.getBackLifterHeight();
        lastFrontPotValue = Robot.lifters.getFrontLifterHeight();
        Robot.lifters.startBackFollow();
        startupCounter.reset();
        startupCounter.start();
    }

    public void execute() {
        currentBackPotValue = Robot.lifters.getBackLifterHeight();
        currentFrontPotValue = Robot.lifters.getFrontLifterHeight();
        frontDiff = Math.abs(currentFrontPotValue - lastFrontPotValue);
        rearDiff = Math.abs(currentBackPotValue - lastBackPotValue);

        if(frontDiff > Constants.kLifterPotTicksInOneLoopForShutdown || rearDiff > Constants.kLifterPotTicksInOneLoopForShutdown){
            //This stops the command if the lifter sees that the pot moved more than kLifterPotTicksInOneLoopForShutdown units in one loop
            System.out.println("Lifter pot diff error");
            end();
        }

        if(Robot.lifters.getFrontHeightRAW() < -50 || Robot.lifters.getBackHeightRAW() < -50){
            //This stops the command if one of the pots gets unplugged
            System.out.println("Lifter pot error");
            end();
        }

        // if(startupCounter.get() > Constants.kLifterCurrentProtectionSpikeTimer && Robot.lifters.getFrontCurrent() > Constants.kLifterCurrentKiller){
        //     //This stops the command if the front lifter draws too much power after the first second
        //     System.out.println("Front lifter current protection engaged");
        //     end();
        // }

        // if(startupCounter.get() > Constants.kLifterCurrentProtectionSpikeTimer && Robot.lifters.getBackCurrent() > Constants.kLifterCurrentKiller){
        //     //This stops the command if the rear lifter draws too much power after the first second
        //     System.out.println("Rear lifter current protection engaged");
        //     end();
        // }
        
        // if(inFullAutoMode == true && sticky.getRawButton(8)==true){
            //This will only happen if the driver is pressing down on Start
                // Robot.lifters.ExtendBothSPEEDY(output);
                // System.out.println("Entering Lifter SPEED MODE");
        // }

        Robot.lifters.ExtendBoth(output);
        
        lastBackPotValue = currentBackPotValue;
        lastFrontPotValue = currentFrontPotValue;
    }

    public void end() {
        Robot.lifters.extendFront(0);
        Robot.lifters.extendBack(0);
    }

    public boolean isFinished() {
        if(level2 == true){
            if(Robot.lifters.getFrontLifterHeight() > Constants.kLiftersLevel2PotValue && Robot.lifters.getBackLifterHeight() > Constants.kLiftersLevel2PotValue){
                return true;
            }
        }
        return Robot.lifters.isFrontExtended() && Robot.lifters.isBackExtended();
    }
}