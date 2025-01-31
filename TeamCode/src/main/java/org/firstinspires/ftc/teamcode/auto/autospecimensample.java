
package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.Arm;
import org.firstinspires.ftc.teamcode.hardware.Distance;
import org.firstinspires.ftc.teamcode.hardware.DistanceBack;
import org.firstinspires.ftc.teamcode.hardware.Grabber;
import org.firstinspires.ftc.teamcode.hardware.Rotator;
import org.firstinspires.ftc.teamcode.hardware.Slide;

@Autonomous(name = "auto specimen no parking", group = "Wallace")
//@Disabled
public class autospecimensample extends LinearOpMode {

    private final ElapsedTime runtime = new ElapsedTime();
    boolean skip_opencv = false;
    /* Declare OpMode members. */
    private DcMotor leftFrontDrive = null;
    private DcMotor rightFrontDrive = null;
    private DcMotor leftBackDrive = null;
    private DcMotor rightBackDrive = null;
    //    IntegratingGyroscope gyro;
    //NavxMicroNavigationSensor navxMicro;
    static final double COUNTS_PER_MOTOR_REV = 28;    // eg: REV Motor Encoder
    static final double DRIVE_GEAR_REDUCTION = 20.0;     // 4x and 5x gear boxes.
    static final double WHEEL_DIAMETER_INCHES = 3.8;     // For figuring circumference
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * Math.PI)*.85;
    static final double DRIVE_SPEED = -0.3;
    static final double TURN_SPEED = -0.2;


    final double MAX_AUTO_SPEED = 0.5;   //  Clip the approach speed to this max value (adjust for your robot)
    final double MAX_AUTO_TURN = 0.25;  //  Clip the turn speed to this max value (adjust for your robot)
    static final double MAX_POS = 0.15;     // Maximum rotational position
    static final double MIN_POS = 0.5;     // Minimum rotational position

    //int DESIRED_TAG_ID = 5;    // Choose the tag you want to approach or set to -1 for ANY tag.
    Arm arm = new Arm(this);
    Rotator rotator = new Rotator(this);
    Grabber grabber = new Grabber(this);
    Slide slide = new Slide(this);
    private Distance distance = new Distance(this);
    private DistanceBack distance_back = new DistanceBack(this);

    @Override
    public void runOpMode() {

        distance.init();
        distance_back.init();
       arm.init();
        slide.init();
       grabber.init();
       rotator.init();
       //	sleep(500);
	//	rotator.initpos();
	grabber.grab();
       leftFrontDrive = hardwareMap.get(DcMotor.class, "frontleft");
        leftBackDrive = hardwareMap.get(DcMotor.class, "backleft");
        rightFrontDrive = hardwareMap.get(DcMotor.class, "frontright");
        rightBackDrive = hardwareMap.get(DcMotor.class, "backright");

        // navxMicro = hardwareMap.get(NavxMicroNavigationSensor.class, "navx");
        // gyro = (IntegratingGyroscope)navxMicro;

        // To drive forward, most robots need the motor on one side to be reversed, because the axles point in opposite directions.
        // When run, this OpMode should start both motors driving forward. So adjust these two lines based on your first test drive.
        // Note: The settings here assume direct drive on left and right wheels.  Gear Reduction or 90 Deg drives may require direction flips
        leftFrontDrive.setDirection(DcMotor.Direction.FORWARD);
        leftBackDrive.setDirection(DcMotor.Direction.FORWARD);
        rightFrontDrive.setDirection(DcMotor.Direction.REVERSE);
        rightBackDrive.setDirection(DcMotor.Direction.REVERSE);

        // leftFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        // leftBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        // rightFrontDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        // rightBackDrive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        leftFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        // leftFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // rightFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // leftBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        // rightBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        leftFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightFrontDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        leftBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        rightBackDrive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Starting at", "%7d :%7d",
                leftFrontDrive.getCurrentPosition(),
                rightFrontDrive.getCurrentPosition());
        //BlueFinder.Selected selected;
        // here is what happens after we hit start
	arm.move(0.1);
        while (!isStarted() && !isStopRequested()) {
        }
	rotator.setposition(0.45);
	//	    moveRobot_forward(DRIVE_SPEED+0.1,0,5);
	//arm.Float();
	    arm.MoveTo(300,0.7);

	//	left_turn(92);
	//arm.Brake();
            // while (!gamepad1.a) {
            //     sleep(1);
            // }
	    slide.MoveTo(1300,0.7);

            leftFrontDrive.setPower((-DRIVE_SPEED));
            rightFrontDrive.setPower((-DRIVE_SPEED));
            leftBackDrive.setPower((-DRIVE_SPEED));
            rightBackDrive.setPower((-DRIVE_SPEED));
	    telemetry.addData(">", "running straight");
	    //            while (distance.getDistanceMM() > 120) {
	telemetry.update();
            while (distance.getDistanceMM() > 180) {
                sleep(1);
            }
            leftFrontDrive.setPower((0));
            rightFrontDrive.setPower((0));
            leftBackDrive.setPower((0));
            rightBackDrive.setPower((0));
	telemetry.addData("slide position: ", "%10d", slide.getCurrentPosition());
	//	slidebusy.run();
	telemetry.addData("arm position: ", "%10d", arm.getCurrentPosition());
	telemetry.update();
	//	    	grabber.release();
             // while (!gamepad1.a) {
             //     sleep(1);
             // }

	telemetry.addData("slide power after a: ", "%5.2f", slide.getPower());
        telemetry.update();
	sleep(1000);
	    long startpos = slide.getCurrentPosition();
	slide.MoveTo(750);
	while(slide.isBusy())
	    {
		sleep(1);
	    }
	sleep(500);
	grabber.release();
	//	            encoderDrive(DRIVE_SPEED, 12, 12, 5.0);
	moveRobot_backward(DRIVE_SPEED,0,5);
	slide.MoveTo(60);
	//	slide.move(-0.07);
	arm.MoveTo(700);
rotator.setposition(0.45);
             while (!gamepad1.a) {
                  sleep(1);
              }

    }
    /*
     *  Method to perform a relative move, based on encoder counts.
     *  Encoders are not reset as the move is based on the current position.
     *  Move will stop if any of three conditions occur:
     *  1) Move gets to the desired position
     *  2) Move runs out of time
     *  3) Driver stops the opmode running.
     */
    public void encoderDrive(double speed,
                             double leftInches, double rightInches,
                             double timeoutS) {
        int newLeftFrontTarget;
        int newRightFrontTarget;
        int newLeftBackTarget;
        int newRightBackTarget;
        telemetry.addData("lfin", "%.0f %.0f / %.0f", speed, leftInches, rightInches);
        telemetry.update();
        //   sleep(10000);  // pause to display final telemetry message.

        // Ensure that the opmode is still active
        if (opModeIsActive()) {

            // Determine new target position, and pass to motor controller
            newLeftFrontTarget = leftFrontDrive.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newRightFrontTarget = rightFrontDrive.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            newLeftBackTarget = leftBackDrive.getCurrentPosition() + (int) (leftInches * COUNTS_PER_INCH);
            newRightBackTarget = rightBackDrive.getCurrentPosition() + (int) (rightInches * COUNTS_PER_INCH);
            leftFrontDrive.setTargetPosition(newLeftFrontTarget);
            rightFrontDrive.setTargetPosition(newRightFrontTarget);
            leftBackDrive.setTargetPosition(newLeftBackTarget);
            rightBackDrive.setTargetPosition(newRightBackTarget);

            // Turn On RUN_TO_POSITION
            leftFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightFrontDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            leftBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            rightBackDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

            // reset the timeout time and start motion.
            runtime.reset();
            leftFrontDrive.setPower((speed));
            rightFrontDrive.setPower((speed));
            leftBackDrive.setPower((speed));
            rightBackDrive.setPower((speed));

            // keep looping while we are still active, and there is time left, and both motors are running.
            // Note: We use (isBusy() && isBusy()) in the loop test, which means that when EITHER motor hits
            // its target position, the motion will stop.  This is "safer" in the event that the robot will
            // always end the motion as soon as possible.
            // However, if you require that BOTH motors have finished their moves before the robot continues
            // onto the next step, use (isBusy() || isBusy()) in the loop test.
            while (opModeIsActive() &&
                    (runtime.seconds() < timeoutS) &&
                    (leftFrontDrive.isBusy() && rightFrontDrive.isBusy())) {

                // Display it for the driver.
                telemetry.addData("Running to", " %7d :%7d", newLeftFrontTarget, newRightFrontTarget);
                telemetry.addData("Currently at", " at %7d :%7d",
                        leftFrontDrive.getCurrentPosition(), rightFrontDrive.getCurrentPosition());
                telemetry.update();
            }

            // Stop all motion;
            leftFrontDrive.setPower(0);
            rightFrontDrive.setPower(0);
            leftBackDrive.setPower(0);
            rightBackDrive.setPower(0);

            // Turn off RUN_TO_POSITION
            leftFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            leftBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            rightBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

            // sleep(250);   // optional pause after each move.
        }
    }


    public void moveRobot_forward(double x, double yaw, double distance) {
        // Calculate left and right wheel powers.
        double rightPower = -(x - yaw);
        double leftPower = -(x + yaw);

        // Normalize wheel powers to be less than 1.0
        double max = Math.max(Math.abs(leftPower), Math.abs(rightPower));

        if (max > 1.0) {
            leftPower /= max;
            rightPower /= max;
        }
        int currpos = leftFrontDrive.getCurrentPosition();
        Double newData = new Double(distance * COUNTS_PER_INCH);
        int gohere = currpos + (newData.intValue());

        telemetry.addData("move robot", "%d, %.1f, %.1f, %d", gohere, distance, COUNTS_PER_INCH, currpos);
        telemetry.update();
        // while (!gamepad1.a) {
        //     sleep(1);
        // }
        // Send powers to the wheels.
        leftFrontDrive.setPower(leftPower);
        leftBackDrive.setPower(leftPower);
        rightFrontDrive.setPower(rightPower);
        rightBackDrive.setPower(rightPower);

        while ((currpos = leftFrontDrive.getCurrentPosition()) < gohere) {
        telemetry.addData("pos robot", "%d, %.1f, %.1f, %d", gohere, distance, COUNTS_PER_INCH, currpos);
        telemetry.update();
            sleep(1);
        }
        leftFrontDrive.setPower(0);
        leftBackDrive.setPower(0);
        rightFrontDrive.setPower(0);
        rightBackDrive.setPower(0);

    }

        public void moveRobot_backward(double x, double yaw, double distance) {
        // Calculate left and right wheel powers.
	    double rightPower = -(-(x - yaw));
	    double leftPower = -(-(x + yaw));

        // Normalize wheel powers to be less than 1.0
        double max = Math.max(Math.abs(leftPower), Math.abs(rightPower));

        if (max > 1.0) {
            leftPower /= max;
            rightPower /= max;
        }
        int currpos = leftFrontDrive.getCurrentPosition();
        Double newData = new Double(distance * COUNTS_PER_INCH);
        int gohere = currpos - (newData.intValue());

        telemetry.addData("move robot", "%d, %.1f, %.1f, %d", gohere, distance, COUNTS_PER_INCH, currpos);
        telemetry.update();
        // while (!gamepad1.a) {
        //     sleep(1);
        // }
        // Send powers to the wheels.
        leftFrontDrive.setPower(leftPower);
        leftBackDrive.setPower(leftPower);
        rightFrontDrive.setPower(rightPower);
        rightBackDrive.setPower(rightPower);

        while ((currpos = leftFrontDrive.getCurrentPosition()) > gohere) {
        telemetry.addData("pos robot", "%d, %.1f, %.1f, %d", gohere, distance, COUNTS_PER_INCH, currpos);
        telemetry.update();
            sleep(1);
        }
        leftFrontDrive.setPower(0);
        leftBackDrive.setPower(0);
        rightFrontDrive.setPower(0);
        rightBackDrive.setPower(0);

    }


    void right_turn_counter(long ticks) {
	long toposition = leftFrontDrive.getCurrentPosition() + ticks;
        leftFrontDrive.setPower(-TURN_SPEED);
        rightFrontDrive.setPower(TURN_SPEED);
        leftBackDrive.setPower(-TURN_SPEED);
        rightBackDrive.setPower(TURN_SPEED);
	while(	leftFrontDrive.getCurrentPosition() < toposition)
	    {
		sleep(1);
	    }
        leftFrontDrive.setPower(0);
        rightFrontDrive.setPower(0);
        leftBackDrive.setPower(0);
        rightBackDrive.setPower(0);
	return;
    }

}
