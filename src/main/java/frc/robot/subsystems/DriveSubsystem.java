package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonFX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase{
    private TalonFX left1, left2, left3, right1, right2, right3;
    private static DriveSubsystem instance;
    private PigeonIMU pigeon;
    double[] speedxyz;
    short[] acceleration;

    public static DriveSubsystem getInstance() {
        if (instance == null) instance = new DriveSubsystem();
        return instance;
    }

    public DriveSubsystem() {
        left1 = new TalonFX(0);
        left2 = new TalonFX(1);
        left3 = new TalonFX(2);
        right1 = new TalonFX(3);
        right2 = new TalonFX(4);
        right3 = new TalonFX(5);

        left2.follow(left1);
        left3.follow(left1);
        right2.follow(right1);
        right3.follow(right1);

        right1.setInverted(InvertType.InvertMotorOutput);
        right2.setInverted(InvertType.FollowMaster);
        right3.setInverted(InvertType.FollowMaster);
        
        left1.setInverted(InvertType.None);
        left2.setInverted(InvertType.FollowMaster);
        left3.setInverted(InvertType.FollowMaster);

        pigeon = new PigeonIMU(0);

        left1.setNeutralMode(NeutralMode.Coast);
        right1.setNeutralMode(NeutralMode.Coast);

        acceleration = new short[3];
        speedxyz = new double[3];
    }

    public void setLeft(double speed) {
        left1.set(ControlMode.PercentOutput, speed);
    }
    
    public void setRight(double speed) {
        right1.set(ControlMode.PercentOutput, speed);
    }

    public void set(double leftSpeed, double rightSpeed) {
        setLeft(leftSpeed);
        setRight(rightSpeed);
    }
    
    public void set(double speed) {
        set(speed, speed);
    }

    public double getLeftMotorVelocity(){
        return left1.getSelectedSensorVelocity();
    }

    public double getLeftStatorCurrent(){
        return left1.getStatorCurrent();
    }

    public double getLeftSupplyCurrent(){
        return left1.getSupplyCurrent();
    }

    public double getRightMotorVelocity(){
        return right1.getSelectedSensorVelocity();
    }

    public double getRightStatorCurrent(){
        return right1.getStatorCurrent();
    }

    public double getRightSupplyCurrent(){
        return right1.getSupplyCurrent();
    }

    public double getSpeedX(){
        pigeon.getRawGyro(speedxyz);
        return speedxyz[0];
    }

    public double getSpeedY(){
        pigeon.getRawGyro(speedxyz);
        return speedxyz[1];
    }

    public double getSpeedZ(){
        pigeon.getRawGyro(speedxyz);
        return speedxyz[2];
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
