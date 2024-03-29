package frc.robot.commands.PanelMech;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class StopPanelMech extends Command {
    double count = 1;
    public StopPanelMech() {
        super("StopPanelMech");
        requires(Robot.panelMech);
        
    }

    //Called just before this Command runs the first time
    @Override
    protected void initialize() {
        
    }
    
    

    //Called repeatedly when this Command is scheduled to run 
    @Override
    protected void execute() {
        Robot.panelMech.setPanelMech(0);
    }

    //Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return true;
    }

    //Called once after isFinished returns true
    @Override
    protected void end() {
        Robot.panelMech.setPanelMech(0);
    }

    //Called when another command which requires one or more of the same 
    //subsystems is scheduled to run

    @Override
    protected void interrupted() {
        
    }
}