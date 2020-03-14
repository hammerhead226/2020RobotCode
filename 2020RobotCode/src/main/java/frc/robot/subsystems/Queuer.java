/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class Queuer extends SubsystemBase {
  /**
   * Creates a new Queuer.
   */
  public VictorSPX queuer = new VictorSPX(RobotMap.QUEUER);

  public Queuer() {
    queuer.setInverted(Constants.QUEUER_INVERTED);
    queuer.configOpenloopRamp(Constants.QUEUER_RAMP_RATE);
  }

  public void runQueuer(double speed) {
    queuer.set(ControlMode.PercentOutput, speed);
  }

  public void output(){}

  @Override
  public void periodic() {
    if (Robot.state == Robot.State.TELEOP)
      runQueuer(Robot.robotContainer.manip.getTriggers() < -.25 ? -0.8 : Robot.robotContainer.manip.getTriggers());

  }
}
