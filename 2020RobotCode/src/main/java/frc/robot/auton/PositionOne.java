/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.auton;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.libs.util.Utility;
import frc.robot.Constants;
import frc.robot.Robot;
import frc.robot.commands.ShooterDown;
import frc.libs.util.Limelight;

public class PositionOne extends CommandBase {
  private boolean[] checkpoints = {false, false, false, false, false, false, false, false, false};
  private Command hoodDown;
  public double angleTime = 0;
  private double lastVelocity;
 
  /**
   * Creates a new PositionOne.
   */
  public PositionOne() {
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
      Robot.shooter.setShooterSpeed(6000);
    }

    double currentVelocity = Robot.shooter.getVelocity();
    if(!checkpoints[0] && Math.abs(currentVelocity - lastVelocity) < 5 && (Robot.shooter.getVelocity() > 5500)) {
      checkpoints[0] = true;
      Robot.shooter.setShooterSpeed(6000);
    }

    if(checkpoints[0] && !checkpoints[2]) {
      Robot.activeFloor.runActiveFloor(-0.7);
      Robot.queuer.runQueuer(0.8);
      Robot.shooter.setShooterSpeed(6000);
    }

    if(Robot.getCurrentTime() > 6.5) {
      checkpoints[1] = true;
      Robot.shooter.setShooterSpeed(6000);
    }

    if(checkpoints[1] && !checkpoints[2]) {
      Limelight.setLEDMode(1);
      Robot.drivetrain.control(0, 0, (Robot.drivetrain.getGyro() - 90) / 125); 
      Robot.shooter.setShooterSpeed(6000);
    }

    if(Math.abs(Robot.drivetrain.getGyro() - 90) < 10) {
      checkpoints[2] = true;
      System.out.println("done turn");
      if(angleTime == 0) {
        angleTime = Robot.getCurrentTime();
      }
      Robot.shooter.setShooterSpeed(6000);
    }

    if(checkpoints[2] && Robot.getCurrentTime() < (angleTime + 2.2)) {
      Robot.drivetrain.control(-.36, .2, 0);
      Robot.intake.intake(-.7);
      Robot.activeFloor.runActiveFloor(0);
      Robot.queuer.runQueuer(0);
      Robot.shooter.setShooterSpeed(6000);
    }

    if(checkpoints[2] && Robot.getCurrentTime() > (angleTime + 2.2)) {
      Robot.drivetrain.control(0, 0, 0);
      checkpoints[3] = true;
      Robot.shooter.setShooterSpeed(6000);
    }

    if(checkpoints[3] && Robot.getCurrentTime() < (angleTime + 2.7)) {
      Robot.drivetrain.control(0.3, 0, 0);
      Robot.shooter.setShooterSpeed(6000);
    }

    if(checkpoints[3] && Robot.getCurrentTime() > (angleTime + 2.7) && Robot.getCurrentTime() < (angleTime + 4.25)) {
      Robot.drivetrain.control(0, 0, 0);
      Robot.shooter.setShooterSpeed(6000);
      checkpoints[6] = true;
    } 

    if(checkpoints[6] && Robot.getCurrentTime() < (angleTime + 8.2)) {
      Robot.drivetrain.control(0, 0.3, 0);
      Robot.intake.intake(-.7);
      Robot.activeFloor.runActiveFloor(0);
      Robot.queuer.runQueuer(0);
      Robot.shooter.setShooterSpeed(6000);
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
    return checkpoints[6] && Robot.getCurrentTime() > (angleTime + 8.2);
  }
}
