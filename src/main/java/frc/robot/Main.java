//Do NOT modify this file unless you know what you are doing.

package frc.robot;

import edu.wpi.first.wpilibj.RobotBase;

public final class Main {
    private Main() {
    }

    public static void main(String... args) {
        RobotBase.startRobot(Robot::new);
    }
}
