package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Shooter;

public class CheckWheelSpeed extends Command{
    public CheckWheelSpeed(){
        super("CheckWheelSpeed");
        requires(Robot.shooter);
    }

    @Override
    
    protected void initialize(){

    }

    @Override
    
    protected void execute() {
        
    }
    
    @Override

    protected boolean isFinished(){
        //change .01 to another number to change error parameters
        if(Robot.shooter.getShooterVelocity() >= Constants.SHOOTER_OUTPUT - .01 && Robot.shooter.getShooterVelocity() <= Constants.SHOOTER_OUTPUT + .01){
            return true;
        }
        else{
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