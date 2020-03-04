package frc.robot.commands.IntakeToHopper;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.IntakeToHopper.MoveManipulator;
import edu.wpi.first.wpilibj.Timer;

public class IrHopper extends Command {
  public int count;
  public Timer timer= new Timer();
  public IrHopper() {
    super("IrHopper");
    requires(Robot.hopper);
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    count = 0;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
      Robot.hopper.loadingWithIR();
      SmartDashboard.putBoolean("Ir Hopper Running", true);
      if(Robot.hopper.limitSwitch.get()){
        timer.start();
        Robot.hopper.setHopper(Constants.HOPPER_LOADING_HORIZONTAL_OUTPUT, Constants.HOPPER_LOADING_VERTICAL_OUTPUT);
      }
      if (timer.get()>=Constants.kHopperTimer){
          timer.stop();
          Robot.hopper.setHopper(0,0);
        }

  }

  // Make this return true when this Command no longer needs to run execute()
  @Override

  protected boolean isFinished() {
    // return !Robot.hopper.isBall() || Robot.hopper.isUpperSensorTripped();
    if (Robot.hopper.isHopperFull()==true){
      return true;
    }
    else{
      return false;
    }
    
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    count++;
    Robot.hopper.setHopper(0, 0);
    SmartDashboard.putBoolean("Ir Hopper Running", false);
    Robot.hopper.counter.reset();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }

}
