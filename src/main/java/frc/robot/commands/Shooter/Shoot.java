package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.MoveHopper;
import frc.robot.subsystems.Shooter;

public class Shoot extends Command{
    private double wheelSpeed;
    private boolean goodSpeed;
    public Shoot(){
        super("Shoot");
        requires(Robot.shooter);
        requires(Robot.hopper);
    }

    @Override
    
    protected void initialize(){

    }

    @Override
    
    protected void execute() {
        Robot.shooter.setHood1(false); //change to true???
        //new CheckWheelSpeed();
        new MoveHopper();
        Robot.hopper.setRetracted(Robot.hopper.isRetracted()); //define isRetracted as true or false???
        
    }
    
    @Override

    protected boolean isFinished(){
        return false;
    }
    @Override

    protected void end() {
        Robot.shooter.setShooter(0);
        Robot.hopper.setHopper(0);
        Robot.hopper.setRetracted(Robot.hopper.isRetracted()); //if yes above, change here as well
    }

    @Override

    protected void interrupted(){
    }
}