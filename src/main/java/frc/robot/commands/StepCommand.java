package frc.robot.commands;

import edu.wpi.first.util.datalog.DataLog;
import edu.wpi.first.util.datalog.DoubleLogEntry;
import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.NewDriveSubsystem;

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

    public StepCommand(){
        addRequirements(NewDriveSubsystem.getInstance());
    }

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
    }

    //Getting values from old Drive Subsystem -> CHANGE
    @Override
    public void execute() {
        leftMotorVelocity.append(NewDriveSubsystem.getInstance().getLeftMotorVelocity());
        leftStatorCurrent.append(NewDriveSubsystem.getInstance().getLeftStatorCurrent());
        leftSupplyCurrent.append(NewDriveSubsystem.getInstance().getLeftSupplyCurrent());

        rightMotorVelocity.append(NewDriveSubsystem.getInstance().getRightMotorVelocity());
        rightStatorCurrent.append(NewDriveSubsystem.getInstance().getRightStatorCurrent());
        rightSupplyCurrent.append(NewDriveSubsystem.getInstance().getRightSupplyCurrent());

        pigeonAccelerationX.append(NewDriveSubsystem.getInstance().getAccelX());
        pigeonAccelerationY.append(NewDriveSubsystem.getInstance().getAccelY());
        pigeonAccelerationZ.append(NewDriveSubsystem.getInstance().getAccelZ());
        NewDriveSubsystem.getInstance().set(0.3);
    }

    @Override
    public void end(boolean interrupted) {
        NewDriveSubsystem.getInstance().set(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
