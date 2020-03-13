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
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.geometry.Translation2d;
import edu.wpi.first.wpilibj.kinematics.SwerveDriveKinematics;
import edu.wpi.first.wpilibj.kinematics.SwerveDriveOdometry;
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

  private SwerveControl swerve = new SwerveControl(module2, module1, module4, module3, pigeon);

  Translation2d m_frontLeftLocation = new Translation2d(0.381, 0.381);
  Translation2d m_frontRightLocation = new Translation2d(0.381, -0.381);
  Translation2d m_backLeftLocation = new Translation2d(-0.381, 0.381);
  Translation2d m_backRightLocation = new Translation2d(-0.381, -0.381);

  SwerveDriveKinematics kinematics = new SwerveDriveKinematics(m_frontLeftLocation, m_frontRightLocation,
      m_backLeftLocation, m_backRightLocation);

  SwerveDriveOdometry odometry = new SwerveDriveOdometry(kinematics, new Rotation2d(),
      new Pose2d(0, 0, new Rotation2d()));

  public Pose2d currentPose2d = new Pose2d();
  public boolean isLocked = false;

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

    pigeon.setYaw(0);
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

  public void control(double x, double y, double rot) {
    swerve.control(x, y, rot);
  }

  public void control(Trajectory.State state){
    swerve.control(state);
  }
  
  public void zeroGyro() {
    pigeon.setYaw(0);
    control(0,0,0.01);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    // currentPose2d = odometry.update(new Rotation2d(swerve.getGyro()), module1.getState(), module2.getState(),
    //     module3.getState(), module4.getState());
    double adjustment;
    if(Robot.robotContainer.driver.getLBButtonPressed()) {
      adjustment = .2;
    } else {
      adjustment = .7;
    }
    if(Robot.state == Robot.State.TELEOP) {control(
        (0.8*(Math.copySign(Math.pow(Robot.robotContainer.driver.getLeftJoystick_X(), 2),
            Robot.robotContainer.driver.getLeftJoystick_X()))),
        (0.8*(-Math.copySign(Math.pow(Robot.robotContainer.driver.getLeftJoystick_Y(), 2),
            Robot.robotContainer.driver.getLeftJoystick_Y()))),
        (adjustment*(-Math.copySign(Math.pow(Robot.robotContainer.driver.getRightJoystick_X(), 2),
            Robot.robotContainer.driver.getRightJoystick_X()))));
    }
  }

  public double getGyro() {
    double[] ypr = new double[3];
    pigeon.getYawPitchRoll(ypr);
    double gyro = ypr[0];

    return gyro;
  }
}
