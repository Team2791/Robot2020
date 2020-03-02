package frc.robot.commands.PanelMech;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class SetPanelmech extends Command {
    double count = 1;
    public SetPanelmech() {
        super("SetPanelmech");
        requires(Robot.panelMech);
        
    }

    //Called just before this Command runs the first time
    @Override
    protected void initialize() {
        count++;
        if(count > 2){
            count = 0;
        }
        Robot.panelMech.extendPanelMech();
    }
    
    

    //Called repeatedly when this Command is scheduled to run 
    @Override
    protected void execute() {
        if(count == 1){
            Robot.panelMech.setPanelMech(Constants.PANEL_MECH_FAST);
        }
        else if(count == 2){
            Robot.panelMech.setPanelMech(Constants.PANEL_MECH_CREEP);
        }
        else{
            Robot.panelMech.setPanelMech(0);
        }
    }

    //Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        if(count == 0){
            return true;
        }
        return false;
    }

    //Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.panelMech.setPanelMech(0);
        Robot.panelMech.retractPanelMech();
    }

    //Called when another command which requires one or more of the same 
    //subsystems is scheduled to run

    @Override
    protected void interrupted() {
        
    }
}