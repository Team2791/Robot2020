package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Constants;
import frc.robot.Robot;

public class FollowLine extends Command {
    int pZone;
    public FollowLine() {
        super("FollowLine");
        requires(Robot.drivetrain);
        pZone = 0;
    }

    public void execute() {
        int lineSensors = Robot.drivetrain.getLineSensors();
        switch (lineSensors) {
        case 0:
        case 6:
        case 9:
        case 15:
            Robot.drivetrain.setMotors(Constants.kLineFollowStraight, Constants.kLineFollowStraight);
            break;
        case 1:
            Robot.drivetrain.setMotors(-Constants.kLineFollowTurn, Constants.kLineFollowTurn);
            break;
        case 2:
        case 5:
        case 7:
        case 3:
        case 11:
            Robot.drivetrain.setMotors(0, Constants.kLineFollowTurn);
           break;
        case 4:
        case 10:
        case 12:
        case 13:
        case 14:
            Robot.drivetrain.setMotors(Constants.kLineFollowTurn, 0);
            break;
        case 8:
            Robot.drivetrain.setMotors(Constants.kLineFollowTurn, -Constants.kLineFollowTurn);
            break;
        default:
            Robot.drivetrain.setMotors(0, 0);
            break;
        }
    }

    public void end() {
        Robot.drivetrain.setMotors(0, 0);
    }

    public boolean isFinished() {
        return false;
    }
}