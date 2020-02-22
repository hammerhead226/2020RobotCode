/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.libs.swerve;

import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;

/**
 * Add your docs here.
 */
public class SwerveControl {

    private double r1, r2, r3, r4;
    private double theta1, theta2, theta3, theta4;
    private SwerveModule module1, module2, module3, module4;
    private PigeonIMU pigeon;
    private double holdAngle;
    private boolean isRotateZero = false;
    public static double[] errorTracker;

    public SwerveControl(SwerveModule module1, SwerveModule module2, SwerveModule module3, SwerveModule module4, PigeonIMU pigeon) {
        this.module1 = module1;
        this.module2 = module2;
        this.module3 = module3;
        this.module4 = module4;
        this.pigeon = pigeon;
        errorTracker = new double[] {0, 0, 0, 0, 0};
    }

    public void control(double x, double y, double rotate) {
        double[] ypr = new double[3];
        pigeon.getYawPitchRoll(ypr);
        double gyro = ypr[0];

        if(x == 0 && y == 0 && rotate == 0) {
            r1 = 0;
            r2 = 0;
            r3 = 0;
            r4 = 0;
        } else {
            if(!isRotateZero) {
                isRotateZero = (rotate == 0);
                if(isRotateZero){
                    holdAngle = gyro;
                }
            } else {
                isRotateZero = (rotate == 0);
            }

            // if(rotate == 0) {
            //     double rotateError = holdAngle - gyro;
            //     rotate = -rotateError * Constants.DRIFT_CORRECTION_KP;
            // }
            double mag = Math.hypot(x, y);//takes distance between (0,0) on the joystick and (x, y) inputted
            double controllerTheta = Math.atan2(y, x);//calculates the angle between the x axis and the line between (0, 0) and (x, y), returns in radians
            controllerTheta = (controllerTheta + 2 * Math.PI) % (2 * Math.PI); 
            controllerTheta = controllerTheta + Math.toRadians(gyro); //calculating the distance between goal(controllertheta) and starting rotation(gyro)
            x = mag * Math.cos(controllerTheta); //calculating the new x after changing the controller theta. Equation is hypot * (newX/hypot), the hypots cancel, giving the newX
            y = mag * Math.sin(controllerTheta);//Same things as above, but with y

            double a = rotate * Constants.DRIVE_BASE_LENGTH / 2 + x;
            double b = -rotate * Constants.DRIVE_BASE_LENGTH / 2 + x;
            double c = rotate * Constants.DRIVE_BASE_WIDTH / 2 + y;
            double d = -rotate * Constants.DRIVE_BASE_WIDTH / 2 + y;

            double[] normalizedMagnitude = Utility.normalizeMagnitude(
                Math.hypot(a, c), 
                Math.hypot(b, c), 
                Math.hypot(b, d), 
                Math.hypot(a, d)
            ); //Makes it such that all the values are at max 1, and never above, see utility.java for further detail

            r1 = normalizedMagnitude[0]; // Each of these 4 take their respective percents of the maximum
            r2 = normalizedMagnitude[1];
            r3 = normalizedMagnitude[2];
            r4 = normalizedMagnitude[3];

            theta1 = Utility.normalizeAngle(Math.atan2(c, a)) + Constants.MODULE_1_OFFSET;
            theta2 = Utility.normalizeAngle(Math.atan2(c, b)) + Constants.MODULE_2_OFFSET;
            theta3 = Utility.normalizeAngle(Math.atan2(d, b)) + Constants.MODULE_3_OFFSET;
            theta4 = Utility.normalizeAngle(Math.atan2(d, a)) + Constants.MODULE_4_OFFSET;
        }

        module1.drive(-r1, -theta1);
        module2.drive(-r2, -theta2);
        module3.drive(-r3, -theta3);
        module4.drive(r4, -theta4);

        SmartDashboard.putNumber("mod 1", module1.getAngle());
        SmartDashboard.putNumber("mod 2", module2.getAngle());
        SmartDashboard.putNumber("mod 3", module3.getAngle());
        SmartDashboard.putNumber("mod 4", module4.getAngle());

        SmartDashboard.putNumber("gyro", gyro);

    }

    public double getGyro() {
        double[] ypr = new double[3];
        pigeon.getYawPitchRoll(ypr);
        return ypr[0];
    }

}