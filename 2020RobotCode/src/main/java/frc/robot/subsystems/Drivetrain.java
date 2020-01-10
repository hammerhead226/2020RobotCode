/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.libs.swerve.SwerveControl;
import frc.libs.swerve.SwerveModule;

public class Drivetrain extends SubsystemBase {
  /**
   * Creates a new Drivetrain.
   */
  public Drivetrain() {
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

    SwerveControl control = new SwerveControl(module1, module2, module3, module4, pigeon);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
