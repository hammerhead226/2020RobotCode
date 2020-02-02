package frc.libs.util;

import java.util.Arrays;

import edu.wpi.first.wpilibj.geometry.Pose2d;
import edu.wpi.first.wpilibj.geometry.Rotation2d;
import edu.wpi.first.wpilibj.trajectory.Trajectory;
import edu.wpi.first.wpilibj.trajectory.TrajectoryConfig;
import edu.wpi.first.wpilibj.trajectory.TrajectoryGenerator;

public class Trajectories {
    Pose2d[] simplePath = new Pose2d[] { new Pose2d(), new Pose2d(Limelight.getHorizontalOffset(), 0, new Rotation2d(Limelight.getSkew())) };
    static TrajectoryConfig config = new TrajectoryConfig(1, 0.5);

    public Trajectory getSimpleTrajectory() {
       return TrajectoryGenerator.generateTrajectory(Arrays.asList(simplePath), config);
    }
}