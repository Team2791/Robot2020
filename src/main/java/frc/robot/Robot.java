package frc.robot;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.Compressor;
import frc.robot.util.Limelight;

                                                                                                   
public class Robot extends TimedRobot {
    long loopCounter = 0;

    public static OI oi;
    public static PowerDistributionPanel pdp;
    public static Compressor compressor;
    public static Limelight limelight;


    @Override
    public void robotInit() {
        limelight = new Limelight();
        pdp = new PowerDistributionPanel(RobotMap.kPDP);
        // oi = new OI();
        
    }
    
    @Override
    public void robotPeriodic() {
        // //EACH debug only runs once per 10 loops
        loopCounter += 1;
        

    }

    @Override
    public void disabledInit() {
        
    }

    @Override
    public void disabledPeriodic() {
        limelight.debug();
        // Robot.drivetrain.resetGyro();
        // autoCommand.start();
    }

    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        // autoCommand.cancel();
        System.out.println("This is init");
        limelight.debug();
    }

    @Override
    public void teleopPeriodic() {
        limelight.debug();
        // Robot.drivetrain.setMotors(-1);
        
         // 0.5 power is the sweet spot for wall, 0.8  for current at angle of 39 degrees
        Scheduler.getInstance().run();
    }
    
    @Override
    public void testPeriodic() {
    }
}
