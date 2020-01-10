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
  private TalonFX shooterMotor1 = new TalonFX(Constants.SHOOTER_MOTOR_1);
  private TalonFX shooterMotor2 = new TalonFX(Constants.SHOOTER_MOTOR_2);

  public Shooter() {
    shooterMotor1.setInverted(true);
    shooterMotor2.setInverted(!true);

    shooterMotor2.follow(shooterMotor1);
  }

  public void shotsfired(int speed){
    shooterMotor1.set(ControlMode.PercentOutput, speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
