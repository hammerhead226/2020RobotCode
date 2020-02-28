/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.util.ArrayList;

import com.ctre.phoenix.music.Orchestra;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import frc.robot.RobotMap;;

/**
 * Add your docs here.
 */
public class MusicPlayer {
     /* The orchestra object that holds all the instruments */
    private static Orchestra orchestra;

     /* Talon FXs to play music through.  
     More complex music MIDIs will contain several tracks, requiring multiple instruments.  */
    private static TalonFX [] fxes =  {new TalonFX(RobotMap.FRONT_LEFT_STEER), new TalonFX(RobotMap.FRONT_RIGHT_STEER), new TalonFX(RobotMap.REAR_LEFT_STEER), new TalonFX(RobotMap.REAR_RIGHT_STEER), new TalonFX(RobotMap.SHOOTER_1), new TalonFX(RobotMap.SHOOTER_2), new TalonFX(RobotMap.CLIMBER_MOTOR_1)};
 
     /* An array of songs that are available to be playe`d, can you guess the song/artists? */
   private static String[] songs = new String[] {
     "imperialMarch.chrp",
   };

   /* track which song is selected for play */
   private static int songSelection = 0;

   /* overlapped actions */
   private static int timeToPlayLoops = 0;

    public static void LoadMusicSelection(int offset){
       /* increment song selection */
       songSelection += offset;
       /* wrap song index in case it exceeds boundary */
       if (songSelection >= songs.length) {
           songSelection = 0;
       }
       if (songSelection < 0) {
           songSelection = songs.length - 1;
       }
       /* load the chirp file */
       orchestra.loadMusic(songs[songSelection]); 

       /* print to console */
       System.out.println("Song selected is: " + songs[songSelection] + ".  Press left/right on d-pad to change.");
       
       /* schedule a play request, after a delay.  
           This gives the Orchestra service time to parse chirp file.
           If play() is called immedietely after, you may get an invalid action error code. */
       timeToPlayLoops = 5;
   }

   public static void InitMusicRobot(){
    /* A list of TalonFX's that are to be used as instruments */
    ArrayList<TalonFX> instruments = new ArrayList<TalonFX>();
      
    /* Initialize the TalonFX's to be used */
    for (int i = 0; i < fxes.length; i++) {
        instruments.add(fxes[i]);
    }
    /* Create the orchestra with the TalonFX instruments */
    orchestra = new Orchestra(instruments);
   }

   public static void InitMusicTeleop(){
       LoadMusicSelection(0);
   }

   public static void MusicTeleop(){
    if (timeToPlayLoops > 0) {
        --timeToPlayLoops;
        if (timeToPlayLoops == 0) {
            /* scheduled play request */
            System.out.println("Auto-playing song.");
            orchestra.play();
        }
        if(Robot.robotContainer.manip.getSTARTButtonPressed()){
            if (orchestra.isPlaying()) {
            orchestra.stop();
            System.out.println("Song stopped.");
         }  else {
            orchestra.play();
            System.out.println("Playing song...");
            }
        }
    }

    
   }
}
