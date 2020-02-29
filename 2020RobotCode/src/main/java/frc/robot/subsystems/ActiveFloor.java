/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class ActiveFloor extends SubsystemBase {
  /**
   * Creates a new ActiveFloor.
   */
  private VictorSPX activeFloor = new VictorSPX(RobotMap.ACTIVE_FLOOR_MOTOR);
  private double speed;
  private double startTime = Timer.getFPGATimestamp();
  private double endTime;
  private double seconds = 1.0;
  public ActiveFloor() {
    activeFloor.setInverted(Constants.ACTIVE_FLOOR_INVERTED);
    activeFloor.configVoltageCompSaturation(Constants.ACTIVE_FLOOR_VOLTAGE_LIMIT);
    activeFloor.enableVoltageCompensation(Constants.ACTIVE_FLOOR_VOLTAGE_ENABLE);
    activeFloor.setNeutralMode(NeutralMode.Brake);
  }

  public void runActiveFloor(double speed){
    activeFloor.set(ControlMode.PercentOutput, speed);
  }

  public void jogActiveFloorForward(){
    endTime = startTime + seconds;
    while (Timer.getFPGATimestamp() < endTime){
      activeFloor.set(ControlMode.PercentOutput, Constants.MAX_ACTIVE_FLOOR_SPEED);
    }
  }

  public void jogActiveFloorBackward(){
    endTime = startTime + seconds;
    while (Timer.getFPGATimestamp() < endTime){
      activeFloor.set(ControlMode.PercentOutput, -Constants.MAX_ACTIVE_FLOOR_SPEED);
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    speed();
  }

  private void speed() {
    runActiveFloor(Robot.robotContainer.manip.getTriggers());
  }
}
