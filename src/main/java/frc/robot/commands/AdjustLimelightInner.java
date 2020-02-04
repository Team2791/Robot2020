package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Drivetrain;

public class AdjustLimelightInner extends Command {

    public double TURN_FACTOR;
    public double kBetweenInnerOuter = 29.25;
    private boolean foundInner;

    public AdjustLimelightInner() {
        super("AdjustLimelightInner");
        requires(Robot.drivetrain);
        TURN_FACTOR = 0.05;
    }

    public void execute() {
        double thrust = 0;
        double xOffset = Robot.limelight.getHorizontalOffset();
        double turn;
        //change angle later to be an input from a sensor; using skew for rn
        double angle = Robot.limelight.getTargetSkew();
        //angle z and y are in between angles used to calculate the total angle to the inner
        double angleZ = 90 - Math.abs(angle);
        double angleY = 180 - angleZ;
        //angle a is the angle to the inner minus the theta(rn target skew)
        double angleA = kBetweenInnerOuter * Math.sin(angleY) / Robot.limelight.getInnerDistance();
        double totalInnerAngle = angleA + Math.abs(angle);

        double setPoint = totalInnerAngle;

        if(xOffset >= setPoint + 15|| xOffset <= setPoint - 15){
            turn = setPoint * TURN_FACTOR;
        }
        else{
            if(xOffset < 0)
                turn = -.55;
            else
                turn = .55;
            if(xOffset >= setPoint - 1 && xOffset <= setPoint + 1){
                turn = 0;
                foundInner = true;
            }
        }
        double left = Math.max(Math.min(thrust + turn, 1), -1);
        double right = Math.max(Math.min(thrust - turn, 1), -1);
        
        Robot.drivetrain.setLeftRightMotorOutputs(left, right);
    }

    public void end() {
        Robot.drivetrain.setLeftRightMotorOutputs(0, 0);
    }

    public boolean isFinished() {
        return foundInner;
    }
}
