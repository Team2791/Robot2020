package frc.robot.commands.PanelMech;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class SetPanelMechFast extends Command {
    double count = 1;
    public SetPanelMechFast() {
        super("SetPanelMechFast");
        requires(Robot.panelMech);
        
    }

    //Called just before this Command runs the first time
    @Override
    protected void initialize() {
        
    }
    
    

    //Called repeatedly when this Command is scheduled to run 
    @Override
    protected void execute() {
        Robot.panelMech.setPanelMech(Constants.PANEL_MECH_FAST);
    }

    //Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return true;
    }

    //Called once after isFinished returns true
    @Override
    protected void end() {

    }

    //Called when another command which requires one or more of the same 
    //subsystems is scheduled to run

    @Override
    protected void interrupted() {
        
    }
}