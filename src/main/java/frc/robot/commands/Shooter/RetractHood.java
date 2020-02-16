package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.MoveHopper;
import frc.robot.subsystems.Shooter;

//in theory this just chanes whether the hood is retracted or not and then stops
public class RetractHood extends Command{
    private boolean retracted;
    public RetractHood(){
        super("RetractHood");
        requires(Robot.shooter);
    }

    @Override
    
    protected void initialize(){
        retracted = Robot.shooter.getHood1();
    }

    @Override
    
    protected void execute() {
        Robot.shooter.setHood1(retracted);
        
    }
    
    @Override

    protected boolean isFinished(){
        return true;
    }
    @Override

    protected void end() {
        
    }

    @Override

    protected void interrupted(){
    }
}