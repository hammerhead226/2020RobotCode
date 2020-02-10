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
    private CANSparkMax leds = new CANSparkMax(1, MotorType.kBrushless);

    public void LedColorGreen(){
        leds.set(0.77);
    }

    public void LedColorBlue(){
        leds.set(0.87);
    }

    public void LedColorRed(){
        leds.set(0.61);
    }

    public void LedColorRainbow(){
        leds.set(-0.95);
    }

}
