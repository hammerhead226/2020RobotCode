/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Shooter extends SubsystemBase {
  /**
   * Creates a new Shooter.
   */
  private TalonFX shooter1 = new TalonFX(Constants.SHOOTER_1);
  private TalonFX shooter2 = new TalonFX(Constants.SHOOTER_2);

  

  public Shooter() {
    shooter1.setInverted(Constants.SHOOTER_1_INVERTED);
    shooter2.setInverted(Constants.SHOOTER_2_INVERTED);
    shooter2.follow(shooter1);
    shooter1.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, Constants.SHOOTER_ENCODER_PIDIDX, Constants.SHOOTER_ENCODER_TIMEOUT);
  }

  public void runShooter(int speed){
    double sensitivity = Constants.SHOOTER_SENSITIVITY;
    double tolerance = Constants.SHOOTER_TOLERANCE;

    while(shooter1.getSelectedSensorVelocity(0) != speed){
        if (shooter1.getSelectedSensorVelocity(0) > (speed + tolerance)){
          shooter1.set(ControlMode.PercentOutput, speed-sensitivity);
        }
        else if (shooter1.getSelectedSensorVelocity(0) < (speed - tolerance)){
          shooter1.set(ControlMode.PercentOutput, speed+sensitivity);
        }
    }
  }

  public void Output(){
    SmartDashboard.putNumber("shooter1 current", shooter1.getStatorCurrent());
    SmartDashboard.putNumber("shooter2 current", shooter2.getStatorCurrent());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
