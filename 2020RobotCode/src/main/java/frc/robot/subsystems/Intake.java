/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

public class Intake extends SubsystemBase {
  /**
   * Creates a new Intake.
   */
  TalonFX intake_motor = new TalonFX(Constants.INTAKE_MOTOR);

  public void climber(double intakeSpeed){
    intake_motor.set(TalonFXControlMode.PercentOutput, intakeSpeed);
  }
  public Intake() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    climber(Robot.robotContainer.getManipLeftTrigger()+Robot.robotContainer.getManipRightTrigger());
  }
}
