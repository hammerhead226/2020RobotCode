/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class PneumaticsSystem extends SubsystemBase {
  /**
   * Creates a new Pneumatics.
   */
  private Compressor compressor = new Compressor(RobotMap.COMPRESSOR);
  private DoubleSolenoid intake = new DoubleSolenoid(RobotMap.INTAKE_SHIFT_1, RobotMap.INTAKE_SHIFT_2);
  private Solenoid shooter = new Solenoid(RobotMap.SHOOTER_SHIFT);
  private DoubleSolenoid climber = new DoubleSolenoid(RobotMap.CLIMBER_SHIFT_1, RobotMap.CLIMBER_SHIFT_2);
  private DoubleSolenoid shooterBrake = new DoubleSolenoid(RobotMap.SHOOTER_BRAKE_SHIFT_1, RobotMap.SHOOTER_BRAKE_SHIFT_2);

  private DoubleSolenoid.Value intakeVal = DoubleSolenoid.Value.kForward;
  private boolean shooterVal = true;
  private DoubleSolenoid.Value climberVal = DoubleSolenoid.Value.kForward;
  private DoubleSolenoid.Value shooterBrakeVal = DoubleSolenoid.Value.kReverse;
  
  public PneumaticsSystem() {
    compressor.start();
  }

  public void toggleCompressor(){
    if(compressor.enabled()){
      compressor.stop();
    }else{
      compressor.start();
    }
  }

  public void toggleIntake(){
    if (intakeVal == DoubleSolenoid.Value.kForward){
      intakeVal = DoubleSolenoid.Value.kReverse;
    }else {
      intakeVal = DoubleSolenoid.Value.kForward;
    }
    intake.set(intakeVal);
  }
  
  public void toggleShooter(){
    shooterVal = !(shooterVal);
    shooter.set(shooterVal);
  }

  public void toggleShooterBrake(){
    if (shooterBrakeVal == DoubleSolenoid.Value.kForward){
      shooterBrakeVal = DoubleSolenoid.Value.kReverse;
    }else {
      shooterBrakeVal = DoubleSolenoid.Value.kForward;
    }
    shooterBrake.set(shooterBrakeVal);
  }

  public void shooterBrakeOut(){
    shooterBrakeVal = DoubleSolenoid.Value.kForward;
    shooterBrake.set(shooterBrakeVal);
  }

  public void shooterBrakeIn(){
    shooterBrakeVal = DoubleSolenoid.Value.kReverse;
    shooterBrake.set(shooterBrakeVal);
  }

  public void shooterUp() {
    shooterVal = false;
    shooter.set(shooterVal);
  }

  public void shooterDown() {
    shooterVal = true;
    shooter.set(shooterVal);
  }

  public void toggleClimber() {
    if (climberVal == DoubleSolenoid.Value.kForward){
      climberVal = DoubleSolenoid.Value.kReverse;
    }
    else {
      climberVal = DoubleSolenoid.Value.kForward;
    }
    climber.set(climberVal);
  }
  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
 }
}
