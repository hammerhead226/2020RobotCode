/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

//import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
//import com.ctre.phoenix.motorcontrol.can.TalonFX;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
//import edu.wpi.first.wpilibj2.command.SubsystemBase;
//import frc.robot.Constants;
//import frc.robot.Robot;

public class Leds extends SubsystemBase{
  /**
   * A class for driving addressable LEDs.
   */
  //change to real port number
  AddressableLED Led1;
  //change length to real length
  AddressableLEDBuffer Led1Buffer;

  public Leds(){
  Led1  = new AddressableLED(5);
  Led1Buffer = new AddressableLEDBuffer(60);
  //change length to real length
  Led1.setLength(Led1Buffer.getLength());

  Led1.setData(Led1Buffer);
  Led1.start();

  }

  
  @Override
  public void periodic(){
    if(Robot.robotContainer.driver.getAButtonPressed(25)){
      for (int i = 0; i < Led1Buffer.getLength(); i++) {
        // Sets the LED to red
        Led1Buffer.setRGB(i, 255,0,0);
      }
    }if(Robot.robotContainer.driver.getBButtonPressed(25)){
      for (int i = 0; i < Led1Buffer.getLength(); i++) {
        // Sets the LED to blue
        Led1Buffer.setRGB(i, 0,0,255);
      }
    }if(Robot.robotContainer.driver.getXButtonPressed(25)){
      for (int i = 0; i < Led1Buffer.getLength(); i++) {
        // Sets the LED to green
        Led1Buffer.setRGB(i, 0,255,0);
      }
    }if(Robot.robotContainer.driver.getYButtonPressed(25)){
      for (int i = 0; i < Led1Buffer.getLength(); i++) {
        // Sets the LED to purple
        Led1Buffer.setRGB(i, 125,0,255);
      }
      }if(Robot.robotContainer.driver.getLBButtonPressed(25)){
      for (int x = 0; x < Led1Buffer.getLength(); x+=2) {
        // Sets the LED by number for every 0,2,4, (even number index)
        Led1Buffer.setRGB(x, 0,0,255);
      }
      for (int y = 1; y < Led1Buffer.getLength(); y+=2) {
        // Sets the LED by number for every 1,3,5, (odd number index)
        Led1Buffer.setRGB(y, 255,0,0 );
      }
      }if(Robot.robotContainer.driver.getRBButtonPressed(25)){
        for (int x = 0; x < Led1Buffer.getLength(); x+=2) {
          // Sets the LED by number starting from 1
          Led1Buffer.setRGB(x, 0,0,255);
        }
        for (int y = 1; y < Led1Buffer.getLength(); y+=2) {
          // Sets the LED by number starting from 0 
          //The red trailing behind the blue gives the impression that the leds are moving like a wave
          Led1Buffer.setRGB(y, 255,0,0 );    
        }
      }else{
        for (int i = 0; i < Led1Buffer.getLength(); i++) {
    //      Sets the LED to yellow
         Led1Buffer.setRGB(i, 255,255,0);
         System.out.println("yellow");
       }
      }

      Led1.setData(Led1Buffer);

  
}
//rgb comments
//red= (255,0,0)
//green= (0,255,0)
//blue= (0,0,255)
//purple= (125,0,255)
//yellow= (255,255,0)
//light blue= (0,255,255)
//pink= (255,0,255)
//orange= (255,125,0)
}
