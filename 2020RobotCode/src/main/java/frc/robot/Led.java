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
    private static CANSparkMax leds = new CANSparkMax(1, MotorType.kBrushless);

    public static void LedColorGreen(){
        leds.set(0.77);
    }

    public static void LedColorBlue(){
        leds.set(0.87);
    }

    public static void LedColorRed(){
        leds.set(0.61);
    }

    public static void LedColorRainbow(){
        leds.set(-0.95);
    }

}
