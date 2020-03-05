/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import javax.management.Query;
import javax.management.QueryEval;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.libs.swerve.Utility;
import frc.libs.util.Limelight;
import frc.robot.commands.DrivetrainToTarget;
import frc.robot.commands.ShooterDown;
import frc.robot.commands.ShooterHoodDown;
import frc.robot.subsystems.ActiveFloor;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.ColorRoller;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.PneumaticsSystem;
import frc.robot.subsystems.Queuer;
import frc.robot.subsystems.Shooter;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private Command autonomousCommand;

  public static RobotContainer robotContainer;
  public static Climber climber = new Climber();
  public static Intake intake = new Intake();
  public static ColorRoller colorRoller = new ColorRoller();
  public static PneumaticsSystem pneumatics = new PneumaticsSystem();
  public static Shooter shooter = new Shooter();
  public static ActiveFloor activeFloor = new ActiveFloor();
  public static Queuer queuer = new Queuer();
  public static Drivetrain drivetrain = new Drivetrain();
  public static double timer;
  public double angleTime = 0;

  private double lastVelocity;

  private boolean[] checkpoints = {false, false, false, false, false, false, false, false, false};
  //checkpoints 5&4 are out of order
  private Command hoodDown;
  public enum State {
    AUTON, TELEOP
  }
  public static State state;

  /**
   * This function is run when the robot is first started up and should be used
   * for any initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer. This will perform all our button bindings,
    // and put our
    // autonomous chooser on the dashboard.
    robotContainer = new RobotContainer();
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for
   * items like diagnostics that you want ran during disabled, autonomous,
   * teleoperated and test.
   *
   * <p>
   * This runs after the mode specific periodic functions, but before LiveWindow
   * and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler. This is responsible for polling buttons, adding
    // newly-scheduled
    // commands, running already-scheduled commands, removing finished or
    // interrupted commands,
    // and running subsystem periodic() methods. This must be called from the
    // robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
    SmartDashboard.putNumber("Limelight distance", Limelight.distanceToTarget());
    //Constants.SHOOTER_MAX_RPM = (int)SmartDashboard.getNumber("set RPM", 6000);
  
  }

  /**
   * This function is called once each time the robot enters Disabled mode.
   */
  @Override
  public void disabledInit() {
    climber.disableDistSensor();
  }

  @Override
  public void disabledPeriodic() {
    if(DriverStation.Alliance.Red == DriverStation.getInstance().getAlliance()){
      Led.red();
    }else {
      Led.blue();
    }

  }

  /**
   * This autonomous runs the autonomous command selected by your
   * {@link RobotContainer} class.
   */
  @Override
  public void autonomousInit() {
    autonomousCommand = robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (autonomousCommand != null) {
      autonomousCommand.schedule();
    }

    drivetrain.zeroGyro();
    timer = Timer.getFPGATimestamp();

    hoodDown = new ShooterDown();
    hoodDown.schedule();
    pneumatics.downIntake();
    shooter.setShooterSpeed(5500);
    state = State.AUTON;
    resetCheckpoints();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    if(!checkpoints[0]) {
      drivetrain.control(0, 0, Utility.sigmoid(Limelight.getHorizontalOffset()) * Constants.SHOOTER_AUTO_ROTATE);
      shooter.setShooterSpeed(5500);
    }

    double currentVelocity = shooter.getVelocity();
    if(!checkpoints[0] && Math.abs(currentVelocity - lastVelocity) < 5 && (shooter.getVelocity() > 5300)) {
      checkpoints[0] = true;
      shooter.setShooterSpeed(5500);
    }

    if(checkpoints[0] && !checkpoints[2]) {
      activeFloor.runActiveFloor(-0.7);
      queuer.runQueuer(-0.8);
      shooter.setShooterSpeed(5500);
    }

    if(getCurrentTime() > 6.5) {
      checkpoints[1] = true;
      shooter.setShooterSpeed(5500);
    }

    if(checkpoints[1] && !checkpoints[2]) {
      drivetrain.control(0, 0, (drivetrain.getGyro() - 90) / 125); 
      shooter.setShooterSpeed(5500);
    }

    System.out.println(Math.abs(drivetrain.getGyro() - 90));

    if(Math.abs(drivetrain.getGyro() - 90) < 10) {
      checkpoints[2] = true;
      System.out.println("done turn");
      if(angleTime == 0) {
        angleTime = getCurrentTime();
      }
      shooter.setShooterSpeed(5500);
    }

    System.out.println(angleTime);

    if(checkpoints[2] && getCurrentTime() < (angleTime + 2.2)) {
      drivetrain.control(-.36, .2, 0);
      intake.intake(-.7);
      activeFloor.runActiveFloor(0);
      queuer.runQueuer(0);
      shooter.setShooterSpeed(5800);
    }

    if(checkpoints[2] && getCurrentTime() > (angleTime + 2.2)) {
      drivetrain.control(0, 0, 0);
      checkpoints[3] = true;
      shooter.setShooterSpeed(5800);
    }

    if(checkpoints[3] && getCurrentTime() < (angleTime + 2.7)) {
      drivetrain.control(0.3, 0, 0);
      shooter.setShooterSpeed(5800);
    }

    if(checkpoints[3] && getCurrentTime() > (angleTime + 2.7) && getCurrentTime() < (angleTime + 4.25)) {
      drivetrain.control(0, 0, 0);
      shooter.setShooterSpeed(5800);
      checkpoints[6] = true;
    } 

    if(checkpoints[6] && getCurrentTime() < (angleTime + 8.2)) {
      drivetrain.control(0, 0.3, 0);
      intake.intake(-.7);
      activeFloor.runActiveFloor(0);
      queuer.runQueuer(0);
      shooter.setShooterSpeed(5800);
    }

    if(checkpoints[6] && getCurrentTime() > (angleTime + 8.2)) {
      drivetrain.control(0, 0, 0);
      shooter.setShooterSpeed(5800);
    }

    // if(getCurrentTime() > 10.54 && !checkpoints[5]) {
    //   drivetrain.control(0, 0, (drivetrain.getGyro() + 5) / 100); 
    //   shooter.setShooterSpeed(5400);
    // }

    // if(Math.abs(drivetrain.getGyro() + 5) < 1) {
    //   checkpoints[5] = true;
    //   shooter.setShooterSpeed(5400);
    // }

    // if(getCurrentTime() > 10.54 && checkpoints[5]) {
    //   drivetrain.control(0, 0, Utility.sigmoid(Limelight.getHorizontalOffset() + 1.5) * Constants.SHOOTER_AUTO_ROTATE);
    //   shooter.setShooterSpeed(5800);
    // }

    // if(checkpoints[3] && Math.abs(currentVelocity - lastVelocity) < 5 && (shooter.getVelocity() > 5800)) {
    //   checkpoints[4] = true;
    //   shooter.setShooterSpeed(5800);
    // }

    // if(checkpoints[4]) {
    //   activeFloor.runActiveFloor(-0.7);
    //   queuer.runQueuer(-0.8);
    //   shooter.setShooterSpeed(5800);
    // }

    lastVelocity = shooter.getVelocity();
  }

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (autonomousCommand != null) {
      autonomousCommand.cancel();
    }
    SmartDashboard.putNumber("set RPM", 5600);
    
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    state = state.TELEOP;
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }

  private double getCurrentTime() {
    return Timer.getFPGATimestamp() - timer;
  }

  private void resetCheckpoints() {
    for(int i = 0; i < checkpoints.length; i++) {
      checkpoints[i] = false;
    }
  }
}
