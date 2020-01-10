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
import frc.robot.RobotContainer;

import com.ctre.phoenix.motorcontrol.can.TalonFX;

public class Climber extends SubsystemBase {
  /**
   * Creates a new Drivetrain.
   */
  TalonFX climberMotor1 = new TalonFX(Constants.CLIMBER_MOTOR_1);


  public void climber(double climbSpeed){
    
    climberMotor1.set(ControlMode.PercentOutput, climbSpeed);
  }
  public Climber() {

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    climber(Robot.robotContainer.getLeftTrigger());

  }
}
