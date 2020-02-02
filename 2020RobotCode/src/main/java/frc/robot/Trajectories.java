package frc.robot;

import java.util.Arrays;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;
import frc.libs.util.Limelight;

public class Trajectories {
    static TrajectoryConfig config = new TrajectoryConfig(Constants.MAX_DRIVE_VELOCITY, Constants.MAX_DRIVE_ACCELERATION);
    
    Pose2d[] targetPath;

    public Trajectory getTargetTrajectory() {
        targetPath = new Pose2d[] { new Pose2d(), new Pose2d(Limelight.getHorizontalOffset(), Limelight.distanceToTarget(), new Rotation2d(Limelight.getSkew())) };
        return TrajectoryGenerator.generateTrajectory(Arrays.asList(targetPath), config);
    }
}