/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.AddressableLED;

public class Leds extends Object implements AutoCloseable {
  /**
   * A class for driving addressable LEDs.
   */

  //change to real port number
  Led1 = new AddressableLED(5);


  Led1Buffer = new AddressableLEDBuffer(500);
  //change length to real length
  Led1.setLength(500);

  // Set the data
  Led1.setData(Led1Buffer);
  Led1.start();
  }{

  for (i = 0; i < Led1Buffer.getLength(); i++) {
  // Sets the specified LED to the RGB values for red
  Led1Buffer.setRGB(i, 0,255,255);
  }

  Led1.setData(Led1Buffer);

  
  }
}
