package Examples;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.options.XCUITestOptions;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

public class First {
    // Driver Declaration
    AndroidDriver driver;
    IOSDriver iosdriver;

    // Set up method
    @BeforeClass
    public void setUp() throws MalformedURLException, URISyntaxException {
    	
    	File apkFile = new File("src/test/resources/Calculator.apk");
    	
        // Step 1: Create an object to define desired capabilities for the Android device
        UiAutomator2Options options = new UiAutomator2Options();

        // Step 2: Set the platform name (Android)
        options.setPlatformName("android");

        // Step 3: Set the automation engine (UiAutomator2 is the most commonly used for Android)
        options.setAutomationName("UiAutomator2");

        // Step 4: Set the path to the app (APK) that you want to test
        options.setApp(apkFile.getAbsolutePath());

        // Step 5: Do not reset app data between sessions (useful for faster tests)
        options.noReset();

        // Step 6: Set the Appium server URL (Appium must be running on this address/port)
        URL serverURL = new URI("http://localhost:4723").toURL();

        // Step 7: Initialize the Android driver with the server URL and capabilities
        driver = new AndroidDriver(serverURL, options);
    }
    
//    //ios Setup
//    @BeforeClass
//    public void iossetUp() throws MalformedURLException, URISyntaxException {
//    	
//        // Step 1: Create an object to define desired capabilities for the Apple device
//        XCUITestOptions options = new XCUITestOptions();
//
//        // Step 2: Set the platform name (ios)
//        options.setPlatformName("ios");
//
//        // Step 3: Set the automation engine (XCUITest is the most commonly used for Android)
//        options.setAutomationName("XCUITest");
//
//        // Step 4: Set the path to the app (APK) that you want to test
//        options.setApp("src/test/resources/Calculator.ipa");
//
//        // Step 5: Do not reset app data between sessions (useful for faster tests)
//        options.noReset();
//
//        // Step 6: Set the Appium server URL (Appium must be running on this address/port)
//        URL serverURL = new URI("http://localhost:4723").toURL();
//
//        // Step 7: Initialize the ios driver with the server URL and capabilities
//        iosdriver = new IOSDriver(serverURL, options);
//    }
//
//
    // Test method
    @Test
    public void multiplyTest() {

    }


    // Tear down method
    @AfterClass
    public void tearDown() {
        // Close the app
        driver.quit();
    }
}