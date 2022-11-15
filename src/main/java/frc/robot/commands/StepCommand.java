package frc.robot.commands;

import edu.wpi.first.util.datalog.DataLog;
import edu.wpi.first.util.datalog.DoubleLogEntry;
import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;

public class StepCommand extends CommandBase {
    DataLog log;
    DoubleLogEntry leftMotorVelocity;
    DoubleLogEntry leftStatorCurrent;
    DoubleLogEntry leftSupplyCurrent;

    DoubleLogEntry rightMotorVelocity;
    DoubleLogEntry rightStatorCurrent;
    DoubleLogEntry rightSupplyCurrent;
    
    DoubleLogEntry pigeonAccelerationX;
    DoubleLogEntry pigeonAccelerationY;
    DoubleLogEntry pigeonAccelerationZ;

    @Override
    public void initialize() {
        DataLogManager.start();
        log = DataLogManager.getLog();

        leftMotorVelocity = new DoubleLogEntry(log, "LeftMotorVelocity");
        leftStatorCurrent = new DoubleLogEntry(log, "LeftStatorCurrnet");
        leftSupplyCurrent = new DoubleLogEntry(log, "LeftSupplyCurrent");

        rightMotorVelocity = new DoubleLogEntry(log, "RightMotorVelocity");
        rightStatorCurrent = new DoubleLogEntry(log, "RightStatorCurrnet");
        rightSupplyCurrent = new DoubleLogEntry(log, "RightSupplyCurrent");

        pigeonAccelerationX = new DoubleLogEntry(log, "PigeonAccelerationX");
        pigeonAccelerationY = new DoubleLogEntry(log, "PigeonAccelerationY");
        pigeonAccelerationZ = new DoubleLogEntry(log, "PigeonAccelerationZ");


        DriveSubsystem.getInstance().set(1);
    }

    @Override
    public void execute() {
        leftMotorVelocity.append(DriveSubsystem.getInstance().getLeftMotorVelocity());
        leftStatorCurrent.append(DriveSubsystem.getInstance().getLeftStatorCurrent());
        leftSupplyCurrent.append(DriveSubsystem.getInstance().getLeftSupplyCurrent());

        rightMotorVelocity.append(DriveSubsystem.getInstance().getRightMotorVelocity());
        rightStatorCurrent.append(DriveSubsystem.getInstance().getRightStatorCurrent());
        rightSupplyCurrent.append(DriveSubsystem.getInstance().getRightSupplyCurrent());

        pigeonAccelerationX.append(DriveSubsystem.getInstance().getAccelX());
        pigeonAccelerationY.append(DriveSubsystem.getInstance().getAccelY());
        pigeonAccelerationZ.append(DriveSubsystem.getInstance().getAccelZ());
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
