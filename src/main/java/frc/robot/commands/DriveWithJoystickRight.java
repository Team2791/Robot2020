// package frc.robot.commands;

// import edu.wpi.first.wpilibj.Joystick;
// import edu.wpi.first.wpilibj.command.Command;
// import frc.robot.Constants;
// import frc.robot.Robot;

// public class DriveWithJoystickRight extends Command {
//     private Joystick stick;
//     private boolean isSquaredTurn;
//     private double deadzone;

//     public DriveWithJoystickRight() {
//         super("DriveWithJoystickRight");
//         requires(Robot.drivetrain);
//     }

//     public void execute() {
    
        
//         Robot.drivetrain.setRightNeo(-1);
//     }

//     public void end() {
//         Robot.drivetrain.setRightNeo(0);
//     }

//     public boolean isFinished() {
//         return false;
//     }
// }