package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
// import frc.robot.subsystems.Shooter;

//in theory this just chanes whether the hopper piston is retracted or not and then stops
public class CloseHopperPiston extends Command{
    private boolean retracted;
    public CloseHopperPiston(){
        super("CloseHopperPiston");
        requires(Robot.hopper);
    }

    @Override
    protected void initialize(){
        retracted = Robot.hopper.getRetracted();
    }

    @Override
    protected void execute() {
        Robot.hopper.setRetracted(false);
        
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