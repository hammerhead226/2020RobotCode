/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ShooterPID extends SubsystemBase {
  /**
   * Creates a new ShooterPID.
   */
  TalonSRX shooter = new TalonSRX(Constants.SHOOTER);

  public void ShooterSpeedPID(double velocity){
    shooter.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 10);
    shooter.set(ControlMode.Velocity, velocity);
  }
  public ShooterPID() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

  }
}
