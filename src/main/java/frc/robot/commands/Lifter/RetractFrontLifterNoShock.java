package frc.robot.commands.Lifter;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class RetractFrontLifterNoShock extends Command {
    private double output;
    private boolean lvl2;
    public RetractFrontLifterNoShock(double output, boolean level2) {
        super("RetractFrontLifter");
        requires(Robot.lifters);
        this.output = output;
        this.lvl2 = level2;
    }
    public void initialize(){
    }

    public void execute() {
        if(lvl2 == false){
        if(Robot.lifters.getFrontLifterHeight() > Constants.kLifterFrontSlowHeight){
            Robot.lifters.extendFront(Constants.kLifterFrontSlowSpeed);
        }
        else{
            Robot.lifters.extendFront(output);
        }
    }
    else{
        if(Robot.lifters.getFrontLifterHeight() > Constants.kLifterFrontSlowHeightLevel2){
            Robot.lifters.extendFront(Constants.kLifterFrontSlowSpeed);
        }
        else{
            Robot.lifters.extendFront(output);
        }
    }
}

    public void end() {
        Robot.lifters.extendFront(0);
    }

    public boolean isFinished() {
        return Robot.lifters.isFrontRetracted();
    }
}