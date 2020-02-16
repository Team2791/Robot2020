package frc.robot.Autons;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.Robot;
import frc.robot.commands.MoveHopper;
import frc.robot.commands.Shooter.CheckWheelSpeed;
import frc.robot.commands.Shooter.RetractHood;
import frc.robot.commands.Shooter.RetractHopperPiston;
import frc.robot.subsystems.Shooter;

public class ShooterGroup extends CommandGroup{

    public ShooterGroup(){
        //must switch these errored ones to commands instead of or along with methods predefined. Honestly, just make a command 
        //with those methods and that is it ig? Or retry the command doing all of them. Problems with thht however. Harder to make 
        //stop method to stop everything you run and the old if statements make me feel lke it doesn't do wat I want it to. For example 
        //it will check the wheel speed and then run the hopper ... doesn't wait for wheel speed to be good. Didn't like old if 
        //statements for that because I felt like they would make the hopper stop moving and stop shooting balls continuously until 
        //shooter speed was back especially since the speed dips everytime a ball is shot 
        //This is the command group vs the command with everything is called Shoot()
        //This command group is what is in oi
        addSequential(new RetractHood()); 
        addSequential(new CheckWheelSpeed());
        addSequential(new MoveHopper());
        addSequential(new RetractHopperPiston());

        
    }
}