/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.libs.swerve;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.TalonFXControlMode;
import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.kinematics.SwerveModuleState;
import frc.robot.Constants;


public class SwerveModule {

    private TalonFX drive;
    private VictorSPX steer;
    private EncoderWrapper steercoder;
    private int module;

    public SwerveModule(TalonFX drive, VictorSPX steer, AnalogInput steercoder, int module) {
        this.drive = drive;
        this.steer = steer;
        this.steercoder = new EncoderWrapper(steercoder);
        this.module = module;
    }


    public void drive(double r, double theta) {
        steercoder.update();

        double error = theta - steercoder.getValue();
        error = error % Constants.ENCODER_TICKS;

        //choose direction to rotate module
        if(Math.abs(error) > Math.abs(error - Constants.ENCODER_TICKS)) {
            error = error - Constants.ENCODER_TICKS;
        } else if(Math.abs(error) > Math.abs(error + Constants.ENCODER_TICKS)) {
            error = error + Constants.ENCODER_TICKS;
        }
        //flip module or run in reverse
        if(Math.abs(error) > Math.abs(error - (Constants.ENCODER_TICKS / 2))) {
            r = -r;
            error = error - (Constants.ENCODER_TICKS / 2);
        } else if(Math.abs(error) > Math.abs(error + (Constants.ENCODER_TICKS / 2))) {
            r = -r;
            error = error + (Constants.ENCODER_TICKS / 2);
        }
        drive.set(TalonFXControlMode.PercentOutput, r);
        steer.set(ControlMode.PercentOutput, error * Constants.STEER_KP);
    }

    

    public SwerveModuleState getState(){
        //  update velocity units
        return new SwerveModuleState(drive.getSelectedSensorVelocity(), Rotation2d.fromDegrees(steercoder.getValue()));
    }

   
}