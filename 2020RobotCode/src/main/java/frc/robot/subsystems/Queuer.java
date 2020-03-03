/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class Queuer extends SubsystemBase {
  /**
   * Creates a new Queuer.
   */
  private VictorSPX queuer = new VictorSPX(RobotMap.QUEUER);
  public DigitalInput beamBreaker = new DigitalInput(RobotMap.BEAM_BREAKER);

  public Queuer() {

    queuer.configOpenloopRamp(1.5);

  }

  public void runQueuer(double speed) {
    queuer.set(ControlMode.PercentOutput, -speed*0.8);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  //  if(beamBreaker.get() == false) {
      runQueuer(Robot.robotContainer.manip.getTriggers() < -.25 ? -0.8 : Robot.robotContainer.manip.getTriggers());
  //  }
  }
}
