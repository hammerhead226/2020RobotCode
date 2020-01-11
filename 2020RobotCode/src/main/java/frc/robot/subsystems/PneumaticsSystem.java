/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class PneumaticsSystem extends SubsystemBase {
  /**
   * Creates a new Pneumatics.
   */
  private Compressor compressor = new Compressor(Constants.COMPRESSOR);
  private DoubleSolenoid Intake = new DoubleSolenoid(Constants.INTAKE_SHIFT_1, Constants.INTAKE_SHIFT_2);
  private DoubleSolenoid Shooter = new DoubleSolenoid(Constants.SHOOTER_SHIFT_1, Constants.SHOOTER_SHIFT_2);
  private DoubleSolenoid.Value intakeVal = DoubleSolenoid.Value.kForward;
  private DoubleSolenoid.Value shooterVal = DoubleSolenoid.Value.kForward;

  public PneumaticsSystem() {
    compressor.start();
  }

  public void toggleCompressor(){
    compressor.stop();
  }

  public void toggleIntake(){
    if (intakeVal == DoubleSolenoid.Value.kForward){
      intakeVal = DoubleSolenoid.Value.kReverse;
    }else {
      intakeVal = DoubleSolenoid.Value.kForward;
    }
    Intake.set(intakeVal);
  }

  public void toggleShooter(){
    if (shooterVal == DoubleSolenoid.Value.kForward){
      shooterVal = DoubleSolenoid.Value.kReverse;
    }else {
      intakeVal = DoubleSolenoid.Value.kForward;
    }
    Shooter.set(shooterVal);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
