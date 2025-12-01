package Activities;

import static java.time.Duration.ofMillis;
import static org.openqa.selenium.interactions.PointerInput.MouseButton.LEFT;
import static org.openqa.selenium.interactions.PointerInput.Origin.viewport;

import java.time.Duration;
import java.util.Arrays;

import org.openqa.selenium.Point;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import io.appium.java_client.AppiumDriver;

public class ActionsBase {
	// Create a virtual finger input for touch gestures
	static PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

	/**
	 * Method to perform swipe action on the screen
	 *
	 * @param driver   Appium driver used to control the app
	 * @param start    Start point (x, y) of the swipe
	 * @param end      End point (x, y) of the swipe
	 * @param duration Time in milliseconds to complete the swipe
	 */
	public static void doSwipe(AppiumDriver driver, Point start, Point end, int duration) {
	    
	    // Create a swipe gesture sequence using touch actions
	    Sequence swipe = new Sequence(finger, 1)
	        // Move finger to the start point instantly
	        .addAction(finger.createPointerMove(ofMillis(0), viewport(), start.getX(), start.getY()))
	        // Press down (touch down) at the start point; LEFT below is 0
	        .addAction(finger.createPointerDown(LEFT.asArg()))
	        // Move finger to the end point over the specified duration
	        .addAction(finger.createPointerMove(ofMillis(duration), viewport(), end.getX(), end.getY()))
	        // Lift the finger up (end the swipe) LEFT below is 0
	        .addAction(finger.createPointerUp(LEFT.asArg()));
	    
	    // Perform the swipe gesture on the device
	    driver.perform(Arrays.asList(swipe));
	}
	
	// Create the long press action function
    public static void dolongpress(AppiumDriver driver, Point start) {
        // Create s sequence of actions
        Sequence longPress = new Sequence(finger, 1)
                .addAction(finger.createPointerMove(ofMillis(0), viewport(), start.getX(), start.getY()))
                .addAction(finger.createPointerDown(LEFT.asArg()))
                .addAction(new Pause(finger, Duration.ofMillis(400)))
                .addAction(finger.createPointerUp(LEFT.asArg()));

        // Perform the actions
        driver.perform(Arrays.asList(longPress));
    }


}
