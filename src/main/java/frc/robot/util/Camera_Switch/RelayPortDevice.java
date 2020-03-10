/****************************************
* @author Chris Lane | FRC 2791 | 2020   
*
* Interface for a relay port device. 
*
* This interface is for interfacing with 
* devices that connect to the relay ports
* on the RoboRIO that are not regular IFI
* SPIKE or similar relays. 
*
*****************************************/

package frc.robot.util.Camera_Switch;
import edu.wpi.first.wpilibj.Relay;

public interface RelayPortDevice {


public void rawSetRelay(Relay.Value kValue);//Set the raw relay port

public void setDirection(Relay.Direction direction);//Indirectly set relay port valid direction 

public void setLocalRelay(Relay.Value klocalValue);//Set the local relay values from a static field


}
//End of file-----------------------------------------------------------------------