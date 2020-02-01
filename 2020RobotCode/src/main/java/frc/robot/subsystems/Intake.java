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

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class Intake extends SubsystemBase {
  /**
   * Creates a new Intake.
   */
  TalonFX intakeActiveFloor = new TalonFX(Constants.INTAKE);

  public void intake(double intakeSpeed){
    intakeActiveFloor.set(TalonFXControlMode.PercentOutput, intakeSpeed);
  }

  public Intake() {
    intakeActiveFloor.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(Constants.INTAKE_CURRENT_ENABLE, Constants.INTAKE_CURRENT_LIMIT, Constants.INTAKE_CURRENT_THRESHOLD_LIMIT, Constants.INTAKE_CURRENT_THRESHOLD_TIME));
    intakeActiveFloor.configVoltageCompSaturation(Constants.INTAKE_VOLTAGE_LIMIT);
    intakeActiveFloor.enableVoltageCompensation(Constants.INTAKE_VOLTAGE_ENABLE);
    intakeActiveFloor.setInverted(Constants.ACTIVEFLOOR_INVERTED);
    intakeActiveFloor.setNeutralMode(NeutralMode.Brake);
  }


  public void Output() {
    SmartDashboard.putNumber("intake current", intakeActiveFloor.getStatorCurrent());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //climber(Robot.robotContainer.getManipLeftTrigger()+Robot.robotContainer.getManipRightTrigger());
    intake(Robot.robotContainer.manip.getTriggers());
  }
}
