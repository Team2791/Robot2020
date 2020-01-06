package frc.robot.commands.CargoManipulator;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.Timer;
import frc.robot.Robot;
import frc.robot.Constants;

//This command does nothing when run, but only completes when the cargo switch state is true.
public class CheckForBall extends Command {
    private Timer currentSpikeTimer = new Timer();
    public CheckForBall() {
        super("CheckForBall");
        requires(Robot.cargoManipulator);
        
    }
    public void initialize(){
    currentSpikeTimer.reset();
    currentSpikeTimer.start();
    }
    public void execute() {
        
    }

    public boolean isFinished() {
        if(Robot.cargoManipulator.getCargoCurrent() > Constants.kCargoIntakeCurrentThreshold && currentSpikeTimer.get() > Constants.kCargoCurrentSpikeDelay){
            return true;
        }
        return false;
        // return Robot.cargoManipulator.getCargoSwitchState();
    }
}