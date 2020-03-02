/*************************************
* @author Chris Lane | FRC 2791 | 2020   
*
* API for a multiplexer camera switch. 
*
* The switch should plug into the relay
* port on the RoboRIO given in the 
* constructor. Each relay value 
* corresponds to a pin on the port being
* driven hi or lo (1/0). 
*
* This API essentially utilizes the 
* relay port as two DI/O ports right 
* next to each other
* kforward = pin1  high/pin 2 low,
* kReverse = pin 1 low/pin 2 high,
* kON  = pin 1 high/pin2 high,
* kOff = pin 1 low/pin 2 low.
*
**************************************/
package frc.robot.util.Camera_Switch;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Victor;

public class CameraSwitch implements RelayPortDevice{

   public static final int kcamera1 = 1;
   public static final int kcamera2 = 2;
   public static final int kcamera3 = 3;
   public static final int kcamera4 = 4;



   public static Relay camrelay1;
   public Relay camrelay2;
   public DigitalOutput DO;


   private int cameraState;
    public CameraSwitch(int port1, int port2){
      
       camrelay1 = new Relay(port1);
       camrelay2 = new Relay(port2);
 
    }//Constructor for a CameraSwitch on a single relay port

    public void select(int camSelected){
        /* enter the constant strings for the switch into this method to select cameras
     constants for cameras are- "kcamera(camera#)" (these can be changed in the switch statment)
     This switch has four possible ports. */
      cameraState = camSelected; 
     switch (camSelected) {
        case kcamera1 :
          camrelay1.set(Value.kReverse);
           camrelay2.set(Value.kOff);
            break;
        case kcamera2 :
        camrelay1.set(Value.kForward);
        camrelay2.set(Value.kOff);
         
            break;
        case kcamera3 :
            camrelay2.set(Value.kReverse);
             camrelay1.set(Value.kOff);
        case kcamera4 :
            camrelay2.set(Value.kForward);
           camrelay1.set(Value.kOff);
            break;
        default:
            camrelay1.set(Value.kReverse);
            System.err.print("Camera not properly selected, setting to case 1,Camera1");
            break;
     }//This switch statement is what actually writes to the relay port

    }//Use this method to select the desired camera to connect to the RoboRIO

    public void rawSetRelay(Relay.Value kvalue){

       camrelay1.set(kvalue);
       camrelay2.set(kvalue);

    }//Use this method to set the relay port to a regular relay value

    public void setDirection(Relay.Direction direction){

       camrelay1.setDirection(direction);
       camrelay2.setDirection(direction);

    }//Use this method to set valid directions for the local relay used in this class

    public void setLocalRelay(Relay.Value klocalValue) {

        //unused function
        System.err.println("setLocalRelay(Relay.Value klocalValue); method not utilized in CameraSwitch.java");
        
    }//DO NOT USE THIS METHOD
    public void debug() {
       SmartDashboard.putNumber("Camera Number: ", cameraState);
    }

   }
//end of file-----------------------------------------------------------------------
