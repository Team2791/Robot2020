package frc.robot.Autons;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.AdjustLimelightOuter;
import frc.robot.commands.AdjustLimelightInner;

public class LimelightInnerOuter extends CommandGroup {
    private boolean useInnerChoice;
    public LimelightInnerOuter (boolean InnerChoice){
        useInnerChoice = InnerChoice;
        addSequential(new AdjustLimelightOuter(useInnerChoice));
        addSequential(new AdjustLimelightInner());
    }
}