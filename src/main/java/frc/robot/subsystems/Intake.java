package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Consatnts.Constants;

public class Intake extends SubsystemBase {
    public TalonFX m_intakeMotor;

    public Intake () {
        m_intakeMotor = new TalonFX(Constants.IntakeConstants.intakeMotorID);
        configIntakeMotor();
    }

    public void intake(double speed) {
        setIntakeSpeed(speed);
    }

    public void eject(double speed) {
        setIntakeSpeed(speed);
    }

    public void stop() {
        setIntakeSpeed(0);
    }

    private void setIntakeSpeed(double speed) {
        m_intakeMotor.set(ControlMode.PercentOutput, speed);
      }

    private void configIntakeMotor(){        
        m_intakeMotor.configFactoryDefault();
        m_intakeMotor.setInverted(Constants.IntakeConstants.intakeMotorInvert);
        m_intakeMotor.setNeutralMode(Constants.IntakeConstants.intakeNeutralMode);
    }

    @Override
    public void periodic() {
        
    }
}
