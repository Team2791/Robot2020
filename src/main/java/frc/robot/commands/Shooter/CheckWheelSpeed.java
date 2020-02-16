package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Shooter;

public class CheckWheelSpeed extends Command{
    private double wheelSpeed;
    public CheckWheelSpeed(){
        super("CheckWheelSpeed");
        requires(Robot.shooter);
    }

    @Override
    
    protected void initialize(){

    }

    @Override
    
    protected void execute() {
        wheelSpeed = Robot.shooter.getShooterVelocity();
    }
    
    @Override

    protected boolean isFinished(){
        //change .01 to another number to change error parameters
        if(wheelSpeed >= Constants.SHOOTER_OUTPUT - .01 && wheelSpeed <= Constants.SHOOTER_OUTPUT + .01){
            return true;
        }
        else{
            Robot.shooter.setShooter(Constants.SHOOTER_OUTPUT);
            return false;
        }
    }
    @Override

    protected void end() {

    }

    @Override

    protected void interrupted(){
    }
}