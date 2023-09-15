package frc.robot.autos;

import frc.robot.Consatnts.Constants;
import frc.robot.autos.commands.IntakeAndStop;
import frc.robot.commands.*;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Swerve;

import java.util.HashMap;
import java.util.List;

import com.pathplanner.lib.PathConstraints;
import com.pathplanner.lib.PathPlanner;
import com.pathplanner.lib.PathPlannerTrajectory;
import com.pathplanner.lib.auto.SwerveAutoBuilder;
import com.pathplanner.lib.commands.FollowPathWithEvents;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.SwerveControllerCommand;
import edu.wpi.first.wpilibj2.command.WaitCommand;

public class exampleAuto extends SequentialCommandGroup {
    public exampleAuto(Swerve s_Swerve, Intake s_Intake){
        PathPlannerTrajectory First = PathPlanner.loadPath("Test Path for Swerve", new PathConstraints(2, 2));
        
        // TrajectoryConfig config =
        //     new TrajectoryConfig(
        //             Constants.AutoConstants.kMaxSpeedMetersPerSecond,
        //             Constants.AutoConstants.kMaxAccelerationMetersPerSecondSquared)
        //         .setKinematics(Constants.Swerve.swerveKinematics);

        // // An example trajectory to follow.  All units in meters.
        // Trajectory exampleTrajectory =
        //     TrajectoryGenerator.generateTrajectory(
        //         // Start at the origin facing the +X direction
        //         new Pose2d(0, 0, new Rotation2d(0)),
        //         // Pass through these two interior waypoints, making an 's' curve path
        //         List.of(new Translation2d(1, 1), new Translation2d(2, -1)),
        //         // End 3 meters straight ahead of where we started, facing forward
        //         new Pose2d(3, 0, new Rotation2d(0)),
        //         config);

        HashMap<String, Command> eventMap = new HashMap<>();
        eventMap.put("Intake", new IntakeGrasp(s_Intake, Constants.IntakeConstants.intakeMaxSpeed));

        var thetaController =
            new ProfiledPIDController(
                Constants.AutoConstants.kPThetaController, 0, 0, Constants.AutoConstants.kThetaControllerConstraints);
        thetaController.enableContinuousInput(-Math.PI, Math.PI);

        SwerveControllerCommand swerveControllerCommand1 =
            new SwerveControllerCommand(
                First,
                s_Swerve::getPose,
                Constants.Swerve.swerveKinematics,
                new PIDController(Constants.AutoConstants.kPXController, 0, 0),
                new PIDController(Constants.AutoConstants.kPYController, 0, 0),
                thetaController,
                s_Swerve::setModuleStates,
                s_Swerve);
        

         addCommands(
            new IntakeAndStop(s_Intake, Constants.IntakeConstants.intakeMaxSpeed, 200),
            new InstantCommand(() -> s_Swerve.resetOdometry(First.getInitialPose())),
            swerveControllerCommand1
         );        
        
    }
}