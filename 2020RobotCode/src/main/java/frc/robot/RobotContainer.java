/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.libs.util.Controller;
import frc.robot.commands.DrivetrainToTarget;
import frc.robot.commands.FollowTrajectory;
import frc.robot.commands.RunShooter;
import frc.robot.commands.ShooterDown;
import frc.robot.commands.ShooterHoodDown;
import frc.robot.commands.ShooterHoodUp;
import frc.robot.commands.ShooterUp;
import frc.robot.commands.JogActiveFloor;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a "declarative" paradigm, very little robot logic should
 * actually be handled in the {@link Robot} periodic methods (other than the
 * scheduler calls). Instead, the structure of the robot (including subsystems,
 * commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  public Controller driver;
  public Controller manip;

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    driver = new Controller(0, 0.05);
    manip = new Controller(1, 0.05);
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by instantiating a {@link GenericHID} or one of its subclasses
   * ({@link edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then
   * passing it to a {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    manip.getAButton().whileHeld(new ShooterHoodUp());
    manip.getAButton().whenPressed(new InstantCommand(Robot.shooter::slow));
    manip.getAButton().whenReleased(new ShooterHoodDown());
    manip.getBButton().whileHeld(new ShooterHoodDown());
    manip.getBButton().whenPressed(new InstantCommand(Robot.shooter::speed));
    driver.getAButton().whileHeld(new DrivetrainToTarget());
    driver.getXButton().whileHeld(new InstantCommand(Robot.drivetrain::brake, Robot.drivetrain));
    // driver.getSELECTButton().whenPressed(new
    // InstantCommand(Robot.driveTrain::zeroGyro, Robot.driveTrain));
    driver.getSTARTButton().whenPressed(new InstantCommand(Robot.pneumatics::toggleCompressor, Robot.pneumatics));
    manip.getRBButton().whenPressed(new InstantCommand(Robot.pneumatics::toggleClimber, Robot.pneumatics));

    driver.getRBButton().whenPressed(new InstantCommand(Robot.pneumatics::toggleIntake, Robot.pneumatics));
    driver.getYButton().whenPressed(new InstantCommand(Robot.drivetrain::zeroGyro, Robot.drivetrain));
    manip.getSTARTButton().whenPressed(new InstantCommand(Robot.climber::zeroClimber, Robot.climber));
    manip.getRBButton().whileHeld(new JogActiveFloor());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
