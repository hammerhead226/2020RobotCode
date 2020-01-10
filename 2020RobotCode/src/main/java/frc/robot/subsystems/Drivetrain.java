/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.swerve.SwerveModule;

public class Drivetrain extends SubsystemBase {
  /**
   * Creates a new Drivetrain.
   */
  public Drivetrain() {
    TalonFX frontLeftDrive = new TalonFX(Constants.FRONT_LEFT_DRIVE);
    TalonFX frontLeftSteer = new TalonFX(Constants.FRONT_LEFT_STEER);
    AnalogInput encoder1;
    SwerveModule module1;

    TalonFX backLeftDrive = new TalonFX(Constants.REAR_LEFT_DRIVE);
    TalonFX backLeftSteer = new TalonFX(Constants.REAR_LEFT_STEER);
    AnalogInput encoder2;
    SwerveModule module2;

    TalonFX frontRightDrive = new TalonFX(Constants.FRONT_RIGHT_DRIVE);
    TalonFX frontRightSteer = new TalonFX(Constants.FRONT_RIGHT_STEER);
    AnalogInput encoder3;
    SwerveModule module3;

    TalonFX backRightDrive = new TalonFX(Constants.REAR_RIGHT_DRIVE);
    TalonFX backRightSteer = new TalonFX(Constants.REAR_RIGHT_STEER);
    AnalogInput encoder4;
    SwerveModule module4;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
