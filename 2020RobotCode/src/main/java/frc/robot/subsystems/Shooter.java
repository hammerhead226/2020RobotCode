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
import frc.robot.RobotMap;

public class Shooter extends SubsystemBase {
  /**
   * Creates a new Shooter.
   */
  private TalonFX shooter1 = new TalonFX(RobotMap.SHOOTER_1);
  private TalonFX shooter2 = new TalonFX(RobotMap.SHOOTER_2);
  private double lastVelocity = 0;
  private double trueVelocity = 0;
  public boolean isTrueVelocity = false;
  private double lastDifference = 0;
  private double lastlastDifference = 0;
  private double lastlastlastDifference = 0;

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
  }

  public void setShooterSpeed(double velocity){
     //velocity = velocity;
    //shooter1.set(ControlMode.Velocity, velocity);

    // if(Utility.convertVelocitytoRPM(shooter1.getSelectedSensorVelocity())*1.33333 <= velocity - 250) {
    //   lastVelocity += 0.004;
    // } else if (Utility.convertVelocitytoRPM(shooter1.getSelectedSensorVelocity()) * 1.333 >= velocity + 250){
    //   lastVelocity -= 0.004;
    // } else if(Utility.convertVelocitytoRPM(shooter1.getSelectedSensorVelocity())*1.33333 <= velocity) {
    //   lastVelocity += 0.001;
    // } else {
    //   lastVelocity -= 0.001;
    // }

    double difference = velocity - Utility.convertVelocitytoRPM(shooter1.getSelectedSensorVelocity())*1.33333;
    difference = 1 / (1 + Math.pow(Math.E, -difference / 400));
    difference -= .5;
    System.out.println(difference);
    lastVelocity += difference / 600; 

    if(velocity > 1) {
      if(Math.abs(difference) < 0.01) {
        System.out.println("true");
        if(!isTrueVelocity) trueVelocity = lastVelocity;
        isTrueVelocity = true;
        
      }
      if(lastVelocity < (velocity / 10000)) {
        lastVelocity = (velocity / 10000);
        }
    } else {
      lastVelocity = 0;
    }

    
    SmartDashboard.putBoolean("true v", isTrueVelocity);
    SmartDashboard.putNumber("last v", isTrueVelocity ? trueVelocity : lastVelocity);
    if(isTrueVelocity) {
      runShooter(trueVelocity);
    } else {
      runShooter(lastVelocity);
    }

    lastDifference = difference;
    //lastlastDifference = lastDifference;
    //lastlastlastDifference = lastlastDifference;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
