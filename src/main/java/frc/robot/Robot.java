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
        
    }
    
    @Override
    public void robotPeriodic() {
        // //EACH debug only runs once per 10 loops
        loopCounter += 1;
        drivetrain.debug();

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
        Scheduler.getInstance().run();
    }
    
    @Override
    public void testPeriodic() {
    }
}
