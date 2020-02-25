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
public final class Constants 
{
    //DRIVE
    public static int FRONT_LEFT_DRIVE = 3;
    public static int REAR_LEFT_DRIVE = 2;
    public static int FRONT_RIGHT_DRIVE = 0;
    public static int REAR_RIGHT_DRIVE = 1;

    //STEER
    public static int FRONT_LEFT_STEER = 13;
    public static int REAR_LEFT_STEER = 12;
    public static int FRONT_RIGHT_STEER = 10;
    public static int REAR_RIGHT_STEER = 11;

    //ENCODER
    public static int FRONT_LEFT_ENCODER = 3;
    public static int REAR_LEFT_ENCODER = 2;
    public static int FRONT_RIGHT_ENCODER = 0;
    public static int REAR_RIGHT_ENCODER = 1;

    //STEER_INVERTED
    public static final boolean FRONT_LEFT_STEER_INVERTED = true;
    public static final boolean REAR_LEFT_STEER_INVERTED = true;    
    public static final boolean FRONT_RIGHT_STEER_INVERTED = true;
    public static final boolean REAR_RIGHT_STEER_INVERTED = true;

    public static final int PIGEON = 0;

    //ENCODER VALUES
    public static final int ENCODER_TICKS = 4096;
    public static final double DRIVE_REV_PER_INCH = .6614404704;

    //DRIVE_BASE_VALUES
    public static final double DRIVE_BASE_WIDTH = 23.5 / 12;
    public static final double DRIVE_BASE_LENGTH = 21.5 / 12;

    //KP VALUES
    public static final double STEER_KP = 0.0007;
    public static final double STEER_KD = 0.00005;
    public static final double DRIVE_KP = 0.05;
    public static final double DRIFT_CORRECTION_KP = 0;
    public static final double AUTO_STEER_KP = 0.0;
    public static final double AUTO_ROTATE_KP = 0.0;

    public static final double MAX_AUTO_DRIVE_SPEED = 0.3;

    //MAX_AUTO_ERRORS
    public static final double MAX_AUTO_DRIVE_ERROR = 1;
    public static final double MAX_AUTO_ROTATE_ERROR = 1;
    public static final double MAX_AUTO_STEER_ERROR = 400;

//3, 2, 0, 1
    public static final int[] MODULE_OFFSETS = {3550, 3900, 200, 650};

    public static final int MODULE_1_OFFSET = MODULE_OFFSETS[0];
    public static final int MODULE_2_OFFSET = MODULE_OFFSETS[1];
    public static final int MODULE_3_OFFSET = MODULE_OFFSETS[2];
    public static final int MODULE_4_OFFSET = MODULE_OFFSETS[3];

    //CURRENT_ENABLES
    public static final boolean DRIVETRAIN_CURRENT_ENABLE = false;
    public static final boolean INTAKE_CURRENT_ENABLE = false;
    public static final boolean SHOOTER_1_CURRENT_ENABLE = false;
    public static final boolean SHOOTER_2_CURRENT_ENABLE = false;
    public static final boolean CLIMBER_CURRENT_ENABLE = false;
    public static final boolean ACTIVE_FLOOR_CURRENT_ENABLE = false;

    //CURRENT_LIMITS
    public static final double DRIVETRAIN_CURRENT_LIMIT = 40;
    public static final double INTAKE_CURRENT_LIMIT = 40; 
    public static final double SHOOTER_1_CURRENT_LIMIT = 40; 
    public static final double SHOOTER_2_CURRENT_LIMIT = 40; 
    public static final double CLIMBER_CURRENT_LIMIT = 40;
    public static final double ACTIVE_FLOOR_CURRENT_LIMIT = 40;

    //CURRENT_THRESHOLD_TIME
    public static final double DRIVETRAIN_CURRENT_THRESHOLD_TIME = 2;
    public static final double INTAKE_CURRENT_THRESHOLD_TIME = 2;
    public static final double SHOOTER_1_CURRENT_THRESHOLD_TIME = 2;
    public static final double SHOOTER_2_CURRENT_THRESHOLD_TIME = 2;
    public static final double ACTIVE_FLOOR_CURRENT_THRESHOLD_TIME = 2;
    public static final double CLIMBER_CURRENT_THRESHOLD_TIME = 2;

    //CURRENT_THRESHOLD_LIMIT
    public static final double DRIVETRAIN_CURRENT_THRESHOLD_LIMIT = 60;
    public static final double INTAKE_CURRENT_THRESHOLD_LIMIT = 60;
    public static final double SHOOTER_1_CURRENT_THRESHOLD_LIMIT = 60;
    public static final double SHOOTER_2_CURRENT_THRESHOLD_LIMIT = 60;
    public static final double CLIMBER_CURRENT_THRESHOLD_LIMIT= 60;

    //TRIGGER_TOLERANCE
    public static final double DRIVER_TRIGGER_TOLERANCE = 0.05;

    //INTAKE_VOLTAGE_LIMIT
    public static final double INTAKE_VOLTAGE_LIMIT = 12; 
    public static final double DRIVETRAIN_VOLTAGE_LIMIT = 12;
    public static final double SHOOTER_1_VOLTAGE_LIMIT = 12; 
    public static final double SHOOTER_2_VOLTAGE_LIMIT = 12;
    public static final double CLIMBER_VOLTAGE_LIMIT = 12;
    public static final double ACTIVE_FLOOR_VOLTAGE_LIMIT = 12;

    //VOLTAGE_ENABLE
    public static final boolean INTAKE_VOLTAGE_ENABLE = false;
    public static final boolean DRIVETRAIN_VOLTAGE_ENABLE = false; 
    public static final boolean SHOOTER_1_VOLTAGE_ENABLE = false;
    public static final boolean SHOOTER_2_VOLTAGE_ENABLE = false;
    public static final boolean CLIMBER_VOLTAGE_ENABLE = false;
    public static final boolean ACTIVE_FLOOR_VOLTAGE_ENABLE = false; 

    //SOFT_LIMIT
    public static final int CLIMBER_SOFT_LIMIT = 120;

    //PORTS
    public static final int SHOOTER_1 = 4;
    public static final int SHOOTER_2 = 5;
    public static final int INTAKE = 16;
    public static final int CLIMBER = 6;
    public static final int ACTIVE_FLOOR_MOTOR = 15;
    public static final int QUEUER = 14;

    //INVERTED
    public static final boolean SHOOTER_1_INVERTED = false;
    public static final boolean SHOOTER_2_INVERTED = true;
    public static final boolean INTAKE_INVERTED = true;  
    public static final boolean CLIMBER_INVERTED = false;
    public static final boolean ACTIVE_FLOOR_INVERTED = false;
    

    public static final double TICKS_PER_REV_COLORWHEEL = 0;

    //SHIFTS
    public static final int INTAKE_SHIFT_1 = 0;
    public static final int INTAKE_SHIFT_2 = 1;
    public static final int SHOOTER_SHIFT = 4;
    public static final int CLIMBER_SHIFT_1 = 2;
    public static final int CLIMBER_SHIFT_2 = 3;
    public static final int COMPRESSOR = 0;

    public static final int SHOOTER_MAX_RPM = 5000;

    //PIDs
    public static final int PID_INDEX = 0;
    public static final int PID_TIMEOUT = 10;


    public static final int BEAM_BREAKER = 0;
    public static final double ACTIVE_FLOOR_SPEED = 1;
    public static final double QUEUER_SPEED = 1;
    
    public static final int LED = 0;

    public static final double GYRO_ORIENTATION = Math.PI;
}

