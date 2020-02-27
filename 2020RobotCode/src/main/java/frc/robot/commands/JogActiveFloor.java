/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.Robot;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/latest/docs/software/commandbased/convenience-features.html
public class JogActiveFloor extends SequentialCommandGroup {
  /**
   * Creates a new JogActiveFloor.
   */
  public JogActiveFloor() {
    // Add your commands in the super() call, e.g.
    // super(new FooCommand(), new BarCommand());
    //super(new ActiveFloorForward(), new Wait(0.25), new ActiveFloorReverse());
    super(new InstantCommand(Robot.activeFloor::jogActiveFloorForward, Robot.activeFloor), new Wait(Constants.JOG_ACTIVE_FLOOR_WAIT_TIME), new InstantCommand(Robot.activeFloor::jogActiveFloorBackward, Robot.activeFloor));
  }
}
