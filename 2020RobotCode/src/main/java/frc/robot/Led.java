/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

/**
 * Add your docs here.
 */
public class Led {
    private static CANSparkMax leds = new CANSparkMax(Constants.LED, MotorType.kBrushless);

    public static void green(){
        leds.set(0.77);
    }

    public static void blue(){
        leds.set(0.87);
    }

    public static void red(){
        leds.set(0.61);
    }

    public static void rainbow(){
        leds.set(-0.95);
    }

}
