package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import frc.robot.commands.*;
import frc.robot.commands.Shooter.StopShooter;
import frc.robot.commands.Shooter.CloseHopperPiston;
import frc.robot.commands.Shooter.MoveShooterWall;
import frc.robot.commands.Shooter.OpenHopperPiston;
// import frc.robot.commands.auto.SetLimit;
import frc.robot.controller.AnalogButton;
import frc.robot.controller.DPadButton;
import frc.robot.controller.MultiButton;
import frc.robot.Autons.ShooterGroupLong;
import frc.robot.Autons.StopShooterGroupLong;
import frc.robot.Autons.ShooterGroupWall;
import frc.robot.Autons.StopShooterGroupWall;
//import frc.robot.subsystems.Camera; 

// import frc.robot.commands.Lifter.ExtendBothLifters;
import frc.robot.util.Util;
// import frc.robot.commands.CargoManipulator.ScoreInRocketCalculated;
// import frc.robot.commands.CargoManipulator.ScoreInRocketDropper;
// import frc.robot.commands.auto.AutoSetLifterPots;
                    //               _____
                    //              |     |
                    //              | | | |
                    //              |_____|
                    //        ____ ___|_|___ ____
                    //       ()___)         ()___)
                    //       // /|           |\ \\
                    //      // / |           | \ \\
                    //     (___) |___________| (___)
                    //     (___)   (_______)   (___)
                    //     (___)     (___)     (___)
                    //     (___)      |_|      (___)
                    //     (___)  ___/___\___   | |
                    //      | |  |           |  | |
                    //      | |  |___________| /___\
                    //     /___\  |||     ||| //   \\
                    //    //   \\ |||     ||| \\   //
                    //    \\   // |||     |||  \\ //
                    //     \\ // ()__)   (__()
                    //           ///       \\\
                    //          ///         \\\
                    //        _///___     ___\\\_
                    //       |_______|   |_______|

public class OI {
    public static Joystick driverStick;
    public static Joystick operatorStick;
    private Button driveButton;
    private Button driverLB, driverRB;
    private Button driverStart, driverBack;
    private Button operatorStart;
    private Button driverA, driverB, driverY;
    private Button driverDPadDown, driverDPadRight, driverDPadLeft;
    private Button operatorRB, operatorLT, operatorLB, operatorRT;
    public Button operatorLS, operatorBack;
    private Button driverX;
    private Button driverRS, driverLS;
    private Button driverRX;
    protected Button operatorLeftJoystickUsed, operatorRightJoystickUsed, operatorDPadDown, operatorDPadLeft;
    private Button operatorA, operatorB, operatorX, operatorY;
    public OI() {
        driverStick = new Joystick(0);
        operatorStick = new Joystick(1);
        initButtons();
        initUsed();

       driveButton.whileHeld(new DriveWithJoystick(driverStick, 0.1)); // TODO CHANGE DEADZONE VALUE IT MIGHT NOT BE THE SAME 
        
       //LEAVE OUT driverStart.whileHeld(new ExtendBothLifters(.8,false,driverStick));

        //THESE TWO LINES ARE FOR TESTING
        //LEAVE OUT driverA.whenPressed(new AutoSetLifterPots());
        //LEAVE OUT driverB.whenPressed(new ExtendBothLifters(.8,false,driverStick,false));

        // driverA.whenPressed(new MoveShooter());
        // driverA.whenReleased(new StopShooter());
        // driverB.whenPressed(new MoveElevator());
        // driverB.whenReleased(new StopElevator());
        // driverX.whenPressed(new MoveHopper());
        // driverX.whenReleased(new StopHopper());
        // driverB.whenPressed(new DrivetrainBackwards());
        // driverB.whenReleased(new StopDrivetrain());
        // driverY.whenPressed(new MoveDrivetrain());
        // driverY.whenReleased(new StopDrivetrain());
        driverA.whenPressed(new MoveManipulator());
        driverY.whenPressed(new StopManipulator());
        driverX.whenPressed(new IrHopper());
        driverX.whenReleased(new StopHopper());
        driverB.whenPressed(new OpenHopperPiston());
        driverB.whenReleased(new CloseHopperPiston());
        // driverRB.whenPressed(new ShooterGroupWall());
        // driverRB.whenReleased(new StopShooterGroupWall());
        // driverLB.whenPressed(new ShooterGroupLong());
        // driverLB.whenReleased(new StopShooterGroupLong());
        driverDPadLeft.whenPressed(new setCameraTwo());
        driverDPadLeft.whenReleased(new setCameraOne());
        driverDPadDown.whenPressed(new ReverseHopper()); 
        driverDPadDown.whenReleased(new StopHopper());
        driverDPadRight.whenPressed(new MoveShooterWall());
        driverDPadRight.whenReleased(new StopShooter());
        // driverLB.whenPressed(new runHopperElevator());
        // driverLB.whenReleased(new stopHopperElevator());
        // driverLS.whenPressed(new runElevatorShooter());
        // driverLS.whenReleased(new stopElevatorShooter());
        // operatorLeftJoystickUsed.whenPressed(new RunHopperWithJoystick(operatorLeftJoystickUsed));
        // //true does right hp far rocket path, false does right hp bay 1 ship path
        // // driverY.whenReleased(new StopCargoMotor());
        // driverRB.whileHeld(new DriveWithJoystickLeftTalon());
        // driverLB.whileHeld(new DriveWithJoystickRightTalon());
        // // driverY.whileHeld(new DriveWithJoystickLeftTalon());
        // //driverX.whenPressed(new DriveWithJoystickLeft(driverStick, 0.1));
        // driverB.whileHeld(new DriveWithJoystickRight());
        // // driverX.whenPressed(new DriveWithJoystickLeft());
        // driverX.whenReleased(new StopDrive());
        // // driverRX.whileHeld(new PreciseTurnJoystick(driverStick, 0.1));

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
            // driverDPadUp = new DPadButton(driverStick, DPadButton.kDPadUp);
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
        }

        catch (Exception error){
            System.out.println("Error Init With Buttons");
            error.printStackTrace();
        }
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
