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
import frc.robot.Robot;

public class PneumaticsSystem extends SubsystemBase {
  /**
   * Creates a new Pneumatics.
   */
  private Compressor compressor = new Compressor(Constants.COMPRESSOR);
  private DoubleSolenoid Intake = new DoubleSolenoid(Constants.INTAKE_SHIFT_1, Constants.INTAKE_SHIFT_2);
  private DoubleSolenoid Shooter = new DoubleSolenoid(Constants.SHOOTER_SHIFT_1, Constants.SHOOTER_SHIFT_2);
  private DoubleSolenoid Climber = new DoubleSolenoid(Constants.CLIMBER_SHIFT_1, Constants.CLIMBER_SHIFT_2);
  private DoubleSolenoid.Value intakeVal = DoubleSolenoid.Value.kForward;
  private DoubleSolenoid.Value shooterVal = DoubleSolenoid.Value.kForward;
  private DoubleSolenoid.Value climberVal = DoubleSolenoid.Value.kForward;

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
    Intake.set(intakeVal);
  }

  public void toggleShooter(){
    if(shooterVal == DoubleSolenoid.Value.kReverse){
      shooterVal = DoubleSolenoid.Value.kForward;
    }
    else {
      shooterVal = DoubleSolenoid.Value.kReverse;
    }
    Shooter.set(shooterVal);
  }

  public void shooterUp() {
    shooterVal = DoubleSolenoid.Value.kForward;
    Shooter.set(shooterVal);
  }

  public void shooterDown() {
    shooterVal = DoubleSolenoid.Value.kReverse;
    Shooter.set(shooterVal);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if(Robot.robotContainer.getDriverLeftTrigger() >= 0.2) {
      intakeVal = DoubleSolenoid.Value.kForward;
    }
    else {
      intakeVal = DoubleSolenoid.Value.kReverse;
    }
    Intake.set(intakeVal);
  }
}
