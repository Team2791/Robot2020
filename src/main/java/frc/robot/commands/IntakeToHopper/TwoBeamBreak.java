package frc.robot.commands.IntakeToHopper;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.IntakeToHopper.MoveManipulator;
import edu.wpi.first.wpilibj.Timer;

public class TwoBeamBreak extends Command {
  public int count;
  private boolean timerStart = false;
  private boolean firstBall = false;
  private boolean on1 = false;
  private boolean off1 = false;
  private boolean on2 = false;
  private boolean beenthrough = false;
  private boolean trippitybop = true;
  public Timer timer= new Timer();
  public TwoBeamBreak() {
    super("TwoBeamBreak");
    requires(Robot.hopper);
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    // count = 0;
    SmartDashboard.putBoolean("Timer Running", false);
    SmartDashboard.putBoolean("Started", false);
    SmartDashboard.putBoolean("Beam Break", !Robot.hopper.beamBreak1.get());
    SmartDashboard.putBoolean("Upper Sensor", Robot.hopper.isUpperSensorTripped());
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
      SmartDashboard.putBoolean("Beam Break Hopper Running", true);
        if(!Robot.hopper.isUpperSensorTripped()){
            if(!Robot.hopper.beamBreak1.get()){
              // timer.start();
              Robot.hopper.setHopper(Constants.HOPPER_LOADING_HORIZONTAL_OUTPUT,
              Constants.HOPPER_LOADING_VERTICAL_OUTPUT);
              on1 = true;
              // timerStart = true;
            }
            on1 = !Robot.hopper.beamBreak2.get();
      
            if(Robot.hopper.beamBreak2.get() || beenthrough){
                beenthrough = true;
                if(!Robot.hopper.beamBreak2.get() && on1){
                  Robot.hopper.setHopper(0,0);
                  on1 = false;
                  beenthrough = false;
                }
                
        // if(timerStart){
        //   if (timer.get()>=Constants.kHopperTimer){
        //     timer.stop();
        //     timer.reset();
        //     Robot.hopper.setHopper(0,0);
        //   }
        // }
            }
          }
              else {
                SmartDashboard.putBoolean("Started", true);
                SmartDashboard.putBoolean("Beam Break", !Robot.hopper.beamBreak1.get());
                SmartDashboard.putBoolean("Upper Sensor", Robot.hopper.isUpperSensorTripped());
                if((!Robot.hopper.beamBreak1.get()&&Robot.hopper.isUpperSensorTripped())&&trippitybop){
                    // if (Robot.hopper.isUpperSensorTripped(){//||trippitybop){
                      timer.start();
                      // trippitybop = true;
                      Robot.hopper.setHopper(Constants.HOPPER_LOADING_HORIZONTAL_OUTPUT, Constants.HOPPER_LOADING_VERTICAL_OUTPUT);
                      SmartDashboard.putBoolean("Timer Running", true);
                      if(timer.get()>=0){
                        Robot.hopper.setHopper(0,0);
                        trippitybop = false;                        
                        SmartDashboard.putBoolean("Timer Running", false);
                      }
                  // }

                }
              }
        
    }

  

  // Make this return true when this Command no longer needs to run execute()
  @Override

  protected boolean isFinished() {
    // if (Robot.hopper.isHopperFull()==true){
    //   return true;
    // }
    // else{
    //   return false;
    // }
    return false;
    
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    // count++;
    Robot.hopper.setHopper(0, 0);
    // SmartDashboard.putBoolean("Ir Hopper Running", false);
    // Robot.hopper.counter.reset();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }

}
