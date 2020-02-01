/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

public class ActiveFloor extends SubsystemBase {
  /**
   * Creates a new ActiveFloor.
   */
  private TalonSRX activeFloor = new TalonSRX(Constants.ACTIVE_FLOOR_MOTOR);
  public ActiveFloor() {
    activeFloor.setInverted(Constants.ACTIVE_FLOOR_INVERTED);
    activeFloor.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(Constants.ACTIVE_FLOOR_CURRENT_ENABLE, Constants.ACTIVE_FLOOR_CURRENT_LIMIT, Constants.ACTIVE_FLOOR_CURRENT_LIMIT, Constants.ACTIVE_FLOOR_CURRENT_THRESHOLD_TIME));
    activeFloor.configVoltageCompSaturation(Constants.ACTIVE_FLOOR_VOLTAGE_LIMIT);
    activeFloor.enableVoltageCompensation(Constants.ACTIVE_FLOOR_VOLTAGE_ENABLE);
    activeFloor.setNeutralMode(NeutralMode.Brake);
  }

  public void activeFloor(double speed){
    activeFloor.set(ControlMode.PercentOutput, speed);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    

  }
}
