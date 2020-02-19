package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.MoveHopper;
import frc.robot.subsystems.Shooter;

//in theory this just chanes whether the hopper piston is retracted or not and then stops
public class StopShooter extends Command{
    private boolean retracted;
    public StopShooter(){
        super("StopShooter");
        requires(Robot.hopper);
    }

    @Override
    protected void initialize(){
    }

    @Override
    protected void execute() {
        Robot.shooter.setShooter(0);
        
    }
    
    @Override
    protected boolean isFinished(){
        return false; 
    }
    @Override
    protected void end() {
        Robot.shooter.setShooter(0);
    }

    @Override

    protected void interrupted(){
    }
}