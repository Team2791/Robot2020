package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import frc.robot.commands.*;
import frc.robot.commands.auto.SetLimit;
import frc.robot.controller.AnalogButton;
import frc.robot.controller.DPadButton;
import frc.robot.controller.MultiButton;

import frc.robot.commands.Elevator.MagicMotionHatchBall;
import frc.robot.commands.Elevator.RunLiftWithJoystick;
import frc.robot.commands.HatchManipulator.GetPanelAutomatedHeld;
import frc.robot.commands.HatchManipulator.GetPanelAutomatedRelease;
import frc.robot.commands.HatchManipulator.ScorePanelAutomatedHeld;
import frc.robot.commands.HatchManipulator.ScorePanelAutomatedRelease;
import frc.robot.commands.CargoManipulator.FastShootCargo;
import frc.robot.commands.CargoManipulator.HoldCargoIntake;
import frc.robot.commands.CargoManipulator.ReleaseCargoIntake;
import frc.robot.commands.CargoManipulator.ScoreCargoShip;
import frc.robot.commands.CargoManipulator.SlowShootCargo;
import frc.robot.commands.CargoManipulator.StopCargoMotor;
import frc.robot.commands.CargoManipulator.CargoHumanPlayerIntake;
import frc.robot.commands.auto.PlatformAuto3;
// import frc.robot.commands.auto.StopTotal;
// import frc.robot.commands.Lifter.ExtendBothLifters;
import frc.robot.commands.Lifter.RetractBothLifters;
import frc.robot.util.Util;
// import frc.robot.commands.CargoManipulator.ScoreInRocketCalculated;
// import frc.robot.commands.CargoManipulator.ScoreInRocketDropper;
// import frc.robot.commands.auto.AutoSetLifterPots;
import frc.robot.commands.auto.PlatformAuto2;
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
    // private Button driverRS, driverLS;
    private Button driverRX;
    protected Button operatorLeftJoystickUsed, operatorRightJoystickUsed, operatorDPadDown, operatorDPadLeft;
    private Button operatorA, operatorB, operatorX, operatorY;
    public OI() {
        driverStick = new Joystick(0);
        operatorStick = new Joystick(1);
        initButtons();
        initUsed();

        driveButton.whileHeld(new DriveWithJoystick(driverStick, 0.1)); //this should be the default command of the DT
        //LEAVE OUT driverStart.whileHeld(new ExtendBothLifters(.8,false,driverStick));
        driverBack.whileHeld(new RetractBothLifters(-1));

        operatorLeftJoystickUsed.whenPressed(new RunLiftWithJoystick(operatorLeftJoystickUsed)); //Elevator manual drive
        operatorA.whenPressed(new MagicMotionHatchBall(operatorStick, Constants.kElevatorMinHeight + 3, Constants.kElevatorMinHeight + 3)); //This will make the lift go to the bottom + 3 pot turns
        operatorB.whenPressed(new MagicMotionHatchBall(operatorStick, Constants.kELEVATOR_PANEL_ONE, Constants.kELEVATOR_BALL_SLAM_SHIP)); //Sets elevator to panel height 1 / ball height 1
        operatorX.whenPressed(new MagicMotionHatchBall(operatorStick, Constants.kELEVATOR_PANEL_TWO, Constants.kELEVATOR_BALL_TWO)); //Sets elevator to panel height 2 / ball height 2
        operatorY.whenPressed(new MagicMotionHatchBall(operatorStick, Constants.kELEVATOR_PANEL_THREE, Constants.kELEVATOR_BALL_THREE)); //Sets elevator to panel height 2 / ball height 2

        //THESE TWO LINES ARE FOR TESTING
        //LEAVE OUT driverA.whenPressed(new AutoSetLifterPots());
        //LEAVE OUT driverB.whenPressed(new ExtendBothLifters(.8,false,driverStick,false));

        driverA.whenPressed(new GetPanelAutomatedHeld()); //Gets panel
        driverA.whenReleased(new GetPanelAutomatedRelease()); //Gets panel
        driverB.whenPressed(new ScorePanelAutomatedHeld()); //Scores panel
        driverB.whenReleased(new ScorePanelAutomatedRelease()); //Scores panel

        // driverX.whenPressed(new PlatformAuto2()); //Runs autonomous lifting sequence

        //FOR TESTING ONLY
        driverX.whenPressed(new LimelightFollow());

        driverStart.whenPressed(new PlatformAuto3()); //Runs autonomous lifting sequence
        //LEAVE OUT driverY.whenPressed(new StopTotal()); //Use this to cancel the autonomous lifting sequence if something has gone wrong

        driverY.whenPressed(new ScoreCargoShip());
        driverY.whenReleased(new StopCargoMotor());
        
        driverRX.whileHeld(new PreciseTurnJoystick(driverStick, 0.1));
        driverDPadDown.whileHeld(new FollowLineAndSetLift(Constants.kELEVATOR_PANEL_ONE+10.0));
        driverDPadRight.whileHeld(new FollowLineAndSetLift(Constants.kELEVATOR_PANEL_TWO));
        driverDPadLeft.whileHeld(new FollowLineAndSetLift(Constants.kELEVATOR_PANEL_THREE));

        operatorDPadLeft.whenPressed(new ScoreCargoShip());
        operatorDPadLeft.whenReleased(new StopCargoMotor());

        operatorRT.whenPressed(new HoldCargoIntake()); //Intakes cargo off floor
        operatorRT.whenReleased(new ReleaseCargoIntake());

        operatorRB.whenPressed(new CargoHumanPlayerIntake()); //Intakes cargo from human player
        operatorRB.whenReleased(new StopCargoMotor());

        operatorLB.whenPressed(new SlowShootCargo()); //Shoots cargo slow
        operatorLB.whenReleased(new StopCargoMotor());

        operatorLT.whenPressed(new FastShootCargo()); //Shoots cargo fast
        operatorLT.whenReleased(new StopCargoMotor());
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
            // driverLS = new JoystickButton(driverStick,9);
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
