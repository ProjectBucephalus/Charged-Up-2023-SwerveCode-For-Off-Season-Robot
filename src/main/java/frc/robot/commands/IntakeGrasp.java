package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class IntakeGrasp extends CommandBase{
    private final Intake m_intakeSubsystem;
    private double m_intakeSpeed;

    public IntakeGrasp(Intake intake, double speed) {
        this.m_intakeSubsystem = intake;
        this.m_intakeSpeed = speed;

        addRequirements(m_intakeSubsystem);
    }

    // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_intakeSubsystem.intake(m_intakeSpeed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }

}
