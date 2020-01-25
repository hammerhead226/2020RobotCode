/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;
import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;

//import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
//import com.ctre.phoenix.motorcontrol.can.TalonFX;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import frc.robot.Constants;
//import frc.robot.Robot;

public class Leds{
  /**
   * A class for driving addressable LEDs.
   */

  //change to real port number
  AddressableLED Led1 = new AddressableLED(5);
  AddressableLEDBuffer Led1Buffer = new AddressableLEDBuffer(500);

  public void LEDS(){

  //change length to real length
  Led1.setLength(500);

  // Set the data
  Led1.setData(Led1Buffer);
  Led1.start();
  }{

    final String color = new String("set");
      //options include "set,red,blue,green,purple,alternate,wave"

    if(color=="set"){
      for (int i = 0; i < Led1Buffer.getLength(); i++) {
      // Sets the LED by number
      Led1Buffer.setRGB(i, 0,0,0);
      }
    if(color=="red"){
      for (int i = 0; i < Led1Buffer.getLength(); i++) {
      // Sets the LED to red
      Led1Buffer.setRGB(i, 255,0,0);
      }
    if(color=="blue"){
      for (int i = 0; i < Led1Buffer.getLength(); i++) {
      // Sets the LED to blue
      Led1Buffer.setRGB(i, 0,0,255);
      }
    }if(color=="green"){
      for (int i = 0; i < Led1Buffer.getLength(); i++) {
      // Sets the LED to green
      Led1Buffer.setRGB(i, 0,255,0);
    }
    }if(color=="purple"){
      for (int i = 0; i < Led1Buffer.getLength(); i++) {
      // Sets the LED to purple
      Led1Buffer.setRGB(i, 125,0,255);
    }
    }if(color=="alternate"){
      for (int x = 0; x < Led1Buffer.getLength(); x+=2) {
        // Sets the LED to blue for every 0,2,4, (even number)
        Led1Buffer.setRGB(x, 0,0,255);
      for (int y = 1; y < Led1Buffer.getLength(); y+=2) {
        // Sets the LED to red for every 1,3,5, (odd number)
        Led1Buffer.setRGB(y, 255,0,0 );
    }
    }if(color=="wave"){
      for (int x = 0; x < Led1Buffer.getLength(); x+=2) {
        // Sets the LED to blue starting from 1
        Led1Buffer.setRGB(x, 0,0,255);
      for (int y = 1; y < Led1Buffer.getLength(); y+=2) {
        // Sets the LED to red starting from 0 
        //The red trailing behind the blue gives the impression that the leds are moving like a wave
        Led1Buffer.setRGB(y, 255,0,0 );
    }
    }{
        System.out.println("Color is Invalid");
    }
  Led1.setData(Led1Buffer);
                }
            }
        }
    }
}
  
