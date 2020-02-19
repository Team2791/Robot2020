package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.MoveHopper;
// import frc.robot.subsystems.Shooter;

//in theory this just chanes whether the hopper piston is retracted or not and then stops
public class OpenHopperPiston extends Command{
    private boolean retracted;
    public OpenHopperPiston(){
        super("OpenHopperPiston");
        requires(Robot.hopper);
    }

    @Override
    protected void initialize(){
    }

    @Override
    protected void execute() {
        Robot.hopper.setRetracted(true);
        
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