/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static int FRONT_LEFT_DRIVE = 1;
    public static int FRONT_LEFT_STEER = 5;
    public static int FRONT_LEFT_ENCODER = 0;

    public static int REAR_LEFT_DRIVE = 2;
    public static int REAR_LEFT_STEER = 6;
    public static int REAR_LEFT_ENCODER = 1;
    
    public static int FRONT_RIGHT_DRIVE = 3;
    public static int FRONT_RIGHT_STEER = 7;
    public static int FRONT_RIGHT_ENCODER = 2;
    
    public static int REAR_RIGHT_DRIVE = 4;
    public static int REAR_RIGHT_STEER = 8;
    public static int REAR_RIGHT_ENCODER = 3;

    public static final int PIGEON = 7;

    public static final int JOYSTICK = 0;

    public static final int ENCODER_TICKS = 4096;
    public static final double DRIVE_REV_PER_INCH = .6614404704;

    public static final double DRIVE_BASE_WIDTH = 23.5 / 12;
    public static final double DRIVE_BASE_LENGTH = 21.5 / 12;

    public static final double STEER_KP = 0.0005;
    public static final double DRIVE_KP = 0.05;
    public static final double DRIFT_CORRECTION_KP = 0.015;
    public static final double AUTO_STEER_KP = 0.005;
    public static final double AUTO_ROTATE_KP = 0.005;

    public static final double MAX_AUTO_DRIVE_SPEED = 0.3;

    public static final double MAX_AUTO_DRIVE_ERROR = 1;
    public static final double MAX_AUTO_ROTATE_ERROR = 1;
    public static final double MAX_AUTO_STEER_ERROR = 400;

    public static final int[] MODULE_OFFSETS = {1330, 3656, 620, 3340};

    public static final int MODULE_1_OFFSET = MODULE_OFFSETS[0];
    public static final int MODULE_2_OFFSET = MODULE_OFFSETS[1];
    public static final int MODULE_3_OFFSET = MODULE_OFFSETS[2];
    public static final int MODULE_4_OFFSET = MODULE_OFFSETS[3];


}
