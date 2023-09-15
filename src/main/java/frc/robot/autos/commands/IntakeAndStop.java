package frc.robot.autos.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import frc.robot.subsystems.Intake;

public class IntakeAndStop extends CommandBase{
    private final Intake m_intakeSubsystem;
    private double m_intakeSpeed;
    private double time = 0;

    public IntakeAndStop(Intake intake, double speed, double time) {
        this.m_intakeSubsystem = intake;
        this.m_intakeSpeed = speed;
        this.time = time;

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
    long currentTime = System.currentTimeMillis();

    while (System.currentTimeMillis() - currentTime < time){
        m_intakeSubsystem.intake(m_intakeSpeed);
    }
    m_intakeSubsystem.stop();
    return true;
  }

}

