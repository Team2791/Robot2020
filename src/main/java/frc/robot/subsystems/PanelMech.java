package frc.robot.subsystems;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.Constants;
import frc.robot.RobotMap;


/**
 * Add your docs here.
 */
public class PanelMech extends Subsystem {
    private CANSparkMax panelMech_motor;
    private Solenoid panelMech_soleinoid;
    //private MoveShooterPassive defaultCommand;

    public PanelMech(){
        panelMech_motor = new CANSparkMax(RobotMap.PANEL_NEO, MotorType.kBrushless);
        panelMech_motor.setOpenLoopRampRate(Constants.kNeoRampTime);
        panelMech_motor.set(0);
        panelMech_soleinoid= new Solenoid(RobotMap.kPCM, RobotMap.PANEL_SOLENOID);
    }
    public void setPanelMech(final double velocity) {
        panelMech_motor.set(velocity);
    }
    public void extendPanelMech() {
        panelMech_soleinoid.set(true);
    }

    public void retractPanelMech(){
        panelMech_soleinoid.set(false);
    }
    @Override
    protected void initDefaultCommand() {

    }

    public void debug() {
    }
}