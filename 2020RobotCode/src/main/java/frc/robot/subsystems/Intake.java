/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class Intake extends SubsystemBase {
  /**
   * Creates a new Intake.
   */

  private TalonSRX intake = new TalonSRX(Constants.INTAKE);

  public void intake(double intakeSpeed){
    intake.set(ControlMode.PercentOutput, intakeSpeed);
  }

  public Intake() {
    intake.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(Constants.INTAKE_CURRENT_ENABLE, Constants.INTAKE_CURRENT_LIMIT, Constants.INTAKE_CURRENT_THRESHOLD_LIMIT, Constants.INTAKE_CURRENT_THRESHOLD_TIME));
    intake.configVoltageCompSaturation(Constants.INTAKE_VOLTAGE_LIMIT);
    intake.enableVoltageCompensation(Constants.INTAKE_VOLTAGE_ENABLE);
    intake.setInverted(Constants.INTAKE_INVERTED);
    intake.setNeutralMode(NeutralMode.Brake);
  }

  public void Output() {
    SmartDashboard.putNumber("intake current", intake.getStatorCurrent());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //climber(Robot.robotContainer.getManipLeftTrigger()+Robot.robotContainer.getManipRightTrigger());
    intake(Robot.robotContainer.manip.getTriggers());
  }
}
