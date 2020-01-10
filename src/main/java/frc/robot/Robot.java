package frc.robot;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.subsystems.Drivetrain;
import edu.wpi.first.wpilibj.Compressor;


                                                                                                   
public class Robot extends TimedRobot {
    long loopCounter = 0;

    public static OI oi;
    public static Drivetrain drivetrain;
    public static PowerDistributionPanel pdp;
    public static Compressor compressor;


    @Override
    public void robotInit() {
        drivetrain = new Drivetrain();
        pdp = new PowerDistributionPanel(RobotMap.kPDP);
        // oi = new OI();
        
    }
    
    @Override
    public void robotPeriodic() {
        // //EACH debug only runs once per 10 loops
        loopCounter += 1;
        drivetrain.debug();

    }

    @Override
    public void disabledInit() {
        drivetrain.setMotors(0);
    }

    @Override
    public void disabledPeriodic() {
         drivetrain.setMotors(0);
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

    }

    @Override
    public void teleopPeriodic() {
        // Robot.drivetrain.setMotors(-1);
        Robot.drivetrain.setRightTalon(-1);
        Robot.drivetrain.setLeftNeo(-1);
        Robot.drivetrain.setLeftTalon(.7);
        Robot.drivetrain.setRightNeo(1);
         // 0.5 power is the sweet spot for wall, 0.8  for current at angle of 39 degrees
        Scheduler.getInstance().run();
    }
    
    @Override
    public void testPeriodic() {
    }
}
