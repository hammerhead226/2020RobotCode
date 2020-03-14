/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class Intake extends SubsystemBase {
  /**
   * Creates a new Intake.
   */

  private VictorSPX intake = new VictorSPX(RobotMap.INTAKE);

  public void intake(double intakeSpeed){
    intake.set(ControlMode.PercentOutput, 0.8*(intakeSpeed));
  }

  public Intake() {
    intake.configVoltageCompSaturation(Constants.INTAKE_VOLTAGE_LIMIT);
    intake.enableVoltageCompensation(Constants.INTAKE_VOLTAGE_ENABLE);
    intake.setInverted(Constants.INTAKE_INVERTED);
    intake.setNeutralMode(NeutralMode.Brake);
  }

  public void output() {
    SmartDashboard.putNumber("intake queuer", intake.getBusVoltage());
  }

  @Override
  public void periodic() {
    if(Robot.state == Robot.State.TELEOP) intake(Robot.robotContainer.driver.getTriggers());
  }
}
