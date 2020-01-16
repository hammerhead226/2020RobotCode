/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.Robot;

public class toggleCompressor extends InstantCommand {
  /**
   * Creates a new CompressorToggle.
   */
  public toggleCompressor() {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(Robot.pneumatics);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    Robot.pneumatics.toggleCompressor();
  }
}
