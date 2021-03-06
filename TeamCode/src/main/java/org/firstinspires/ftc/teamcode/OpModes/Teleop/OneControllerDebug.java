package org.firstinspires.ftc.teamcode.OpModes.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.APIs.Leds.LedController;
import org.firstinspires.ftc.teamcode.APIs.TelemetryWriter;
import org.firstinspires.ftc.teamcode.mechanisms.Armdex;
import org.firstinspires.ftc.teamcode.mechanisms.DeliveryWheel;
import org.firstinspires.ftc.teamcode.mechanisms.Drivetrain;
import org.firstinspires.ftc.teamcode.mechanisms.Placer;

@TeleOp(name="Debug/Test (One Controller)")
public class OneControllerDebug extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        final double NORMAL_DRIVE_MAX_POWER = 0.5;
        final double OVERDRIVE_MAX_POWER = 1;

        Drivetrain drivetrain = new Drivetrain(this);
        Armdex armdex = new Armdex(this);
        DeliveryWheel deliveryWheel = new DeliveryWheel(this);
        LedController led = new LedController(this);
        Placer placer = new Placer(this);
        TelemetryWriter output =  new TelemetryWriter(telemetry);
        output.setDrivetrain(drivetrain).setArmdex(armdex).setDeliveryWheel(deliveryWheel);

        output.robotInitialized();
        waitForStart();

        boolean overrideIntake = false;
        placer.closeHand();
        placer.armStraightUp();
        armdex.wristUp();
        boolean isWristSupposedToBeUp = true;


        while(opModeIsActive()) {

            double drivePowerMultiplier = NORMAL_DRIVE_MAX_POWER;
            // Check if driver is using overdrive mode
            if(gamepad1.right_bumper) {
                drivePowerMultiplier = OVERDRIVE_MAX_POWER;
                output.addLine("OVERDRIVE ENABLED");
            }

            // If we detect a block, write it to the telemetry
            if(armdex.isObjectDetectedInIntake()) {
                output.addFreightDetected();
            }

            // Write our wrist sensor info to the telemetry
            output.addIsWristUp().addIsWristDown().addWristSensorValues();

            // Write our intake sensor values to the telemetry
            output.addIntakeSensorValues();

            // Calculate our target drivetrain powers
            double leftTargetPower = -drivePowerMultiplier*gamepad1.left_stick_y;
            double rightTargetPower = -drivePowerMultiplier*gamepad1.right_stick_y;

            // Write our target powers to the telemetry
            output.addLine("Left Target Power: " + leftTargetPower).addLine("Right Target Power: " + rightTargetPower);

            // Drive at the target power
            drivetrain.driveAtPower(leftTargetPower, rightTargetPower);

            // Control the delivery mechanism
            if(gamepad1.a) {
                deliveryWheel.setPower(0.6);
            } else if(gamepad1.b) {
                deliveryWheel.setPower(-0.5);
            } else {
                deliveryWheel.stop();
            }

            // Control the intake
            boolean isRightTriggerPressed = gamepad1.right_trigger > 0.2;
            boolean isBlockPresentInIntake = armdex.isObjectDetectedInIntake();

            if(!isRightTriggerPressed) {
                if(isBlockPresentInIntake) {
                    overrideIntake = true;
                } else {
                    overrideIntake = false;
                }
            }
            if(gamepad1.left_trigger > 0.2) {
                armdex.eject();
            } else if(isRightTriggerPressed) {
                if(!overrideIntake) {
                    if(!isBlockPresentInIntake) {
                        if(!armdex.isWristUp()) {
                            armdex.intake();
                        } else {
                            armdex.stopIntake();
                        }
                    } else {
                        armdex.stopIntake();
                        armdex.wristUp();
                        isWristSupposedToBeUp = true;
                    }
                } else {
                    if(armdex.isWristUp()) {
                        armdex.place();
                    } else {
                        armdex.intake();
                    }
                }
            } else {
                armdex.stopIntake();
            }

            // Operate the wrist
            if(gamepad1.dpad_up) {
                armdex.wristUp();
                isWristSupposedToBeUp = true;
            } else if(gamepad1.dpad_down) {
                armdex.wristDown();
                isWristSupposedToBeUp = false;
            }

            // Update the telemetry
            output.update();
        }

    }
}
