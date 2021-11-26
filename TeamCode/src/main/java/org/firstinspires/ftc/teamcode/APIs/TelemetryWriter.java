package org.firstinspires.ftc.teamcode.APIs;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.APIs.Gyroscope.GyroscopeApi;
import org.firstinspires.ftc.teamcode.mechanisms.Armdex;
import org.firstinspires.ftc.teamcode.mechanisms.DeliveryWheel;
import org.firstinspires.ftc.teamcode.mechanisms.Drivetrain;

public class TelemetryWriter {

    Telemetry telemetry;
    Drivetrain drivetrain;
    GyroscopeApi gyro;
    DeliveryWheel deliveryWheel;
    Armdex armdex;

    /**
     * Initialize the Debug object with a telemetry to write to
     * @param telemetry The telemetry to write to
     * @return This object
     */
    public TelemetryWriter init(Telemetry telemetry) {
        this.telemetry = telemetry;
        return this;
    }

    /**
     * Set the drivetrain for this debug object
     * @param drivetrain The drivetrain object
     * @return This object
     */
    public TelemetryWriter setDrivetrain(Drivetrain drivetrain) {
        this.drivetrain = drivetrain;
        return this;
    }

    /**
     * Set the gyroscope for this debug object
     * @param gyro The gyroscope API object
     * @return This object
     */
    public TelemetryWriter setGyroscope(GyroscopeApi gyro) {
        this.gyro = gyro;
        return this;
    }

    /**
     * Set the delivery wheel for this debug object
     * @param deliveryWheel The delivery wheel object
     * @return This object
     */
    public TelemetryWriter setDeliveryWheel(DeliveryWheel deliveryWheel) {
        this.deliveryWheel = deliveryWheel;
        return this;
    }

    public TelemetryWriter setArmdex(Armdex armdex) {
        this.armdex = armdex;
        return this;
    }

    /**
     * Add a line to the debug with certain contents
     * @param contents The contents to include on the line
     * @return This object
     */
    public TelemetryWriter addLine(String contents) {
        telemetry.addLine(contents);
        return this;
    }

    /**
     * Update the telemetry with the currently added content
     * @return This object
     */
    public TelemetryWriter update() {
        telemetry.update();
        return this;
    }

    /**
     * Write that the robot has been initialized to the telemetry
     */
    public void robotInitialized()  {
        addLine("Robot initialized");
        update();
    }

    /**
     * Add all of the gyroscope values to the telemetry
     * @return This object
     */
    public TelemetryWriter addGyroValues() {
        gyro.update();
        telemetry.addLine("X: " + gyro.getX() + " | Y: " + gyro.getY() + " | Z: " + gyro.getZ());
        return this;
    }

    /**
     * Add the value of the front left drive encoder to the telemetry
     * @return This object
     */
    public TelemetryWriter addFrontLeftDriveEncoder() {
        telemetry.addLine("FL Drive: " + drivetrain.getFrontLeftEncoderValue());
        return this;
    }

    /**
     * Add the value of the front right drive encoder to the telemetry
     * @return This object
     */
    public TelemetryWriter addFrontRightDriveEncoder() {
        telemetry.addLine("FR Drive: " + drivetrain.getFrontRightEncoderValue());
        return this;
    }

    /**
     * Add the value of the rear left drive encoder to the telemetry
     * @return This object
     */
    public TelemetryWriter addRearLeftDriveEncoder() {
        telemetry.addLine("RL Drive: " + drivetrain.getRearLeftEncoderValue());
        return this;
    }

    /**
     * Add the value of the rear right drive encoder to the telemetry
     * @return This object
     */
    public TelemetryWriter addRearRightDriveEncoder() {
        telemetry.addLine("RR Drive: " + drivetrain.getRearRightEncoderValue());
        return this;
    }

    /**
     * Add all of the drive encoder values to the telemetry
     * @return This object
     */
    public TelemetryWriter addAllDriveEncoders() {
        telemetry.addLine("FL: " + drivetrain.getFrontLeftEncoderValue() + " | FR: " + drivetrain.getFrontRightEncoderValue());
        telemetry.addLine("RL: " + drivetrain.getRearLeftEncoderValue() + " | RR: " + drivetrain.getRearRightEncoderValue());
        return this;
    }

    /**
     * Add the left delivery wheel encoder to the telemetry
     * @return This object
     */
    public TelemetryWriter addLeftDeliveryEncoder() {
        telemetry.addLine("Left Delivery: " + deliveryWheel.getLeftPosition());
        return this;
    }

    /**
     * Add the right delivery wheel encoder to the telemetry
     * @return This object
     */
    public TelemetryWriter addRightDeliveryEncoder() {
        telemetry.addLine("Right Delivery: " + deliveryWheel.getRightPosition());
        return this;
    }

    /**
     * Add both delivery wheel encoders to the telemetry
     * @return This object
     */
    public TelemetryWriter addDeliveryEncoders() {
        telemetry.addLine("Delivery | Left: " + deliveryWheel.getLeftPosition() + " | Right: " + deliveryWheel.getRightPosition());
        return this;
    }

    /**
     * Add the intake encoder to the telemetry
     * @return This object
     */
    public TelemetryWriter addIntakeEncoder() {
        telemetry.addLine("Intake: " + armdex.getIntakePosition());
        return this;
    }

    /**
     * Add the wrist encoder to the telemetry
     * @return This object
     */
    public TelemetryWriter addWristEncoder() {
        telemetry.addLine("Wrist: " + armdex.getWristPosition());
        return this;
    }

    /**
     * Add the intake color sensor RGB values to the telemetry
     * @return This object
     */
    public TelemetryWriter addIntakeSensorValues() {
        telemetry.addLine("Intake Sensor R: " + armdex.getIntakeSensorRed());
        telemetry.addLine("Intake Sensor G: " + armdex.getIntakeSensorGreen());
        telemetry.addLine("Intake Sensor B: " + armdex.getIntakeSensorBlue());
        return this;
    }

    /**
     * Add the intake color sensor red value to the telemetry
     * @return This object
     */
    public TelemetryWriter addIntakeSensorRed() {
        telemetry.addLine("Intake Sensor R: " + armdex.getIntakeSensorRed());
        return this;
    }

    /**
     * Add the intake color sensor green value to the telemetry
     * @return This object
     */
    public TelemetryWriter addIntakeSensorGreen() {
        telemetry.addLine("Intake Sensor G: " + armdex.getIntakeSensorGreen());
        return this;
    }

    /**
     * Add the intake color sensor blue value to the telemetry
     * @return This object
     */
    public TelemetryWriter addIntakeSensorBlue() {
        telemetry.addLine("Intake Sensor B: " + armdex.getIntakeSensorBlue());
        return this;
    }

    /**
     * Add whether freight is being detected in the intake to the telemetry
     * @return
     */
    public TelemetryWriter addFreightDetected() {
        if(armdex.isObjectDetectedInIntake()) {
            telemetry.addLine("Freight Detected");
        } else {
            telemetry.addLine("Freight Not Detected");
        }
        return this;
    }

    /**
     * Add whether the up limit switch is being triggered to the telemetry
     * @return This object
     */
    public TelemetryWriter addUpLimitSwitch() {
        if(armdex.isWristUp()) {
            telemetry.addLine("Wrist Up Sensor: Active");
        } else {
            telemetry.addLine("Wrist Up Sensor: Not Active");
        }
        return this;
    }

    /**
     * Add whether the down limit switch is being triggered to the telemetry
     * @return This object
     */
    public TelemetryWriter addDownLimitSwitch() {
        if(armdex.isWristDown()) {
            telemetry.addLine("Wrist Down Sensor: Active");
        } else {
            telemetry.addLine("Wrist Down Sensor: Not Active");
        }
        return this;
    }

}
