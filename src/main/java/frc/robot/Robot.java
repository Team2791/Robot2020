package frc.robot;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.setCameraOne;
import frc.robot.subsystems.*;
import frc.robot.util.LedMode;
import frc.robot.util.Camera_Switch.CameraSwitch;
import frc.robot.OI.*;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.first.cameraserver.CameraServer;

import frc.robot.subsystems.Climber;
import frc.robot.subsystems.*;


                                                                                                   
public class Robot extends TimedRobot {

	long loopCounter = 0;

    public static Joystick operatorStick;
    public static OI oi;
    public static Drivetrain drivetrain;
    public static PowerDistributionPanel pdp;
    public static Compressor compressor;
    public static Shooter shooter;
    public static Elevator elevator;
    public static Hopper hopper;
    public static Limelight limelight;
    public static RobotMap robotmap;

    public static Manipulator manipulator;
    public static PanelMech panelMech;
    public static CameraServer Cam;
    public static CameraSwitch Cam_switch; 
    private Button operatorA, operatorB; 

    public static Climber climber;

    @Override
    public void robotInit() {
        hopper = new Hopper();
        shooter = new Shooter();
        drivetrain = new Drivetrain();
        manipulator = new Manipulator();
        panelMech = new PanelMech();
        limelight = new Limelight();
        climber = new Climber();
        pdp = new PowerDistributionPanel(RobotMap.kPDP);
        oi = new OI(); 
        robotmap = new RobotMap();

        Cam_switch = new CameraSwitch(0,1); 
        Cam = CameraServer.getInstance();
        compressor = new Compressor(RobotMap.kPCM);
        compressor.start();
        Cam.startAutomaticCapture(0);
        operatorStick = new Joystick(1);
        operatorA = new JoystickButton(operatorStick, 1);
        operatorB = new JoystickButton(operatorStick, 2);
        
        
        SmartDashboard.putBoolean("Drivetrain Align Complete", false);
    }

    // @Override
    public void robotPeriodic() {
        // //EACH debug only runs once per 10 loops
        // DriveWithJoystick joystick_command = new DriveWithJoystick(OI.driverStick, 0.1)
        drivetrain.debug();
        compressor.start();
        Cam_switch.debug();
        SmartDashboard.putBoolean("Compressor Status", compressor.enabled());
        hopper.debug();
        climber.debug();
        shooter.debug();
        // if(operatorA.get() == true) {
        //     Cam_switch.select(CameraSwitch.kcamera1);
        // }
        // else if (operatorB.get() == true) {
        //     Cam_switch.select(CameraSwitch.kcamera2);
        // }
        // else {
        //     Cam_switch.select(CameraSwitch.kcamera3);
        // }
    }

    @Override
    public void disabledInit() {
        drivetrain.setMotors(0,0);
        hopper.setHopper(0,0);
        manipulator.setManipulator(0);
        manipulator.setRetracted(); 
        shooter.setShooter(0);
        //add emergency stops for panelMech
    }

    @Override
    public void disabledPeriodic() {
         drivetrain.setMotors(0,0);
         hopper.setHopper(0,0);
         manipulator.setManipulator(0);
         manipulator.setRetracted(); 
         shooter.setShooter(0);
         //add emergency stops for panelMech
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
        compressor.start(); 
        System.out.println("This is init");
        

        SmartDashboard.putNumber("Shooter kP", Constants.ShooterTrenchkP);
        SmartDashboard.putNumber("Shooter kF", Constants.ShooterTrenchkFF);
        SmartDashboard.putNumber("Shooter kD", Constants.ShooterTrenchkD);
        SmartDashboard.putNumber("Shooter setpoint", 0);

        manipulator.setRetracted();
        Robot.hopper.hopper_stopper.set(true);
        Robot.shooter.setHood1(false);
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


        // double kp = SmartDashboard.getNumber("Shooter kP", 0);
        // double kf = SmartDashboard.getNumber("Shooter kF", 0);
        // double kd = SmartDashboard.getNumber("Shooter kD", 0);

        // shooter.shooter_leader.getPIDController().setP(kp);
        // shooter.shooter_leader.getPIDController().setFF(kf);
        // shooter.shooter_leader.getPIDController().setD(kd);

        // double setpoint = SmartDashboard.getNumber("Shooter setpoint", 0);
        // if (setpoint==0)
        //     shooter.setShooter(0);
        // else
        //     shooter.setShooterPID(setpoint);
    }
    @Override
    public void testPeriodic() {
    }
}
