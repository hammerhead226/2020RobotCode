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

public class Pneumatics extends SubsystemBase {
  /**
   * Creates a new Pneumatics.
   */
  private Compressor compressor = new Compressor(Constants.COMPRESSOR);
  private DoubleSolenoid Intake = new DoubleSolenoid(Constants.INTAKE_TOGGLE_SHIFT_1, Constants.INTAKE_TOGGLE_SHIFT_2);
  private DoubleSolenoid Hood = new DoubleSolenoid(Constants.HOOD_TOGGLE_SHIFT_1, Constants.HOOD_TOGGLE_SHIFT_2);
  private DoubleSolenoid.Value intakeVal = DoubleSolenoid.Value.kForward;
  private DoubleSolenoid.Value hoodVal = DoubleSolenoid.Value.kForward;

  public Pneumatics() {
    compressor.start();
  }

  public void compressorToggle(){
    compressor.stop();
  }

  public void intakeToggle(){
    if (intakeVal == DoubleSolenoid.Value.kForward){
      intakeVal = DoubleSolenoid.Value.kReverse;
    }else {
      intakeVal = DoubleSolenoid.Value.kForward;
    }
    Intake.set(intakeVal);
  }

  public void hoodToggle(){
    if (hoodVal == DoubleSolenoid.Value.kForward){
      hoodVal = DoubleSolenoid.Value.kReverse;
    }else {
      intakeVal = DoubleSolenoid.Value.kForward;
    }
    Hood.set(hoodVal);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
