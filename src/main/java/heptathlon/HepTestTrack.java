package heptathlon;

import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.fail;

public class HepTestTrack {

    Hep100MHurdles hep100MHurdles = new Hep100MHurdles();
    Hep200M hep200M = new Hep200M();
    Hep800M hep800M = new Hep800M();

    @Test(timeOut = 2000)
    public void testScoreHep100MHurdles() {
        try {
            // Score = INT(A(B-P)C)
            // P = distance A = 9.23076  B = 26.7  C = 1.835
            // Example distance 16.5: INT(9.23076(26.7-16.5)^1.835) = 1

            int expected = 654;

            //Set a value 5 - 26.4
            //Call calculateResult to get the score
            int actual = hep100MHurdles.calculateResult(16.5);

            // Checks that expected and actual are equal
            assertEquals(expected, actual);

            // Catch any unexpected errors
        } catch (Exception e) {
            fail("Test failed due to an unexpected error: " + e.getMessage());
        }
    }

    @Test(timeOut = 2000)
    public void testDistanceHep100MHurdles() {
        try {
            // Capture console output
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));

            // Do not change the value!
            // Simulate a valid user input (example distance 16.5) in the end, to break the loop
            System.setIn(new ByteArrayInputStream("16.5".getBytes()));

            // Set any value for distance that you want to test
            hep100MHurdles.calculateResult(16.5);

            // Check the console output
            String output = outputStream.toString();

            // If the output contains "Value too low" or "Value too high", the test will fail
            if ((output.contains("Value too low") || output.contains("Value too high"))) {
                fail("Set a value for distance that is within 5 to 26.4");
            }

            // Catch any unexpected errors
        } catch (Exception e) {
            fail("Test failed due to an unexpected error: " + e.getMessage());
        }
    }

    @Test(timeOut = 2000)
    public void testScoreHep200M() {
        try {
            // Score = INT(A(B-P)^C)
            // P = time A = 4.99087  B = 42.5  C = 1.81
            // Example time 25: INT(4.99087(42.5-25)^1.81) = 887

            int expected = 887;

            // Set a valid time within the range 14 - 42.08
            // Call calculateResult to get the score
            int actual = hep200M.calculateResult(25);

            // Checks that expected and actual are equal
            assertEquals(expected, actual);

        } catch (Exception e) {
            fail("Test failed due to an unexpected error: " + e.getMessage());
        }
    }

    @Test(timeOut = 2000)
    public void testDistanceHep200M() {
        try {
            // Capture console output
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));

            // Do not change the value!
            // Simulate a valid user input (example time 25) in the end, to break the loop
            System.setIn(new ByteArrayInputStream("25".getBytes()));

            // Set any value for time that you want to test
            hep200M.calculateResult(25);

            // Check the console output
            String output = outputStream.toString();

            // If the output contains "Value too low" or "Value too high", the test will fail
            if (output.contains("Value too low") || output.contains("Value too high")) {
                fail("Set a value for time that is within 14 to 42.08");
            }

        } catch (Exception e) {
            fail("Test failed due to an unexpected error: " + e.getMessage());
        }
    }

    @Test(timeOut = 2000)
    public void testScoreHep800M() {
        try {
            // Score = INT(A(B-P)^C)
            // P = time A = 0.11193  B = 254  C = 1.88
            // Example time 120: INT(0.11193(254-120)^1.88) = 1116

            int expected = 1116;

            // Set a valid time within the range 70 - 250.79
            // Call calculateResult to get the score
            int actual = hep800M.calculateResult(120);

            // Checks that expected and actual are equal
            assertEquals(expected, actual);

        } catch (Exception e) {
            fail("Test failed due to an unexpected error: " + e.getMessage());
        }
    }

    @Test(timeOut = 2000)
    public void testDistanceHep800M() {
        try {
            // Capture console output
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));

            // Do not change the value!
            // Simulate a valid user input (example time 120) in the end, to break the loop
            System.setIn(new ByteArrayInputStream("120".getBytes()));

            // Set any value for time that you want to test
            hep800M.calculateResult(120);

            // Check the console output
            String output = outputStream.toString();

            // If the output contains "Value too low" or "Value too high", the test will fail
            if (output.contains("Value too low") || output.contains("Value too high")) {
                fail("Set a value for time that is within 70 to 250.79");
            }

        } catch (Exception e) {
            fail("Test failed due to an unexpected error: " + e.getMessage());
        }
    }
}
