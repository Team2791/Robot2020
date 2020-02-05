package frc.robot.Autons;

import edu.wpi.first.wpilibj.command.CommandGroup;
import frc.robot.commands.MoveDrivetrain;
import frc.robot.commands.MoveShooter;
import frc.robot.commands.StopDrivetrain;
import frc.robot.commands.StopShooter;

public class stopDrivetrainShooter extends CommandGroup {
  /**
   * Add your docs here.
   */
  public stopDrivetrainShooter() {
    addParallel(new StopDrivetrain());
    addParallel(new StopShooter());
    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
  }
}