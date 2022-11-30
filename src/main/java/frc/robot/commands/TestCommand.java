package frc.robot.commands;

import edu.wpi.first.util.sendable.SendableBuilder;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.NewDriveSubsystem;

public class TestCommand extends CommandBase{
    @Override
    public void initialize() {
        // TODO Auto-generated method stub
        super.initialize();
    }

    @Override
    public void execute() {
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

