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

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class Shooter extends SubsystemBase {
  /**
   * Creates a new Shooter.
   */
  private TalonFX shooter1 = new TalonFX(Constants.SHOOTER_1);
  private TalonFX shooter2 = new TalonFX(Constants.SHOOTER_2);

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

    shooter1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.PID_INDEX, Constants.PID_TIMEOUT);

    shooter2.follow(shooter1);
  }

  public void runShooter(int speed){
    shooter1.set(ControlMode.PercentOutput, speed);
  }

  public void Output(){
    SmartDashboard.putNumber("shooter1 current", shooter1.getStatorCurrent());
    SmartDashboard.putNumber("shooter2 current", shooter2.getStatorCurrent());
  }

  public void setShooterSpeed(double velocity){
    
    shooter1.set(ControlMode.Velocity, velocity);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
