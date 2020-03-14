/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auton;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.libs.util.Limelight;
import frc.libs.util.Utility;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.ShooterDown;

public class PositionTwo extends CommandBase {
  private boolean[] checkpoints = { false, false, false, false, false, false, false, false, false };
  private Command hoodDown;
  public double angleTime = 0;
  private double lastVelocity;

  /**
   * Creates a new PositionTwo.
   */
  public PositionTwo() {
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    hoodDown = new ShooterDown();
    hoodDown.schedule();
    Robot.shooter.setShooterSpeed(5500);
    Robot.intake.intake(-0.5);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    if (!checkpoints[0]) {
      Limelight.setLEDMode(3);
      Robot.drivetrain.control(0, 0, Utility.sigmoid(Limelight.getHorizontalOffset()) * Constants.STEER_AUTO_KP);
      Robot.shooter.setShooterSpeed(5700);
      Robot.drivetrain.control(0, 0, 0);
    }

    double currentVelocity = Robot.shooter.getVelocity();
    if (!checkpoints[0] && Math.abs(currentVelocity - lastVelocity) < 5 && (Robot.shooter.getVelocity() > 5700)) {
      checkpoints[0] = true;
      Robot.shooter.setShooterSpeed(5700);
    }

    if (checkpoints[0]) {
      Robot.activeFloor.runActiveFloor(-0.6);
      Robot.queuer.runQueuer(0.7);
      Robot.shooter.setShooterSpeed(5700);
    }

    if (Robot.getCurrentTime() > 12) {
      checkpoints[1] = true;
      Robot.shooter.setShooterSpeed(5700);
      Robot.drivetrain.control(0, 0.3, 0);
      Robot.intake.intake(0);
      Robot.pneumatics.upIntake();
    }
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    Robot.drivetrain.control(0, 0, 0);
    Robot.shooter.setShooterSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return Robot.getCurrentTime() > 13.5;
  }
}
