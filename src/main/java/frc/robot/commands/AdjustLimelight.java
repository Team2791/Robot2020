package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;

public class AdjustLimelight extends Command {

    public double TURN_FACTOR;
    private boolean foundOuter;

    public AdjustLimelight() {
        super("AdjustLimelight");
        TURN_FACTOR = 0.05;
    }

    public void execute() {
        double thrust = 0;
        double xOffset = Robot.limelight.getHorizontalOffset();
        double turn;
        //try to get it to turn at a slow speed until it hits the center
        //once that's done, try to have this type of if where it is faster
        //farther away
        if(xOffset >= 15|| xOffset <= -15){
            turn = Robot.limelight.getHorizontalOffset() * TURN_FACTOR;
        }
        if((xOffset < 15 && xOffset > 10)||(xOffset > -15 && xOffset < -10)){
            turn = Robot.limelight.getHorizontalOffset() * 0.03;
        }
        else{
        if(xOffset < 0)
            turn = -.55;
        else
            turn = .55;
        if(xOffset >= -1 && xOffset <= 1)
            turn = 0;
            foundOuter = true;
        }
        double left = Math.max(Math.min(thrust + turn, 1), -1);
        double right = Math.max(Math.min(thrust - turn, 1), -1);
        
        Robot.drivetrain.setLeftRightMotorOutputs(left, right);
    }

    public void end() {
        Robot.drivetrain.setLeftRightMotorOutputs(0, 0);
    }

    public boolean isFinished() {
        return foundOuter;
    }
}