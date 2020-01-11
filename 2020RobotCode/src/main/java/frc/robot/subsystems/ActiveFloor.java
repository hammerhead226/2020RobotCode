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

public class ActiveFloor extends SubsystemBase {
  /**
   * Creates a new MovingFloor.
   */
  private TalonFX roller = new TalonFX(Constants.ROLLER);
  private TalonFX activeFloor = new TalonFX(Constants.ACTIVE_FLOOR);

  public ActiveFloor() {

  }

  public void RunRollers(int speed){
    roller.set(ControlMode.PercentOutput, speed);
    activeFloor.set(ControlMode.PercentOutput, speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
