/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.libs.util.Utility;
import frc.libs.util.Limelight;
import frc.robot.Constants;
import frc.robot.Robot;

public class DrivetrainToTarget extends CommandBase {
  /**
   * Creates a new DrivetrainToTarget.
   */
  public DrivetrainToTarget() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.drivetrain);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Limelight.setLEDMode(3);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    Robot.drivetrain.control(0, 0, Utility.sigmoid(Limelight.getHorizontalOffset()) * Constants.STEER_AUTO_KP);
    if(Math.abs(Limelight.getHorizontalOffset()) < 1 ) {
      Robot.drivetrain.isLocked = true;
    } else {
      Robot.drivetrain.isLocked = false;
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.drivetrain.control(0, 0, 0);
    Robot.drivetrain.isLocked = false;
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Math.abs(Limelight.getHorizontalOffset()) <= 0.5;
  }
}
