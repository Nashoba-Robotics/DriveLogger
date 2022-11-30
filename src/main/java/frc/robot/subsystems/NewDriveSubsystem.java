package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class NewDriveSubsystem extends SubsystemBase{
    private static NewDriveSubsystem singleton;
    public static NewDriveSubsystem getInstance(){
        if(singleton == null) singleton = new NewDriveSubsystem();
        return singleton;
    }

    private TalonFX leftMotor, leftMotor2, leftMotor3;
    private TalonFX rightMotor, rightMotor2, rightMotor3;

    private PigeonIMU pigeon;

    private short[] acceleration;

    public NewDriveSubsystem(){
        leftMotor = new TalonFX(3, "Drive");
        leftMotor2 = new TalonFX(4, "Drive");
        leftMotor3 = new TalonFX(5, "Drive");

        leftMotor2.follow(leftMotor);
        leftMotor3.follow(leftMotor);

        leftMotor.setInverted(InvertType.InvertMotorOutput);
        leftMotor2.setInverted(InvertType.InvertMotorOutput);
        leftMotor3.setInverted(InvertType.InvertMotorOutput);

        rightMotor = new TalonFX(0, "Drive");
        rightMotor2 = new TalonFX(1, "Drive");
        rightMotor3 = new TalonFX(2, "Drive");

        rightMotor2.follow(rightMotor);
        rightMotor3.follow(rightMotor);

        rightMotor.setInverted(InvertType.None);
        rightMotor2.setInverted(InvertType.None);
        rightMotor3.setInverted(InvertType.None);

        pigeon = new PigeonIMU(0);

        acceleration = new short[3];
    }

    public void setLeftSpeed(double speed){
        leftMotor.set(ControlMode.PercentOutput, speed);
    }

    public void setRightSpeed(double speed){
        rightMotor.set(ControlMode.PercentOutput, speed);
    }

    public void set(double leftSpeed, double rightSpeed){
        setLeftSpeed(leftSpeed);
        setRightSpeed(rightSpeed);
    }

    public void set(double speed){
        set(speed, speed);
    }

    public double getLeftMotorVelocity(){
        return leftMotor.getSelectedSensorVelocity();
    }

    public double getLeftStatorCurrent(){
        return leftMotor.getStatorCurrent();
    }

    public double getLeftSupplyCurrent(){
        return leftMotor.getSupplyCurrent();
    }

    public double getRightMotorVelocity(){
        return rightMotor.getSelectedSensorVelocity();
    }

    public double getRightStatorCurrent(){
        return rightMotor.getStatorCurrent();
    }

    public double getRightSupplyCurrent(){
        return rightMotor.getSupplyCurrent();
    }

    public double getAccelX(){
        pigeon.getBiasedAccelerometer(acceleration);
        return acceleration[0];
    }

    public double getAccelY(){
        pigeon.getBiasedAccelerometer(acceleration);
        return acceleration[1];
    }

    public double getAccelZ(){
        pigeon.getBiasedAccelerometer(acceleration);
        return acceleration[2];
    }
}
