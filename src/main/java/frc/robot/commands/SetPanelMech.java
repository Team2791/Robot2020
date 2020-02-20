package frc.robot.commands;
import frc.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;

public class SetPanelMech extends Command {
  public SetPanelMech() {
    super("SetPanelMech");

    requires(Robot.panelMech);

    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Constants.PanelNum++;
    if(Constants.PanelNum==3){
        Constants.PanelNum=0;
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
      if(Constants.PanelNum==1){
        Robot.panelMech.setPanelMech(Constants.PANEL_MECH_FAST);
      }
      else if(Constants.PanelNum==0){
        Robot.panelMech.setPanelMech(Constants.PANEL_MECH_CREEP);
      }
      else{
        Robot.panelMech.setPanelMech(0);
      }


  }



  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.panelMech.setPanelMech(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}