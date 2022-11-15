package frc.robot.commands;

import edu.wpi.first.util.datalog.DataLog;
import edu.wpi.first.util.datalog.DoubleLogEntry;
import edu.wpi.first.wpilibj.DataLogManager;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class LogTestCommand extends CommandBase{
    DataLog log;
    DoubleLogEntry dEntry;

    @Override
    public void initialize(){
        DataLogManager.start();

        log = DataLogManager.getLog();
        dEntry = new DoubleLogEntry(log, "Double");
    }

    @Override
    public void execute() {
        dEntry.append(0);
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}
