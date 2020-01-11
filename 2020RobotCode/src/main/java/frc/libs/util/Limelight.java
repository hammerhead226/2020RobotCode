/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.libs.util;

import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.networktables.NetworkTable;

/**
 * Add your docs here.
 */
public class Limelight {
    NetworkTable limelight =  NetworkTableInstance.getDefault().getTable("limelight");

    public double getValue(String index){
        return limelight.getEntry(index).getDouble(0);
    }

    public double getNumTargets(){
        return limelight.getEntry("tv").getDouble(0);
    }

    public double getHorizontalOffset(){
        return limelight.getEntry("tx").getDouble(0);
    }

    public double getVerticallOffset(){
        return limelight.getEntry("ty").getDouble(0);
    }
}
