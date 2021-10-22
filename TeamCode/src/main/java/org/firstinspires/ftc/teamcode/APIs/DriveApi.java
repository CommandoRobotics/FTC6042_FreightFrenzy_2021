package org.firstinspires.ftc.teamcode.APIs;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Constants.DrivetrainConstants;

public class DriveApi {

    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor rearLeft;
    DcMotor rearRight;
    LinearOpMode opMode;

    /**
     * Instantiate a new Drive API object
     * @param frontLeft The front left drive motor
     * @param frontRight The front right drive motor
     * @param rearLeft The rear left drive motor
     * @param rearRight The rear right drive motor
     * @param opMode This opmode object
     */
    public DriveApi(DcMotor frontLeft, DcMotor frontRight, DcMotor rearLeft, DcMotor rearRight, LinearOpMode opMode) {
        this.frontLeft = frontLeft;
        this.frontRight = frontRight;
        this.rearLeft = rearLeft;
        this.rearRight = rearRight;
        this.opMode = opMode;
        //TODO make sure we're reversing the correct motors
        frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        rearLeft.setDirection(DcMotorSimple.Direction.REVERSE);
        resetEncoders();
    }

    /**
     * Set the drive power of the left side
     * @param power The power to set the left side to
     */
    public void setLeftPower(double power) {
        frontLeft.setPower(power);
        rearLeft.setPower(power);
    }

    /**
     * Set the drive power of the right side
     * @param power The power to set the right side to
     */
    public void setRightPower(double power) {
        frontRight.setPower(power);
        rearRight.setPower(power);
    }

    /**
     * Drive the robot at the specified power
     * @param power The power at which to drive the robot
     */
    public void driveAtPower(double power) {
        setLeftPower(power);
        setRightPower(power);
    }

    /**
     * Drive the robot at the specified left and right power, respectively
     * @param leftPower The power for the left side
     * @param rightPower The power for the right side
     */
    public void driveAtPower(double leftPower, double rightPower) {
        setLeftPower(leftPower);
        setRightPower(rightPower);
    }

    /**
     * Drive the front left motor
     * @param power The power at which to drive the front left motor
     */
    public void driveFrontLeft(double power) {
        frontLeft.setPower(power);
    }

    /**
     * Drive the front right motor
     * @param power The power at which to drive the front right motor
     */
    public void driveFrontRight(double power) {
        frontRight.setPower(power);
    }

    /**
     * Drive the rear left motor
     * @param power The power at which to drive the rear left motor
     */
    public void driveRearLeft(double power) {
        rearLeft.setPower(power);
    }

    /**
     * Drive the rear right motor
     * @param power The power at which to drive the rear right motor
     */
    public void driveRearRight(double power) {
        rearRight.setPower(power);
    }

    /**
     * Convert inches to drive to ticks to drive
     * @param inches The number of inches to drive
     * @return The number of ticks to drive
     */
    private double inchesToTicks(double inches) {
        double rotations = inches/DrivetrainConstants.driveWheelCircumferenceInInches;
        return rotations*DrivetrainConstants.encoderTicksPerWheelRotation;
    }

    /**
     * Reset the encoders of our drivetrain
     */
    public void resetEncoders() {
        frontLeft.setMode(DcMotor.RunMode.RESET_ENCODERS);
        frontRight.setMode(DcMotor.RunMode.RESET_ENCODERS);
        rearLeft.setMode(DcMotor.RunMode.RESET_ENCODERS);
        rearRight.setMode(DcMotor.RunMode.RESET_ENCODERS);

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rearLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rearRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /**
     * Get the average of all of the drivetrain encoders
     * @return The average of all of the drivetrain encoders
     */
    public double getEncoderAverage() {
        double frontLeftEncoder = frontLeft.getCurrentPosition();
        double frontRightEncoder = frontRight.getCurrentPosition();
        double rearLeftEncoder = rearLeft.getCurrentPosition();
        double rearRightEncoder = rearRight.getCurrentPosition();
        return (frontLeftEncoder+frontRightEncoder+rearLeftEncoder+rearRightEncoder)/4;
    }

    /**
     * Drive forward the given number of inches using encoders
     * @param inches The number of inches to drive
     */
    public void driveForwardInches(double inches) {
        resetEncoders();
        double distanceToTravelInTicks = inchesToTicks(inches);
        while(opMode.opModeIsActive() && getEncoderAverage() < distanceToTravelInTicks) {
            driveAtPower(DrivetrainConstants.defaultPower);
        }
    }

    /**
     * Stop the drive motors
     */
    public void stopMotors() {
        driveAtPower(0);
    }

}
