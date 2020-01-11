/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import java.sql.Driver;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.libs.swerve.SwerveControl;
import frc.libs.swerve.SwerveModule;

public class Drivetrain extends SubsystemBase {
  /**
   * Creates a new Drivetrain.
   */
  TalonFX frontLeftDrive = new TalonFX(Constants.FRONT_LEFT_DRIVE);
  TalonFX frontLeftSteer = new TalonFX(Constants.FRONT_LEFT_STEER);
  AnalogInput encoder1 = new AnalogInput(Constants.FRONT_LEFT_ENCODER);
  SwerveModule module1 = new SwerveModule(frontLeftDrive, frontLeftSteer, encoder1, 1);


  TalonFX rearLeftDrive = new TalonFX(Constants.REAR_LEFT_DRIVE);
  TalonFX rearLeftSteer = new TalonFX(Constants.REAR_LEFT_STEER);
  AnalogInput encoder2 = new AnalogInput(Constants.REAR_LEFT_ENCODER);
  SwerveModule module2 = new SwerveModule(rearLeftDrive, rearLeftSteer, encoder2, 1);
  

  TalonFX frontRightDrive = new TalonFX(Constants.FRONT_RIGHT_DRIVE);
  TalonFX frontRightSteer = new TalonFX(Constants.FRONT_RIGHT_STEER);
  AnalogInput encoder3 = new AnalogInput(Constants.FRONT_RIGHT_ENCODER);
  SwerveModule module3 = new SwerveModule(frontRightDrive, frontRightSteer, encoder3, 1);

  TalonFX rearRightDrive = new TalonFX(Constants.REAR_RIGHT_DRIVE);
  TalonFX rearRightSteer = new TalonFX(Constants.REAR_RIGHT_STEER);
  AnalogInput encoder4 = new AnalogInput(Constants.REAR_RIGHT_ENCODER);
  SwerveModule module4 = new SwerveModule(rearRightDrive, rearRightSteer, encoder4, 1);

  PigeonIMU pigeon = new PigeonIMU(Constants.PIGEON);

  SwerveControl swerve = new SwerveControl(module1, module2, module3, module4, pigeon);  

  public Drivetrain() {
    frontLeftSteer.setInverted(Constants.FRONT_LEFT_STEER_INVERTED);
    frontRightSteer.setInverted(Constants.FRONT_RIGHT_STEER_INVERTED);
    rearLeftSteer.setInverted(Constants.REAR_LEFT_STEER_INVERTED);
    rearRightSteer.setInverted(Constants.REAR_RIGHT_STEER_INVERTED);

    frontLeftDrive.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(Constants.CURRENT_ENABLE, Constants.CURRENT_LIMIT, Constants.CURRENT_LIMIT, Constants.CURRENT_TRESHOLD_TIME));
    frontLeftSteer.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(Constants.CURRENT_ENABLE, Constants.CURRENT_LIMIT, Constants.CURRENT_LIMIT, Constants.CURRENT_TRESHOLD_TIME));
    frontRightDrive.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(Constants.CURRENT_ENABLE, Constants.CURRENT_LIMIT, Constants.CURRENT_LIMIT, Constants.CURRENT_TRESHOLD_TIME));
    frontRightSteer.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(Constants.CURRENT_ENABLE, Constants.CURRENT_LIMIT, Constants.CURRENT_LIMIT, Constants.CURRENT_TRESHOLD_TIME));
    rearLeftDrive.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(Constants.CURRENT_ENABLE, Constants.CURRENT_LIMIT, Constants.CURRENT_LIMIT, Constants.CURRENT_TRESHOLD_TIME));
    rearLeftSteer.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(Constants.CURRENT_ENABLE, Constants.CURRENT_LIMIT, Constants.CURRENT_LIMIT, Constants.CURRENT_TRESHOLD_TIME));
    rearRightDrive.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(Constants.CURRENT_ENABLE, Constants.CURRENT_LIMIT, Constants.CURRENT_LIMIT, Constants.CURRENT_TRESHOLD_TIME));
    rearRightSteer.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(Constants.CURRENT_ENABLE, Constants.CURRENT_LIMIT, Constants.CURRENT_LIMIT, Constants.CURRENT_TRESHOLD_TIME));

    frontLeftDrive.configVoltageCompSaturation(Constants.VOLTAGE_LIMIT);
    frontLeftSteer.enableVoltageCompensation(Constants.VOLTAGE_ENABLE);
    frontRightDrive.configVoltageCompSaturation(Constants.VOLTAGE_LIMIT);
    frontRightSteer.enableVoltageCompensation(Constants.VOLTAGE_ENABLE);
    rearLeftDrive.configVoltageCompSaturation(Constants.VOLTAGE_LIMIT);
    rearLeftSteer.enableVoltageCompensation(Constants.VOLTAGE_ENABLE);
    rearRightDrive.configVoltageCompSaturation(Constants.VOLTAGE_LIMIT);
    rearRightSteer.enableVoltageCompensation(Constants.VOLTAGE_ENABLE);

    frontLeftDrive.setNeutralMode(NeutralMode.Brake);
    frontLeftSteer.setNeutralMode(NeutralMode.Brake);
    frontRightDrive.setNeutralMode(NeutralMode.Brake);
    frontRightSteer.setNeutralMode(NeutralMode.Brake);
    rearLeftDrive.setNeutralMode(NeutralMode.Brake);
    rearLeftSteer.setNeutralMode(NeutralMode.Brake);
    rearRightDrive.setNeutralMode(NeutralMode.Brake);
    rearRightSteer.setNeutralMode(NeutralMode.Brake);


  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    swerve.control(Robot.robotContainer.driver.getLeftJoystick_X(), Robot.robotContainer.driver.getLeftJoystick_Y(), Robot.robotContainer.driver.getRightJoystick_X());
  }
}
