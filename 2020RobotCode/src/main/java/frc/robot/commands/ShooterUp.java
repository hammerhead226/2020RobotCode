/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Robot;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class ShooterUp extends SequentialCommandGroup {
  /**
   * Creates a new ShooterUp.
   */
  public ShooterUp() {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    super(new InstantCommand(Robot.shooter::enableLimelight, Robot.shooter), new InstantCommand(Robot.pneumatics::shooterBrakeIn, Robot.pneumatics), new Wait(0.25), new InstantCommand(Robot.pneumatics::shooterUp, Robot.pneumatics));
  }
}
