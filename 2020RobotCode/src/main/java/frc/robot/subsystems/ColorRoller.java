/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ColorRoller extends SubsystemBase {
  /**
   * Creates a new ColorRoller.
   */
  TalonSRX color_Roller = new TalonSRX(0);
  
  ColorSensorV3 colorSensor = new ColorSensorV3(I2C.Port.kOnboard);

  public boolean completedRotation;
  
  public void rotationalControl(double rotations) {
    color_Roller.set(ControlMode.Position, rotations * Constants.TICKS_PER_REV_COLORWHEEL);
    completedRotation = true;
  }

  public ColorRoller() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
