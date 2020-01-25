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
      //options include "set,red,blue,green,purple,rainbow"

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
    }else{
        System.out.println("Color is Invalid");
    }
  Led1.setData(Led1Buffer);
  
  }
}
  }
}
  
