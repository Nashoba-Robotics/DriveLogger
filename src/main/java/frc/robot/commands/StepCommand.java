package frc.robot.commands;

import edu.wpi.first.util.datalog.DataLog;
import edu.wpi.first.util.datalog.DoubleLogEntry;
import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class StepCommand extends CommandBase {
    DataLog log;
    DoubleLogEntry entry;

    @Override
    public void initialize() {
        DataLogManager.start();
        log = DataLogManager.getLog();
        entry = new DoubleLogEntry(log, "LeftMotorVelocity");

        DriveSubsystem.getInstance().set(1.);
    }

    @Override
    public void execute() {
        entry.append(DriveSubsystem.getInstance().getLeftMotorVelocity());
    }

    @Override
    public void end(boolean interrupted) {
        DriveSubsystem.getInstance().set(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
