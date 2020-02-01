/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;

public class Intake extends SubsystemBase {
  /**
   * Creates a new Intake.
   */
  private TalonSRX intake = new TalonSRX(Constants.INTAKE);

  public void intake(double intakeSpeed){
    intake.set(ControlMode.PercentOutput, intakeSpeed);
  }
  public Intake() {

  }

  public void Output(){
    SmartDashboard.putNumber("intake current", intake.getStatorCurrent());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    //climber(Robot.robotContainer.getManipLeftTrigger()+Robot.robotContainer.getManipRightTrigger());
    intake(Robot.robotContainer.manip.getTriggers());
  }
}
