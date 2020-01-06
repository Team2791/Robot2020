package frc.robot.commands.CargoManipulator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

//This command sets the intake motor speed to a double "speed" and then finishes, while leaving the motor at that given speed
public class SetIntakeMotor extends Command {
    private double speedSetPoint;
    public SetIntakeMotor(){
        super("SetIntakeMotor");
        requires(Robot.cargoManipulator);
    }

    public SetIntakeMotor(double speed){
        speedSetPoint = speed;
    }

    public void execute() {
        Robot.cargoManipulator.setIntakeMotor(speedSetPoint);
    }

    public void end() {
        
    }

    public boolean isFinished() {
         return true;
    }
    
}