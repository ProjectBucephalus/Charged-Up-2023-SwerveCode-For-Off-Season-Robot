package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class IntakeEject extends CommandBase{
    private final Intake m_intakeSubsystem;
    private double m_ejectSpeed;

    public IntakeEject(Intake intake, double speed) {
        this.m_intakeSubsystem = intake;
        this.m_ejectSpeed = speed;

        addRequirements(m_intakeSubsystem);
    }

    // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_intakeSubsystem.eject(m_ejectSpeed);
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
