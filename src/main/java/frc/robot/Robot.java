package frc.robot;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.TimedRobot;
// import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.HatchManipulator;
import edu.wpi.first.wpilibj.Compressor;
// import edu.wpi.cscore.UsbCamera;
// import edu.wpi.first.wpilibj.CameraServer;

                                                                                                   
//                                                      . ...                                         
//                                              .(%,%%%%%,%%%%%%/%%#*                                 
//                                           /%%%%%,#%%%%,%%%%%%*%%%%%,                               
//                                       ,/*%%%%%%%,#%%%%,%%%%%%*%%%%%*#.                             
//                                    *#%%%*%%%%%%%,#%%%%,#%%%%%*%%%%%*%#,                            
//                                ,%%%%%%%%*%%%%%%%,#%%%%%#,#%%%*%%%%%*%%%%*                          
//                           *%%%(#%%%%%%%%*%%%%%%%,#%%%%%%%%%%%*%%%%%*%%%%%%*,,,..                   
//                        *#%%%%%/#%%%%%%%%*%%%%%%(*%%%%%%%%%%%%*%%%%%*%%%%%%%%%%%#,%%%%%#*           
//                     .%%%%%%%%%(#%%%%%%%%*%%%%%,#%%%%%%%%%%%%%*%%%%%*%%%%%%%%%%%#,%%%%%%%%%%,       
//                  .#%,%%%%%%%%%(#%%%%%%%%*%%%%%%(,/%%%%%%%%%%%*%%%%%*%%%%%%%%%%%#,%%%%%%%%%%%/      
//    .(#%#/       (%%%,%%%%%%%%%/#%%%%%%%%*%%%%%%%%%#*(%%%%%%%%*%%%%(,%%%%%%%%%%%#,%%%%%%%%%%%%#  *#,  
//   %%#*. .#%#(#%,(%%%,%%%%%%%%%(/%%%,,%%%*%%%%%%%%%%%%*#%%%%%%*%*./%%%%%%%%%%%%%**%%%%%%%%%%%%%, .%* 
//  (%%,      .,*##,#%%,%%%%%%%%%%%#*,/%%%%*%%%%%%%%%%%%%%%%%%%%*%%%%%%%%%%%%%%%(,%%%%%%%%%%%%%%%/ /%( 
//  .%%%%.      %(*%%%%/***.%%%%%%%%%%%%%%%,%%%%%%%%%%%%%%%%%%/,%%%%%%%%%%%%%%%%#%%%%%%%%%%%%%%%%%%%( 
//  .%%%%,     **#%%%%%%%%,#%%%%%%%%%%%%%%%%%/,,(#%%%%%%%%%%%#*#%%%%%%%%%%%%%%%%%%%%%%#,.%%%%%%%%%%%/ 
// ,%%%%/     .%%%%%%%%%%(#%%%%%%%%%%%%%%%%%%%%%%%%%#,,(%%%%%%%%%%%%%%%%%%%%%%%%%%. /%%%,%%%%%%%%%%%( 
// .%%%*.     *%%%%%%%%%%%%%%%( (%%%%%%%%%%%%%%%%%%%%%%%%(,%%%%%%%%%%%%%%%%%%%%%%%%%%%%%**%%%%%%%%%%* 
//   ..       /%%%%%%%%%%%%%%((%%%%%%%%%%%%%%%%%%%%%%%%%%%/ %%%%%%%%%%% ,%%%%%%%%%%%%%%%%%%%/,/%%%%/  
//            /%%%%%%%%%%%%%%,%%%%%%%%%%%/. %%%%%./%%%%%%%%%#*/,*,%%%%%%%%*/%%%%%%%%%%%%%%%%%%%%#/,   
//            .%%%%%%%%%%%%%%,%%%%%%%%%,#%%%%%%%,%%%%%%%%%%%%(%%%*%%%%%%%%%%,%%%%%%#%%%%%%%%%%%%%,    
//             /%%%%,(*%%%%%%,%%%%%%%(*%%%%%%%,#%%%%%%%%%%%%%%%%%*%%%%%%%%%/#%%%%%%#*(%%%%%%%%%%(     
//              %#,#%%*%%%%%%*%%%%%%,(%%%%%%%%,%%%%%%%%%%%%%%%%%%*%%%%%%%%#*%%%%%%%%%%,#%%%%%%%/.     
//              #%%%%( /%%%#.   *%(/%%%%%%%%%%,%%%%%%%%%%%%% *%%%*%%%%%%%%%%/*#%%%%%%%%%(*%%%%#       
//            .%%%%%%%%%%%*.     .%%%%%%%%%%%%,%%%%%%%%%%%(/%%%%%*%%%*#%%%%%%%%(*%%%%%%%%,%%%%/       
//             %%%%%%%%%#,       %%%%%%%%%%%##,#%%%%%%%%%,#%%%%%%*%%%#,#%%%%%%%%*%%%%%%%*##/*.        
//             *%%**%%%,        .%%%%%%,         (%%%%%%%,#%%%%%%*%%%(#%%%%%%%#%,%%%%#.               
//             .*.#%**.           (%%%%,         .%%%%%%%,#%%%%%%*%%%(#%%%%%%%( ,%%%%*                
//             .%%%%,              ,%%%%(         %%%%%%%,#%%%%(,. *%(#%%%%%%%%, /%#*                 
//             .%%%,                 *%%%%(       *%%%%%%,#%%%*      .,,.  (%%%/                      
//             /%%%,                  ,%%%%%*       ,#%%%,#%%%%(         ./%%%%(                      
//            ,%%%%,                   .##%%%%/        .(,(,,%%%%,      ,%%%%/,                       
//            /%%%#.                      .#%##/            ,%%%%%*     *%%(,                         
//            .%#*                                            #%%%%#.                                 
//                                                               ...                                  


public class Robot extends TimedRobot {
    long loopCounter = 0;

    public static OI oi;
    public static Drivetrain drivetrain;
    public static HatchManipulator hatchManipulator; 
    public static PowerDistributionPanel pdp;
    public static Compressor compressor;
    // private Command autoCommand;

    // public static UsbCamera driver_cam;


    @Override
    public void robotInit() {
        drivetrain = new Drivetrain();
        pdp = new PowerDistributionPanel(RobotMap.kPDP);
         oi = new OI(); 
        robotmap = new RobotMap();
        SmartDashboard.putNumber("Shooter Speed Percent", Constants.SHOOTER_OUTPUT);
        SmartDashboard.putNumber("Hopper Speed Percent", Constants.HOPPER_OUTPUT);
        SmartDashboard.putNumber("Elevator Speed Percent", Constants.ELEVATOR_OUTPUT);
        //These putNumbers are set once, default to Constants, and are never updated
        //Used for prototyping to change speeds through SmartDashboard rather than deploying code through constants
        //Remember to CHANGE CONSTANTS to match the ideal speeds we find
    }
    
    @Override
    public void robotPeriodic() {
        // //EACH debug only runs once per 10 loops
        loopCounter += 1;
        drivetrain.debug();
        hopper.debug();
        shooter.debug();
        elevator.debug();
    }

    @Override
    public void disabledInit() {
        drivetrain.setMotors(0);
    }

    @Override
    public void disabledPeriodic() {
         drivetrain.setMotors(0);
    }

    @Override
    public void autonomousInit() {
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
         // 0.5 power is the sweet spot for wall, 0.8  for current at angle of 39 degrees
        double shootSpeed = SmartDashboard.getNumber("Shooter Speed Percent", Constants.SHOOTER_OUTPUT);
        Robot.shooter.setShooter(shootSpeed);   
        Scheduler.getInstance().run();
    }
    
    @Override
    public void testPeriodic() {
    }
}
