package org.firstinspires.ftc.teamcode.OpModes.Teleop;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.APIs.AutonomousActions;
import org.firstinspires.ftc.teamcode.mechanisms.DeliveryWheel;
import org.firstinspires.ftc.teamcode.mechanisms.Drivetrain;

@TeleOp(name="Main Teleop")
public class MainTeleOp extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {

        // Instantiate and initialize our drivetrain
        Drivetrain drivetrain = new Drivetrain();
        drivetrain.init(this);
        DeliveryWheel deliveryWheel = new DeliveryWheel();
        deliveryWheel.init(this);
        AutonomousActions autonomousActions = new AutonomousActions();
        autonomousActions.init(this);

        // Let the driver know the robot is done initializing
        telemetry.addLine("Robot initialized");
        telemetry.update();

        // Wait for the driver to press play
        waitForStart();

        // Repeat while the opmode is still active
        while(opModeIsActive()) {
            // Get our power for each side based on the left and right stick values
            double leftTargetPower = gamepad1.left_stick_y;
            double rightTargetPower = gamepad1.right_stick_y;

            // Write our target powers to the telemetry
            telemetry.addLine("Left: " + leftTargetPower);
            telemetry.addLine("Right: " + rightTargetPower);
            telemetry.update();

            if(gamepad1.right_trigger > 0.2) {
                drivetrain.stopMotors();
                autonomousActions.deliverDuck();
            }
        }
    }
}
