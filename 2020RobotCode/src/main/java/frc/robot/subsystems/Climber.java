/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.SupplyCurrentLimitConfiguration;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import com.revrobotics.Rev2mDistanceSensor;
import com.revrobotics.Rev2mDistanceSensor.Port;
import com.revrobotics.Rev2mDistanceSensor.Unit;

import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class Climber extends SubsystemBase {
  /**
   * Creates a new Drivetrain.
   */
  public TalonFX climber = new TalonFX(RobotMap.CLIMBER);
  private Rev2mDistanceSensor distSensor = new Rev2mDistanceSensor(Port.kOnboard);

  public Climber() {
    climber.configSupplyCurrentLimit(new SupplyCurrentLimitConfiguration(Constants.CLIMBER_CURRENT_ENABLE, Constants.CLIMBER_CURRENT_LIMIT, Constants.CLIMBER_CURRENT_THRESHOLD_LIMIT, Constants.CLIMBER_CURRENT_THRESHOLD_TIME));

    climber.configVoltageCompSaturation(Constants.CLIMBER_VOLTAGE_LIMIT);
    climber.enableVoltageCompensation(Constants.CLIMBER_VOLTAGE_ENABLE);

    climber.setInverted(Constants.CLIMBER_INVERTED);
    
    climber.setNeutralMode(NeutralMode.Brake);

    distSensor.setEnabled(true);
    distSensor.setAutomaticMode(true);
    distSensor.setDistanceUnits(Unit.kMillimeters);
  }

  public void climber(double climbSpeed) {
    if (Robot.robotContainer.manip.getLeftJoystick_Y() <= -.25) {
      climber.set(ControlMode.PercentOutput, -0.6);
    } else if (Robot.robotContainer.manip.getLeftJoystick_Y() >= -.25 && Robot.robotContainer.manip.getLeftJoystick_Y() <= 0){ 
      climber.set(ControlMode.PercentOutput, 0);
    } else {
      climber.set(ControlMode.PercentOutput, climbSpeed);
    }
  }

  public void zeroClimber() {
    climber.set(ControlMode.Position, 0);
  }

  public void disableDistSensor() {
    distSensor.setEnabled(false);
    distSensor.setAutomaticMode(false);
  }

  public void wiggleClimber() {
    if(Robot.pneumatics.getClimberState() == Value.kForward) {
      climber.set(ControlMode.PercentOutput, .2);
      System.out.println("out");
    } else {
      climber.set(ControlMode.PercentOutput, -.2);
      System.out.println("in");
    }
  }

  public void stopClimber() {
    climber.set(ControlMode.PercentOutput, 0);
  }

  public void output(){
    SmartDashboard.putNumber("climber current", climber.getStatorCurrent());
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    double rawValue = Robot.robotContainer.manip.getLeftJoystick_Y();
    if(rawValue <= -0.25) {
      climber(-0.6);
    } else if(rawValue > -0.25 && rawValue <= 0) {
      climber(0);
    } else {
      climber(rawValue);
    }

    output();
    // if(distSensor.getRange() <= Constants.DISTANCE_SENSOR_MIN && distSensor.isRangeValid()) {
    //   Robot.robotContainer.manip.setRumble(RumbleType.kLeftRumble, Constants.MANIP_RUMBLE_ON);
    //   Robot.robotContainer.manip.setRumble(RumbleType.kRightRumble, Constants.MANIP_RUMBLE_ON);
    // }
    // else {
    //   Robot.robotContainer.manip.setRumble(RumbleType.kLeftRumble, Constants.MANIP_RUMBLE_OFF);
    //   Robot.robotContainer.manip.setRumble(RumbleType.kRightRumble, Constants.MANIP_RUMBLE_OFF);
    //   climber(Robot.robotContainer.manip.getTriggers());
    // }
  }
}
