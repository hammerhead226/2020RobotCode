/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.libs.util;

import edu.wpi.first.networktables.NetworkTableInstance;
import frc.robot.Constants;
import frc.robot.Robot;
import edu.wpi.first.networktables.NetworkTable;

/**
 * Add your docs here.
 */
public class Limelight {
    private static NetworkTable limelight = NetworkTableInstance.getDefault().getTable("limelight");

    public static double getValue(String index) {
        return limelight.getEntry(index).getDouble(0);
    }

    public static boolean isTargetValid() {
        return limelight.getEntry("tv").getDouble(0) == 1;
    }

    public static double getHorizontalOffset() {
        return limelight.getEntry("tx").getDouble(0);
    }

    public static double getVerticalOffset() {
        return limelight.getEntry("ty").getDouble(0);
    }

    public static double getTargetArea() {
        return limelight.getEntry("ta").getDouble(0);
    }

    public static double getSkew() {
        return limelight.getEntry("ts").getDouble(0);
    }

    public static double getPipelineLatency() {
        return limelight.getEntry("tl").getDouble(0);
    }

    public static double getShortestSidelength() {
        return limelight.getEntry("tshort").getDouble(0);
    }

    public static double getLongestSidelength() {
        return limelight.getEntry("tlong").getDouble(0);
    }

    public static double getHorizontalSidelength() {
        return limelight.getEntry("thor").getDouble(0);
    }

    public static double getVerticalSidelength() {
        return limelight.getEntry("tvert").getDouble(0);
    }

    public static double getPipeline() {
        return limelight.getEntry("getpipe").getDouble(0);
    }

    public static double get3DPos() {
        return limelight.getEntry("camtran").getDouble(0);
    }

    public static void setLEDMode(int mode) {
        limelight.getEntry("ledMode").setNumber(mode);
    }

    public static void setCamMode(int mode) {
        limelight.getEntry("camMode").setNumber(mode);
    }

    public static void setPipeline(int line) {
        limelight.getEntry("pipeline").setNumber(line);
    }

    public static void setStreamMode(int mode) {
        limelight.getEntry("stream").setNumber(mode);
    }

    public static void takeSnapshot(int mode) {
        limelight.getEntry("snapshot").setNumber(mode);
    }

    public static double distanceToTarget() {
        return (Constants.HIGH_GOAL_HEIGHT - Constants.CAMERA_HEIGHT)
                / Math.tan(Math.toRadians(Constants.LIMELIGHT_ANGLE + getVerticalOffset()));
    }

    public static double getTargetRotation() {
        return Math
                .asin((Robot.drivetrain.currentPose2d.getTranslation().getX() - Constants.GOAL_X) / distanceToTarget());
    }

}