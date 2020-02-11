package frc.robot;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;

import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj.Compressor;
import frc.robot.util.Limelight;

                                                                                                   
public class Robot extends TimedRobot {
    long loopCounter = 0;

    public static OI oi;
    public static PowerDistributionPanel pdp;
    public static Compressor compressor;

    public static Shooter shooter;
    public static Elevator elevator;
    public static Hopper hopper;
    public static RobotMap robotmap;
    public static Limelight limelight;
    public static Drivetrain drivetrain;

    @Override
    public void robotInit() {
        hopper = new Hopper();
        elevator = new Elevator();
        shooter = new Shooter();
        drivetrain = new Drivetrain();
        pdp = new PowerDistributionPanel(RobotMap.kPDP);
        oi = new OI(); 
        limelight = new Limelight();
        robotmap = new RobotMap();
    }
    
    @Override
    public void robotPeriodic() {
        drivetrain.debug();
        hopper.debug();
        limelight.debug();
    }

    @Override
    public void disabledInit() {
        drivetrain.setMotors(0,0);
    }

    @Override
    public void disabledPeriodic() {
        drivetrain.setMotors(0,0);
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
        Scheduler.getInstance().run();
    }
    @Override
    public void testPeriodic() {
    }
}
