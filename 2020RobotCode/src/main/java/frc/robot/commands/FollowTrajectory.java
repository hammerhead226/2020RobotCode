/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class FollowTrajectory extends CommandBase {
  /**
   * Creates a new FollowTrajectory.
   */
  Trajectory trajectory;
  int numStates;
  double startTime;
  
  public FollowTrajectory(Trajectory trajectory) {
    // Use addRequirements() here to declare subsystem dependencies
    addRequirements(Robot.drivetrain);
    this.trajectory = trajectory;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    startTime = Timer.getFPGATimestamp();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    double currTime = Timer.getFPGATimestamp() - startTime;
    Trajectory.State state = trajectory.sample(currTime);
    Robot.drivetrain.control(state);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.drivetrain.control(0, 0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Timer.getFPGATimestamp() - startTime >= trajectory.getTotalTimeSeconds();
  }
}
