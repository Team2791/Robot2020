package frc.robot.commands.Elevator;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.subsystems.Elevator;
import frc.robot.util.DelayedBoolean;

public class SetLiftHeightMagicMotion extends Command {
    private double targetHeight;
    protected Elevator elevator;
    protected DelayedBoolean finishDelayedBoolean;
    
    
    public SetLiftHeightMagicMotion(double height) {
        super("GoToHeight");
        requires(Robot.elevator);
        this.targetHeight = height;
        this.elevator = Robot.elevator;
        finishDelayedBoolean = new DelayedBoolean(0.3);
    }


    @Override
    protected void initialize() {
        elevator.setBreak(false);
        elevator.setTargetMagicMotion(targetHeight);
        System.out.println("Trying to get to RAW height "+targetHeight);
        finishDelayedBoolean.update(false);
    }

    @Override
    protected void execute() {
        Robot.elevator.command = "SetLiftHeightMagicMotion";
        //elevator.setTargetMagicMotion(SmartDashboard.getNumber("Elevator target height", Constants.kElevatorMinHeight + 3));
    }

    @Override
    public boolean isFinished() {
        double error = elevator.getMagicMotionInstantError();
        // double error = Robot.elevator.getSensorPosition() - targetHeight;
        // SmartDashboard.putNumber("Elevator - error", error);
        
        if(Robot.elevator.atTop()){
            return true;
        }
        return finishDelayedBoolean.update(Math.abs(error) < Constants.kELEVATOR_ERROR_LEVEL);
    }

    @Override
    protected void end () {
        System.out.println("Lift magic motion done!");
        Robot.elevator.setBreak(true);
    }

    @Override
    protected void interrupted() {
        end();
    }
}