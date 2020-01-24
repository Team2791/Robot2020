// package frc.robot.commands;

// import edu.wpi.first.wpilibj.Joystick;
// import edu.wpi.first.wpilibj.command.Command;
// import frc.robot.Constants;
// import frc.robot.Robot;

// public class DriveWithJoystickLeftTalon extends Command {
//     private Joystick stick;
//     private boolean isSquaredTurn;
//     private double deadzone;

//     public DriveWithJoystickLeftTalon() {
//         super("DriveWithJoystickLeftTalon");
//         requires(Robot.drivetrain);
//     }

//     public void execute() {
    
        
//         Robot.drivetrain.setLeftTalon(-1);
//     }

//     public void end() {
//         Robot.drivetrain.setLeftTalon(0);
//     }

//     public boolean isFinished() {
//         return false;
//     }
// }