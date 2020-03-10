package frc.robot;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.setCameraOne;
import frc.robot.commands.AutoCommands.FourBallTrench;
import frc.robot.controller.DPadButton;
import frc.robot.subsystems.*;
import frc.robot.util.LedMode;
import frc.robot.util.Camera_Switch.CameraSwitch;
import frc.robot.OI.*;
import edu.wpi.first.wpilibj.DriverStation;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.Joystick;

import edu.wpi.first.cameraserver.CameraServer;

import frc.robot.subsystems.Climber;
import frc.robot.subsystems.*;


                                                                                                   
public class Robot extends TimedRobot {

	long loopCounter = 0;

    //m_ds = DriverStation.getInstance;


    public static Joystick pitStick;
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
    private Button pitDPadDown, pitDPadRight, pitDPadLeft, pitDPadUp;

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
        pitStick = new Joystick(3);
        pitDPadDown = new DPadButton(pitStick, DPadButton.kDPadDown);
        pitDPadLeft = new DPadButton(pitStick, DPadButton.kDPadLeft);
        pitDPadUp = new DPadButton(pitStick, DPadButton.kDPadUp);
        pitDPadRight = new DPadButton(pitStick, DPadButton.kDPadRight);

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
        manipulator.debug();
       
        if(pitDPadUp.get() == true) {
            Cam_switch.select(CameraSwitch.kcamera1);
        }
        else if(pitDPadRight.get() == true) {
            Cam_switch.select(CameraSwitch.kcamera2);
        }
        else if(pitDPadDown.get() == true){
            Cam_switch.select(CameraSwitch.kcamera3);
        }
        else if(pitDPadLeft.get()) {
            Cam_switch.select(CameraSwitch.kcamera4);
        }
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
       
        // autoCommand.start();
    }

    @Override
    public void autonomousInit(){
        // if(m_ds.isAutonomous()){ 
        //     new FourBallTrench();
        // }
        drivetrain.driveTime.start();
    }

    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        drivetrain.BackupAndShoot();

        
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
        Robot.shooter.setHood1(true);
    }


    @Override
    public void teleopPeriodic() {
       
        Scheduler.getInstance().run();
        
        
        double kp = SmartDashboard.getNumber("Shooter kP", Constants.DrivekP);
        double kf = SmartDashboard.getNumber("Shooter kF", Constants.DrivekFF);
        double kd = SmartDashboard.getNumber("Shooter kD", Constants.DrivekD);

        shooter.shooter_leader.getPIDController().setP(kp);
        shooter.shooter_leader.getPIDController().setFF(kf);
        shooter.shooter_leader.getPIDController().setD(kd);

        double setpoint = SmartDashboard.getNumber("Shooter setpoint", 0);
        if (setpoint==0)
            shooter.setShooter(0);
        else
            shooter.setShooterPID(setpoint);
    }
    @Override
    public void testPeriodic() {
    }
}
