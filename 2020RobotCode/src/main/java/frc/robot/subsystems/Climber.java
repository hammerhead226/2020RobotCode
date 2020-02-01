/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class Climber extends SubsystemBase {
  /**
   * Creates a new Drivetrain.
   */
  private TalonFX climber = new TalonFX(Constants.CLIMBER);

  public Climber() {

  }

  public void climber(double climbSpeed) {
    climber.set(ControlMode.PercentOutput, climbSpeed);
  }

  public void Output(){
    SmartDashboard.putNumber("climber current", climber.getStatorCurrent());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    climber(Robot.robotContainer.driver.getTriggers());

  }
}
