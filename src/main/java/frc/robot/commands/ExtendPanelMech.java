package frc.robot.commands;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;



public class ExtendPanelMech extends Command {
  public ExtendPanelMech() {
    super("ExtendPanelMech");

    requires(Robot.panelMech);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Constants.SpinNum++;
    if(Constants.SpinNum==2){
      Constants.SpinNum=0;
    }
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if(Constants.SpinNum==0){
    Robot.panelMech.extendPanelMech(true);

  }
  else{
    Robot.panelMech.extendPanelMech(false);
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
    Robot.panelMech.extendPanelMech(false);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}