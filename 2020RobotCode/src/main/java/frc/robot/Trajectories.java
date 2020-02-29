package frc.robot;

import java.util.Arrays;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import frc.libs.util.Limelight;

public class Trajectories {
        static TrajectoryConfig config = new TrajectoryConfig(Constants.MAX_DRIVE_VELOCITY,
                        Constants.MAX_DRIVE_ACCELERATION);

        static Pose2d[] targetPath;

        public static Trajectory getTargetTrajectory() {
                targetPath = new Pose2d[] { new Pose2d(), new Pose2d(
                                Robot.drivetrain.currentPose2d.getTranslation().getX() - Constants.TARGET_X,
                                Math.sqrt(Math.pow(Limelight.distanceToTarget(), 2)
                                                - Math.pow(Robot.drivetrain.currentPose2d.getTranslation().getX()
                                                                - Constants.TARGET_X, 2)),
                                new Rotation2d(Limelight.getTargetRotation())) };
                return TrajectoryGenerator.generateTrajectory(Arrays.asList(targetPath), config);
        }
        public static Trajectory simplePath(){
                targetPath = new Pose2d[]{ new Pose2d(), new Pose2d(0, 5, new Rotation2d())};
                return TrajectoryGenerator.generateTrajectory(Arrays.asList(targetPath), config);
        }
}