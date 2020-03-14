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

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.libs.swerve.Utility;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotMap;
import frc.robot.Robot.State;

public class Shooter extends SubsystemBase {
  /**
   * Creates a new Shooter.
   */
  public TalonFX shooter1 = new TalonFX(RobotMap.SHOOTER_1);
  private TalonFX shooter2 = new TalonFX(RobotMap.SHOOTER_2);
  private double lastVelocity = 0;
  public boolean isTrueVelocity = false;
  private int lastPOV = -1;

  public Shooter() {
    shooter1.setNeutralMode(NeutralMode.Brake);
    shooter2.setNeutralMode(NeutralMode.Brake);

    shooter1.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(Constants.SHOOTER_1_CURRENT_ENABLE, Constants.SHOOTER_1_CURRENT_LIMIT,
            Constants.SHOOTER_1_CURRENT_THRESHOLD_LIMIT, Constants.SHOOTER_1_CURRENT_THRESHOLD_TIME));
    shooter2.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(Constants.SHOOTER_2_CURRENT_ENABLE, Constants.SHOOTER_2_CURRENT_LIMIT,
            Constants.SHOOTER_2_CURRENT_THRESHOLD_LIMIT, Constants.SHOOTER_2_CURRENT_THRESHOLD_TIME));
    
    shooter1.configVoltageCompSaturation(Constants.SHOOTER_1_VOLTAGE_LIMIT);
    shooter1.enableVoltageCompensation(Constants.SHOOTER_1_VOLTAGE_ENABLE);

    shooter2.configVoltageCompSaturation(Constants.SHOOTER_2_VOLTAGE_LIMIT);
    shooter2.enableVoltageCompensation(Constants.SHOOTER_2_VOLTAGE_ENABLE);

    shooter1.setInverted(Constants.SHOOTER_1_INVERTED);
    shooter2.setInverted(Constants.SHOOTER_2_INVERTED);

    shooter1.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor, Constants.PID_INDEX, Constants.PID_TIMEOUT);

    shooter2.follow(shooter1);
  }

  public void runShooter(double speed){
    shooter1.set(ControlMode.PercentOutput, speed);
  }

  public void output(){
    SmartDashboard.putNumber("shooter4 rpm", Utility.convertVelocitytoRPM(shooter1.getSelectedSensorVelocity())*1.33333);
    SmartDashboard.putNumber("shooter5 rpm", Utility.convertVelocitytoRPM(shooter2.getSelectedSensorVelocity())*1.33333);
    SmartDashboard.putNumber("nominal velocity", Constants.SHOOTER_MAX_RPM);
  }

  public void setShooterSpeed(double velocity){

    double difference = velocity - Utility.convertVelocitytoRPM(shooter1.getSelectedSensorVelocity())*1.33333;
    SmartDashboard.putNumber("differencebefore", difference);
    difference = (1 / (1 + Math.pow(Math.E, -difference / 500))) - 0.5;
    SmartDashboard.putNumber("differenceafter", difference);
    lastVelocity += difference / 130; 

    if(velocity != 0) {
      if(lastVelocity < (velocity / 8500)) {
        lastVelocity = (velocity / 8500);
      }
    } else {
      lastVelocity = 0;
    }
    runShooter(lastVelocity);
  }

  @Override
  public void periodic() {
    SmartDashboard.putNumber("shooter current", shooter1.getStatorCurrent());
      if(Robot.state == State.TELEOP) {
        if(lastPOV != Robot.robotContainer.manip.getPOV() && Robot.robotContainer.manip.getPOV() == 0) {
          Constants.SHOOTER_MAX_RPM += 100;
        }
    
        if(lastPOV != Robot.robotContainer.manip.getPOV() && Robot.robotContainer.manip.getPOV() == 180) {
          Constants.SHOOTER_MAX_RPM -= 100;
        }
    
        if(Robot.robotContainer.manip.getTriggers() > 0.1 && !Robot.robotContainer.manip.getAButtonPressed() && !Robot.robotContainer.manip.getBButtonPressed()) {
          shooter1.set(ControlMode.PercentOutput, -Robot.robotContainer.manip.getTriggers() / 3);
        } else if(!Robot.robotContainer.manip.getAButtonPressed() && !Robot.robotContainer.manip.getBButtonPressed()) {
          shooter1.set(ControlMode.PercentOutput, 0);
        }
        lastPOV = Robot.robotContainer.manip.getPOV();
      }
  }

  public double getVelocity() {

    SmartDashboard.putNumber("shooter4 rpm", Utility.convertVelocitytoRPM(shooter1.getSelectedSensorVelocity())*1.33333);
    SmartDashboard.putNumber("shooter5 rpm", Utility.convertVelocitytoRPM(shooter2.getSelectedSensorVelocity())*1.33333);

    return Utility.convertVelocitytoRPM(shooter1.getSelectedSensorVelocity())*1.33333; // 1.33 accounts for gear ratio from motor to flywheel
  }

  public void speed() {
    Constants.SHOOTER_MAX_RPM = 6000;
  }

  public void slow() {
    Constants.SHOOTER_MAX_RPM = 4400;
  }

}
