package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.buttons.Trigger;
import edu.wpi.first.wpilibj.command.InstantCommand;
import frc.robot.Autons.ManipulatorToIrHopper;
import frc.robot.Autons.ManipulatorToLimitHopper;
import frc.robot.Autons.StopShooterGroup;
import frc.robot.Autons.TrenchHoodAndFire;
import frc.robot.Autons.WallHoodAndFire;
import frc.robot.commands.Shooter.*;
import frc.robot.commands.Climb.*;
import frc.robot.commands.IntakeToHopper.*;
import frc.robot.commands.PanelMech.*;

import frc.robot.commands.*;
import frc.robot.commands.Shooter.StopShooter;
import frc.robot.commands.tempTest.*;
import frc.robot.commands.Climb.*;
import frc.robot.controller.AnalogButton;
import frc.robot.controller.DPadButton;
import frc.robot.controller.MultiButton;

import frc.robot.util.Camera_Switch.CameraSwitch;
import frc.robot.util.Util;

public class OI {
    public static Joystick driverStick;
    public static Joystick operatorStick;
    public static Joystick pitStick; 
    private Button driveButton;
    private Button driverLB, driverRB;
    private Button driverStart, driverBack;
    private Button operatorStart;
    private Button driverA, driverB, driverY;
    private Button driverDPadDown, driverDPadRight, driverDPadLeft, driverDPadUp;
    private Button operatorRB, operatorLT, operatorLB, operatorRT;
    public Button operatorLS, operatorBack;
    private Button driverX;
    private Button driverRS, driverLS;
    private Button driverRX;
    protected Button operatorLeftJoystickUsed, operatorRightJoystickUsed, operatorDPadDown, operatorDPadLeft, operatorDPadRight;
    private Button operatorA, operatorB, operatorX, operatorY;
    private Button pitA, pitB, pitX, pitY; 


    public OI() {


        driverStick = new Joystick(0);
        operatorStick = new Joystick(1);
        pitStick = new Joystick(3);

        initButtons();
        initUsed();

        driveButton.whileHeld(new DriveWithJoystick(driverStick, 0.1)); // TODO CHANGE DEADZONE VALUE IT MIGHT NOT BE THE SAME 

        operatorDPadLeft.whenPressed(new LongShotHood());
        operatorDPadRight.whenPressed(new WallShotHood());

        new Trigger(){
            @Override
            public boolean get() {
                // TODO Auto-generated method stub
                return operatorStick.getRawAxis(1) > 0.8;
            }
        }.whenActive(new ReverseHopper());
 
        new Trigger(){
            @Override
            public boolean get() {
                // TODO Auto-generated method stub
                return operatorStick.getRawAxis(1) > 0.8;
            }
        }.whenInactive(new StopHopper());

        operatorA.whenPressed(new InstantCommand(() -> {
            Robot.hopper.hopper_stopper.set(true);
        })) ;

        operatorB.whileHeld(new SetPanelMechSlow());
        operatorB.whenPressed(new ExtendPanelMech());
        operatorB.whenReleased(new DefaultPanelMech());
        operatorB.whenReleased(new StopPanelMech());

        operatorA.whileHeld(new SetPanelMechFast());
        operatorA.whenPressed(new ExtendPanelMech());
        operatorA.whenReleased(new StopPanelMech());
        operatorA.whenReleased(new DefaultPanelMech());

        operatorY.whileHeld(new MoveHopperWall());//moves hopper at constant speeds
        operatorY.whenReleased(new StopHopper());

        operatorX.whileHeld(new IrHopper());
        operatorX.whenReleased(new StopHopper());

        operatorRB.whenPressed(new WallShot());
        // // operatorRB.whenPressed(new InstantCommand(() -> {
        //     Robot.Cam_switch.select(CameraSwitch.kcamera1);;
        // }));

        operatorLB.whenPressed(new LongShot());
  
        new Trigger(){
            @Override
            public boolean get() {
                // TODO Auto-generated method stub
                return operatorStick.getRawAxis(3) > 0.25;
            }
        }.whenActive(new WallHoodAndFire());

        new Trigger(){
            @Override
            public boolean get() {
                // TODO Auto-generated method stub
                return operatorStick.getRawAxis(3) > 0.25;
            }
        }.whenInactive(new StopShooterGroup());

        new Trigger(){
            @Override
            public boolean get() {
                // TODO Auto-generated method stub
                return operatorStick.getRawAxis(2) > 0.25;
            }
        }.whenActive(new StopShooter());

        // driverA.whenPressed(new ManipulatorToLimitHopper());
        driverA.whileHeld(new ManipulatorToIrHopper());
        
        driverA.whenPressed(new InstantCommand(() -> {
                Robot.Cam_switch.select(CameraSwitch.kcamera2);;
            }));
        driverA.whenPressed(new InstantCommand(() -> {
            Robot.hopper.setRetracted();;
            
        }));
        driverA.whenReleased(new StopManipulator());
       driverA.whenReleased(new StopHopper());
        

        driverDPadRight.whileHeld(new DrivetrainAlignToGoal());

        driverDPadDown.whenPressed(new InstantCommand(() -> {
            Robot.climber.setRetracted();;
        }));
        
        driverStart.whenPressed(new ReleasePin(true, true));

        driverX.whileHeld(new WinchClimb(true));
        driverX.whenReleased(new StopWinchClimb());

        driverY.whileHeld(new WinchClimb(false));
        driverY.whenReleased(new StopWinchClimb());

        driverB.whileHeld(new TrenchHoodAndFire());
        driverB.whenReleased(new StopShooterGroup());

        //Pit Controller commands to reset the climb
        pitA.whenPressed(new testManipulator());            //Extends Manipulator
        pitB.whenPressed(new testStopManipulator());        //Retracts Manipulator
        pitX.whenPressed(new testExtendClimber());          //Extends Climber
        pitY.whenPressed(new testRetractClimber());         //Retracts Climber
        
    }

    private void initButtons(){
        try{
//DRIVER BUTTONS//
            driverA = new JoystickButton(driverStick, 1);
            driverB = new JoystickButton(driverStick, 2);
            driverX = new JoystickButton(driverStick,3);
            driverY = new JoystickButton(driverStick,4);
            driverBack = new JoystickButton(driverStick, 7);
            driverStart = new JoystickButton(driverStick, 8);
            driverRB = new JoystickButton(driverStick, 6);
            driverLB = new JoystickButton(driverStick, 5);
            driverLS = new JoystickButton(driverStick,9);
            // driverRS = new JoystickButton(driverStick,10);
            driverRX = new AnalogButton(driverStick, 4);
            driverDPadDown = new DPadButton(driverStick, DPadButton.kDPadDown);
            driverDPadRight = new DPadButton(driverStick, DPadButton.kDPadRight);
            driverDPadUp = new DPadButton(driverStick, DPadButton.kDPadUp);
            driverDPadLeft = new DPadButton(driverStick, DPadButton.kDPadLeft);
            driveButton = new MultiButton(new Button[] {
                new AnalogButton(driverStick, 3, 2, 0, 0.2),
                driverRB,
                driverLB
            });

//OPERATOR BUTTONS//
            operatorA = new JoystickButton(operatorStick, 1);
            operatorB = new JoystickButton(operatorStick, 2);
            operatorX = new JoystickButton(operatorStick, 3);
            operatorY = new JoystickButton(operatorStick, 4);
            operatorBack = new JoystickButton(operatorStick,7);
            operatorStart = new JoystickButton(driverStick, 8);
            operatorRB = new JoystickButton(operatorStick, 6);
            operatorLB = new JoystickButton(operatorStick, 5);
            operatorLT = new AnalogButton(operatorStick, 2);
            operatorRT = new AnalogButton(operatorStick, 3);
            operatorLS = new AnalogButton(operatorStick, 1);
            operatorDPadDown = new DPadButton(operatorStick, DPadButton.kDPadDown);
            operatorDPadLeft = new DPadButton(operatorStick, DPadButton.kDPadLeft);
            operatorDPadRight = new DPadButton(operatorStick, DPadButton.kDPadRight);
        }

        catch (Exception error){
            System.out.println("Error Init With Buttons");
            error.printStackTrace();
        }
//TEMPORARY PIT CONTROLS//
            pitA = new JoystickButton(pitStick, 1);
            pitB = new JoystickButton(pitStick, 2); 
            pitX = new JoystickButton(pitStick, 3); 
            pitY = new JoystickButton(pitStick, 4); 
    }
    
    private void initUsed(){
        operatorLeftJoystickUsed = new Button() {
			@Override
			public boolean get() {
				return Math.abs(Util.deadzone(Constants.DEADZONE, operatorStick.getRawAxis(1), 1.0)) > 0.08;
			}
		};
    }
}