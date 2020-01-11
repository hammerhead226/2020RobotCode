/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  /**
   * Creates a new Shooter.
   */
  private TalonFX shooter1 = new TalonFX(Constants.SHOOTER_1);
  private TalonFX shooter2 = new TalonFX(Constants.SHOOTER_2);

  public Shooter() {
    shooter1.setInverted(Constants.SHOOTER_1_INVERTED);
    shooter2.setInverted(Constants.SHOOTER_2_INVERTED);

    shooter2.follow(shooter1);
  }

  public void runShooter(int speed){
    shooter1.set(ControlMode.PercentOutput, speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
