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
    public static final boolean FRONT_LEFT_STEER_INVERTED = false;

    public static int REAR_LEFT_DRIVE = 2;
    public static int REAR_LEFT_STEER = 6;
    public static int REAR_LEFT_ENCODER = 1;
    public static final boolean REAR_LEFT_STEER_INVERTED = false;    
    

    public static int FRONT_RIGHT_DRIVE = 3;
    public static int FRONT_RIGHT_STEER = 7;
    public static int FRONT_RIGHT_ENCODER = 2;
    public static final boolean FRONT_RIGHT_STEER_INVERTED = false;
    
    public static int REAR_RIGHT_DRIVE = 4;
    public static int REAR_RIGHT_STEER = 8;
    public static int REAR_RIGHT_ENCODER = 3;
    public static final boolean REAR_RIGHT_STEER_INVERTED = false;

    public static final int PIGEON = 7;

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

    public static final int CLIMBER_MOTOR_1 = 0;

    public static final boolean DRIVETRAIN_CURRENT_ENABLE = false;
    public static final double DRIVETRAIN_CURRENT_LIMIT = 40;
    public static final double DRIVETRAIN_CURRENT_THRESHOLD_TIME = 2;

    public static final boolean INTAKE_CURRENT_ENABLE = false;
    public static final double INTAKE_CURRENT_LIMIT = 40; 
    public static final double INTAKE_CURRENT_THRESHOLD_TIME = 2;
    public static final double INTAKE_CURRENT_THRESHOLD_LIMIT = 60;


    public static final boolean SHOOTER_1_CURRENT_ENABLE = false;
    public static final double SHOOTER_1_CURRENT_LIMIT = 40; 
    public static final double SHOOTER_1_CURRENT_THRESHOLD_TIME = 2;
    public static final double SHOOTER_1_CURRENT_THRESHOLD_LIMIT = 60;

    public static final boolean SHOOTER_2_CURRENT_ENABLE = false;
    public static final double SHOOTER_2_CURRENT_LIMIT = 40; 
    public static final double SHOOTER_2_CURRENT_THRESHOLD_TIME = 2;
    public static final double SHOOTER_2_CURRENT_THRESHOLD_LIMIT = 60;

    
    public static final double DRIVER_TRIGGER_TOLERANCE = 0.05;


    public static final double INTAKE_VOLTAGE_LIMIT = 12; 
    public static final boolean INTAKE_VOLTAGE_ENABLE = false;

    public static final double DRIVETRAIN_VOLTAGE_LIMIT = 12;
    public static final boolean DRIVETRAIN_VOLTAGE_ENABLE = false; 
    public static final double DRIVETRAIN_CURRENT_THRESHOLD_LIMIT = 60;

    public static final double SHOOTER_1_VOLTAGE_LIMIT = 12; 
    public static final boolean SHOOTER_1_VOLTAGE_ENABLE = false;

    public static final double SHOOTER_2_VOLTAGE_LIMIT = 12;
    public static final boolean SHOOTER_2_VOLTAGE_ENABLE = false;

    public static final boolean CLIMBER_CURRENT_ENABLE = false;
    public static final double CLIMBER_CURRENT_LIMIT = 40;
    public static final double CLIMBER_CURRENT_THRESHOLD_TIME = 2;
    public static final double CLIMBER_CURRENT_THRESHOLD_LIMIT= 60;

    public static final double CLIMBER_VOLTAGE_LIMIT = 12;
    public static final boolean CLIMBER_VOLTAGE_ENABLE = false;

    public static final int SHOOTER_1 = 0;
    public static final int SHOOTER_2 = 0;
    public static final boolean SHOOTER_1_INVERTED = false;
    public static final boolean SHOOTER_2_INVERTED = false;

    public static final int ROLLER = 0;
    public static final int INTAKE = 0;
    public static final boolean INTAKE_INVERTED = false;  
    
    public static final int CLIMBER = 0;
    public static final boolean CLIMBER_INVERTED = false;

    public static final double TICKS_PER_REV_COLORWHEEL = 0;

    public static final int INTAKE_SHIFT_1 = 0;
    public static final int INTAKE_SHIFT_2 = 0;
    public static final int SHOOTER_SHIFT = 0;
    public static final int CLIMBER_SHIFT_1 = 0;
    public static final int CLIMBER_SHIFT_2 = 0;
    public static final int COMPRESSOR = 0;

    public static final int SHOOTER_MAX_RPM = 5000;

    public static final int PID_INDEX = 0;
    public static final int PID_TIMEOUT = 10;
  
    public static final int QUEUER = 0;

    public static final int ACTIVE_FLOOR_MOTOR = 0;
    public static final boolean ACTIVE_FLOOR_INVERTED = false;
    public static final boolean ACTIVE_FLOOR_CURRENT_ENABLE = false;
    public static final double ACTIVE_FLOOR_CURRENT_LIMIT = 40;
    public static final double ACTIVE_FLOOR_CURRENT_THRESHOLD_TIME = 2;
    public static final double ACTIVE_FLOOR_VOLTAGE_LIMIT = 12;
    public static final boolean ACTIVE_FLOOR_VOLTAGE_ENABLE = false; 


    public static final int BEAM_BREAKER = 0;
    public static final double ACTIVE_FLOOR_SPEED = 1;
    public static final double QUEUER_SPEED = 1;
    
    public static final int LED = 0;

}

