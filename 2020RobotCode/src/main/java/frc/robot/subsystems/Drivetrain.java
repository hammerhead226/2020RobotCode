/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.libs.swerve.SwerveControl;
import frc.libs.swerve.SwerveModule;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class Drivetrain extends SubsystemBase {
  /**
   * Creates a new Drivetrain.
   */
  private TalonFX frontLeftDrive = new TalonFX(RobotMap.FRONT_LEFT_DRIVE);
  private VictorSPX frontLeftSteer = new VictorSPX(RobotMap.FRONT_LEFT_STEER);
  private AnalogInput encoder1 = new AnalogInput(RobotMap.FRONT_LEFT_ENCODER);
  private SwerveModule module1 = new SwerveModule(frontLeftDrive, frontLeftSteer, encoder1, 1);

  private TalonFX rearLeftDrive = new TalonFX(RobotMap.REAR_LEFT_DRIVE);
  private VictorSPX rearLeftSteer = new VictorSPX(RobotMap.REAR_LEFT_STEER);
  private AnalogInput encoder2 = new AnalogInput(RobotMap.REAR_LEFT_ENCODER);
  private SwerveModule module2 = new SwerveModule(rearLeftDrive, rearLeftSteer, encoder2, 1);
  

  private TalonFX frontRightDrive = new TalonFX(RobotMap.FRONT_RIGHT_DRIVE);
  private VictorSPX frontRightSteer = new VictorSPX(RobotMap.FRONT_RIGHT_STEER);
  private AnalogInput encoder3 = new AnalogInput(RobotMap.FRONT_RIGHT_ENCODER);
  private SwerveModule module3 = new SwerveModule(frontRightDrive, frontRightSteer, encoder3, 1);

  private TalonFX rearRightDrive = new TalonFX(RobotMap.REAR_RIGHT_DRIVE);
  private VictorSPX rearRightSteer = new VictorSPX(RobotMap.REAR_RIGHT_STEER);
  private AnalogInput encoder4 = new AnalogInput(RobotMap.REAR_RIGHT_ENCODER);
  private SwerveModule module4 = new SwerveModule(rearRightDrive, rearRightSteer, encoder4, 1);

  private PigeonIMU pigeon = new PigeonIMU(RobotMap.PIGEON);

  private SwerveControl swerve = new SwerveControl(module1, module2, module3, module4, pigeon);

  public Drivetrain() {
    frontLeftSteer.setInverted(Constants.FRONT_LEFT_STEER_INVERTED);
    frontRightSteer.setInverted(Constants.FRONT_RIGHT_STEER_INVERTED);
    rearLeftSteer.setInverted(Constants.REAR_LEFT_STEER_INVERTED);
    rearRightSteer.setInverted(Constants.REAR_RIGHT_STEER_INVERTED);

    frontLeftDrive.configSupplyCurrentLimit(
        new SupplyCurrentLimitConfiguration(Constants.DRIVETRAIN_CURRENT_ENABLE, Constants.DRIVETRAIN_CURRENT_LIMIT,
            Constants.DRIVETRAIN_CURRENT_THRESHOLD_LIMIT, Constants.DRIVETRAIN_CURRENT_THRESHOLD_TIME));
    frontRightDrive.configSupplyCurrentLimit(
        new SupplyCurrentLimitConfiguration(Constants.DRIVETRAIN_CURRENT_ENABLE, Constants.DRIVETRAIN_CURRENT_LIMIT,
            Constants.DRIVETRAIN_CURRENT_THRESHOLD_LIMIT, Constants.DRIVETRAIN_CURRENT_THRESHOLD_TIME));
    rearLeftDrive.configSupplyCurrentLimit(
        new SupplyCurrentLimitConfiguration(Constants.DRIVETRAIN_CURRENT_ENABLE, Constants.DRIVETRAIN_CURRENT_LIMIT,
            Constants.DRIVETRAIN_CURRENT_THRESHOLD_LIMIT, Constants.DRIVETRAIN_CURRENT_THRESHOLD_TIME));
    rearRightDrive.configSupplyCurrentLimit(
        new SupplyCurrentLimitConfiguration(Constants.DRIVETRAIN_CURRENT_ENABLE, Constants.DRIVETRAIN_CURRENT_LIMIT,
            Constants.DRIVETRAIN_CURRENT_THRESHOLD_LIMIT, Constants.DRIVETRAIN_CURRENT_THRESHOLD_TIME));

    frontLeftDrive.configVoltageCompSaturation(Constants.DRIVETRAIN_VOLTAGE_LIMIT);
    frontLeftSteer.enableVoltageCompensation(Constants.DRIVETRAIN_VOLTAGE_ENABLE);
    frontRightDrive.configVoltageCompSaturation(Constants.DRIVETRAIN_VOLTAGE_LIMIT);
    frontRightSteer.enableVoltageCompensation(Constants.DRIVETRAIN_VOLTAGE_ENABLE);
    rearLeftDrive.configVoltageCompSaturation(Constants.DRIVETRAIN_VOLTAGE_LIMIT);
    rearLeftSteer.enableVoltageCompensation(Constants.DRIVETRAIN_VOLTAGE_ENABLE);
    rearRightDrive.configVoltageCompSaturation(Constants.DRIVETRAIN_VOLTAGE_LIMIT);
    rearRightSteer.enableVoltageCompensation(Constants.DRIVETRAIN_VOLTAGE_ENABLE);

    frontLeftDrive.setNeutralMode(NeutralMode.Brake);
    frontLeftSteer.setNeutralMode(NeutralMode.Brake);
    frontRightDrive.setNeutralMode(NeutralMode.Brake);
    frontRightSteer.setNeutralMode(NeutralMode.Brake);
    rearLeftDrive.setNeutralMode(NeutralMode.Brake);
    rearLeftSteer.setNeutralMode(NeutralMode.Brake);
    rearRightDrive.setNeutralMode(NeutralMode.Brake);
    rearRightSteer.setNeutralMode(NeutralMode.Brake);

    frontLeftDrive.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    frontRightDrive.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    rearLeftDrive.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    rearRightDrive.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
  }

  public void brake() {
    int fLSensPosD = frontLeftDrive.getSelectedSensorPosition();
    int fRSensPosD = frontLeftDrive.getSelectedSensorPosition();
    int rLSensPosD = frontLeftDrive.getSelectedSensorPosition();
    int rRSensPosD = frontLeftDrive.getSelectedSensorPosition();
    int fLSensPosS = module1.getAngle();
    int fRSensPosS = module3.getAngle();
    int rLSensPosS = module2.getAngle();
    int rRSensPosS = module4.getAngle();

    frontLeftDrive.setSelectedSensorPosition(fLSensPosD);
    frontRightDrive.setSelectedSensorPosition(fRSensPosD);
    rearLeftDrive.setSelectedSensorPosition(rLSensPosD);
    rearRightDrive.setSelectedSensorPosition(rRSensPosD);


    frontLeftSteer.set(ControlMode.PercentOutput, (fLSensPosS - module1.getAngle()) * Constants.STEER_KP);
    rearLeftSteer.set(ControlMode.PercentOutput, (rLSensPosS - module2.getAngle()) * Constants.STEER_KP);
    frontRightSteer.set(ControlMode.PercentOutput, (fRSensPosS - module3.getAngle()) * Constants.STEER_KP);
    rearRightSteer.set(ControlMode.PercentOutput, (rRSensPosS - module4.getAngle()) * Constants.STEER_KP);
    // target - acutal multiplied by the steer kp
    pigeon.setYaw(0);
  }

  public void Output() {
    SmartDashboard.putNumber("frontLeftDrive current", frontLeftDrive.getStatorCurrent());
    SmartDashboard.putNumber("frontRightDrive current", frontRightDrive.getStatorCurrent());
    SmartDashboard.putNumber("rearLeftDrive current", rearLeftDrive.getStatorCurrent());
    SmartDashboard.putNumber("rearRightDrive current", rearRightDrive.getStatorCurrent());
  }

  public void zeroGyro() {
    pigeon.setYaw(0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    swerve.control(
        (0.8*(Math.copySign(Math.pow(Robot.robotContainer.driver.getLeftJoystick_X(), 2),
            Robot.robotContainer.driver.getLeftJoystick_X()))),
        (0.8*(Math.copySign(Math.pow(Robot.robotContainer.driver.getLeftJoystick_Y(), 2),
            Robot.robotContainer.driver.getLeftJoystick_Y()))),
        (0.8*(Math.copySign(Math.pow(Robot.robotContainer.driver.getRightJoystick_X(), 2),
            Robot.robotContainer.driver.getRightJoystick_X()))));
  }
}
