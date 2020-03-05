package frc.robot.commands.IntakeToHopper;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.IntakeToHopper.MoveManipulator;
import edu.wpi.first.wpilibj.Timer;

public class LimitSwitch extends Command {
  public int count;
  //public double lastDebounceTime = 0;
  //public boolean reading;
  private boolean timerStart = false;
  //private boolean lastButtonState = true;
  public Timer timer = new Timer();
  public LimitSwitch() {
    super("LimitSwitch");
    requires(Robot.hopper);
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.hopper.counter.setDistancePerPulse(1./1.);
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
        SmartDashboard.putBoolean("Ir Hopper Running", true);
        if (Robot.hopper.counter.get() < 5){
            if(!Robot.hopper.limitSwitch.get()){
                timer.start();
                Robot.hopper.setHopper(Constants.HOPPER_LOADING_HORIZONTAL_OUTPUT,
                Constants.HOPPER_LOADING_VERTICAL_OUTPUT);
                timerStart = true;
            }
            if(timerStart){
                if (timer.get()>=Constants.kHopperTimer){
                    timer.stop();
                    timer.reset();
                    Robot.hopper.setHopper(0,0);
                }
            }

        }

    }

  // Make this return true when this Command no longer needs to run execute()
  @Override

  protected boolean isFinished() {
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
    // count++;
    Robot.hopper.setHopper(0, 0);
    SmartDashboard.putBoolean("Ir Hopper Running", false);
    // Robot.hopper.counter.reset();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }

}
