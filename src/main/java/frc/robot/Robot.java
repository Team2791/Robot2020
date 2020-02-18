package frc.robot;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Elevator;
import frc.robot.subsystems.Hopper;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.Compressor;

import edu.wpi.first.cameraserver.CameraServer;


                                                                                                   
public class Robot extends TimedRobot {
    long loopCounter = 0;

    public static OI oi;
    public static Drivetrain drivetrain;
    public static PowerDistributionPanel pdp;
    public static Compressor compressor;
    public static Shooter shooter;
    public static Elevator elevator;
    public static Hopper hopper;
    public static RobotMap robotmap;
    public static Intake intake;
    public static ControlPan controlPan;

   //public static final CameraServer CamServer = CameraServer.getInstance();


    @Override
    public void robotInit() {
        hopper = new Hopper();
        shooter = new Shooter();
        drivetrain = new Drivetrain();
        intake = new Intake();
        controlPan = new ControlPan();

        pdp = new PowerDistributionPanel(RobotMap.kPDP);
        oi = new OI(); 
        robotmap = new RobotMap();

        compressor = new Compressor(RobotMap.kPCM);
        compressor.start(); 
        compressor.enabled();
    }
    
    @Override
    public void robotPeriodic() {
        // //EACH debug only runs once per 10 loops
        // DriveWithJoystick joystick_command = new DriveWithJoystick(OI.driverStick, 0.1)
        drivetrain.debug();
        hopper.debug();

        // CamServer.startAutomaticCapture(0);
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
        //   Robot.elevator.setElevator(-.1);
        //   Robot.hopper.setHopper(-1);
        // Robot.shooter.setShooter(-1);
        //  Robot.drivetrain.setMotors(-1);

        //  Robot.drivetrain.setMotors(-.0000001);
        // Robot.drivetrain.setRightTalon(-1);
        // Robot.drivetrain.setLeftNeo(-1);
        // Robot.drivetrain.setLeftTalon(.7);
        // Robot.drivetrain.setRightNeo(1);
         // 0.5 power is the sweet spot for wall, 0.8  for current at angle of 39 degrees
        Scheduler.getInstance().run();
    }
    @Override
    public void testPeriodic() {
    }
}
