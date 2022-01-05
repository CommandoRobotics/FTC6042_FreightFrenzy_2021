package org.firstinspires.ftc.teamcode.OpModes.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.APIs.AutonomousActions;
import org.firstinspires.ftc.teamcode.APIs.Leds.LedController;
import org.firstinspires.ftc.teamcode.APIs.TelemetryWriter;
import org.firstinspires.ftc.teamcode.mechanisms.Armdex;
import org.firstinspires.ftc.teamcode.mechanisms.Drivetrain;
import org.firstinspires.ftc.teamcode.mechanisms.Placer;

@Autonomous(name="Blue RDeDS")
public class BlueRightDetectDeliverStorage extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {

        Drivetrain drivetrain = new Drivetrain(this);
        AutonomousActions actions = new AutonomousActions(this);
        TelemetryWriter output = new TelemetryWriter(telemetry);
        Armdex armdex = new Armdex(this);
        Placer placer = new Placer(this);
        LedController led = new LedController(this, 'b');

        output.robotInitialized();
        waitForStart();

        placer.armStraightUp();
        actions.delay(100);
        armdex.wristUp();

        actions.placeFreightFromRightAndReturn();
        led.setColorBlue();

        actions.rotateDegrees(88);
        actions.delay(500);

        // Drive to delivery wheel
        drivetrain.driveForwardInchesNoPid(5, 0.5);
        long startTimeInMillis = System.currentTimeMillis();
        while(opModeIsActive() && (System.currentTimeMillis() < startTimeInMillis+1600)) {
            drivetrain.driveAtPower(0.2);
        }
        drivetrain.stopMotors();

        // Deliver duck
        actions.deliverDuckAutonomous();

        // Drive backwards
        actions.driveInchesNoPid(-10, 0.5);
        actions.delay(500);

        // Rotate left
        actions.rotateDegrees(-90);
        actions.delay(500);

        // Drive forward
        actions.driveInchesNoPid(12, 0.5);
        actions.delay(500);

        // Rotate right
        actions.rotateDegrees(90);
        actions.delay(500);

        // Park in zone
        actions.driveInchesNoPid(16, 0.5);

    }
}
