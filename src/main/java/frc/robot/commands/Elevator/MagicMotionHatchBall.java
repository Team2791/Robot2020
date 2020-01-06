package frc.robot.commands.Elevator;
import edu.wpi.first.wpilibj.Joystick;

public class MagicMotionHatchBall extends SetLiftHeightMagicMotion {
private Joystick stick;
private double hatchHeight;
private double ballHeight;

// hatch height is the hight we want to go to if we are holding a hatch. ditto ball
    public MagicMotionHatchBall (Joystick stick, double hatchHeight, double ballHeight) {
        // THIS IS HACKY we're going to override logic in the init that is being done in the constructor of the superclass.
        // Really we should refactor these classes
        super(hatchHeight); // this doesn't really matter since we're going to reset this in the init
        this.stick = stick;
        this.hatchHeight = hatchHeight;
        this.ballHeight = ballHeight;        
        }

        @Override
    protected void initialize() {
        // condition ? true case : false case;
        double targetHeight = stick.getRawButton(7) ? ballHeight : hatchHeight; //Added this "or" statement here

        elevator.setBreak(false);
        elevator.setTargetMagicMotion(targetHeight);
        System.out.println("Trying to get to height "+targetHeight);
        finishDelayedBoolean.update(false);
    }
}