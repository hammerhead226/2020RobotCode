package frc.libs.util;

import frc.robot.Constants;

public class Utility {

    public static double normalizeAngle(double theta) {
        return -Constants.ENCODER_TICKS * ((theta + 2 * Math.PI) % (2 * Math.PI)) / (2 * Math.PI) + Constants.ENCODER_TICKS; 
    }

    public static double[] normalizeMagnitude(double r1, double r2, double r3, double r4) {
        double max = 1;
        if(r1 > max) max = r1;
        if(r2 > max) max = r2;
        if(r3 > max) max = r3;
        if(r4 > max) max = r4;

        return new double[] {r1 / max, r2 / max, r3 / max, r4 / max}; //Function works by finding the max of the four, then dividing the rest by the max, thus making every value a percent of the max
    }

    public static double sigmoid(double angle){
        return (2/(1+Math.pow(Math.E, -angle/1.5)) - 1) ;
      } 

    public static double convertVelocitytoRPM(double velocity){
        return (velocity/204800) * 60000;
    }
}