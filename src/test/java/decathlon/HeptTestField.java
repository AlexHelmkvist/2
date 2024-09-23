package decathlon;

import heptathlon.HeptHightJump;
import heptathlon.HeptJavelinThrow;
import heptathlon.HeptLongJump;
import heptathlon.HeptShotPut;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.fail;

public class HeptTestField {

    HeptHightJump heptHighJump = new HeptHightJump();
    HeptJavelinThrow heptJavelinThrow = new HeptJavelinThrow();
    HeptLongJump heptLongJump = new HeptLongJump();
    HeptShotPut heptShotPut = new HeptShotPut();

    @Test(timeOut = 2000)
    public void testScoreHeptHightJump() {
        try {
            // Score = INT(A(P — B)C)
            // P = distance A = 1.84523  B = 75  C = 1.348
            // Example distance 200: INT(1.84523(200-75)^1.348) = 1237

            int expected = 1237;

            //Set a value 75.7 - 270
            //Call calculateResult to get the score
            int actual = heptHighJump.calculateResult(200);

            // Checks that expected and actual are equal
            assertEquals(expected, actual);

            // Catch any unexpected errors
        } catch (Exception e) {
            fail("Test failed due to an unexpected error: " + e.getMessage());
        }
    }

    @Test(timeOut = 2000)
    public void testDistanceHeptHightJump() {
        try {
            // Capture console output
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));

            // Do not change the value!
            // Simulate a valid user input (example distance 200) in the end, to break the loop
            System.setIn(new ByteArrayInputStream("200".getBytes()));

            // Set any value for distance that you want to test
            heptHighJump.calculateResult(200);

            // Check the console output
            String output = outputStream.toString();

            // If the output contains "Value too low" or "Value too high", the test will fail
            if ((output.contains("Value too low") || output.contains("Value too high"))) {
                fail("Set a value for distance that is within 75.7 to 270");
            }

            // Catch any unexpected errors
        } catch (Exception e) {
            fail("Test failed due to an unexpected error: " + e.getMessage());
        }
    }

    @Test(timeOut = 2000)
    public void testScoreHeptJavelinThrow() {
        try {
            // Score = INT(A(P — B)C)
            // P = distance A = 15.9803  B = 3.8  C = 1.04
            // Example distance 80: INT(15.9803(80 - 3.8)^1.04) = 1448

            int expected = 1448;

            // Set a value 0 - 100
            // Call calculateResult to get the score
            int actual = heptJavelinThrow.calculateResult(80);

            // Checks that expected and actual are equal
            assertEquals(expected, actual);

        } catch (Exception e) {
            fail("Test failed due to an unexpected error: " + e.getMessage());
        }
    }

    @Test(timeOut = 2000)
    public void testDistanceHeptJavelinThrow() {
        try {
            // Capture console output
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));

            // Do not change the value!
            // Simulate a valid user input (example distance 80) in the end, to break the loop
            System.setIn(new ByteArrayInputStream("80".getBytes()));

            // Set any value for distance that you want to test
            heptJavelinThrow.calculateResult(80);

            // Check the console output
            String output = outputStream.toString();

            // If the output contains "Value too low" or "Value too high", the test will fail
            if ((output.contains("Value too low") || output.contains("Value too high"))) {
                fail("Set a value for distance that is within 0 to 100");
            }

        } catch (Exception e) {
            fail("Test failed due to an unexpected error: " + e.getMessage());
        }
    }

    @Test(timeOut = 2000)
    public void testScoreHeptLongJump() {
        try {
            // Score = INT(A(P — B)C)
            // P = distance A = 0.1888807  B = 210  C = 1.41
            // Example distance 300: INT(0.1888807(300 - 210)^1.41) = 107

            int expected = 107;

            // Set a value 0 - 400
            // Call calculateResult to get the score
            int actual = heptLongJump.calculateResult(300);

            // Checks that expected and actual are equal
            assertEquals(expected, actual);

        } catch (Exception e) {
            fail("Test failed due to an unexpected error: " + e.getMessage());
        }
    }

    @Test(timeOut = 2000)
    public void testDistanceHeptLongJump() {
        try {
            // Capture console output
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));

            // Do not change the value!
            // Simulate a valid user input (example distance 300) in the end, to break the loop
            System.setIn(new ByteArrayInputStream("300".getBytes()));

            // Set any value for distance that you want to test
            heptLongJump.calculateResult(3000);

            // Check the console output
            String output = outputStream.toString();

            // If the output contains "Value too low" or "Value too high", the test will fail
            if ((output.contains("Value too low") || "Value too high".contains(output))) {
                fail("Set a value for distance that is within 0 to 400");
            }

        } catch (Exception e) {
            fail("Test failed due to an unexpected error: " + e.getMessage());
        }
    }

    @Test(timeOut = 2000)
    public void testScoreHeptShotPut() {
        try {
            // Score = INT(A(P — B)C)
            // P = distance A = 56.0211  B = 1.5 C = 1.05
            // Example distance 16.5: INT(56.0211(16.5-1.5)^1.05) = 962

            int expected = 962;

            //Set a value 5 - 100
            //Call calculateResult to get the score
            int actual = heptShotPut.calculateResult(16.5);

            // Checks that expected and actual are equal
            assertEquals(expected, actual);

            // Catch any unexpected errors
        } catch (Exception e) {
            fail("Test failed due to an unexpected error: " + e.getMessage());
        }
    }

    @Test(timeOut = 2000)
    public void testDistanceHeptShotPut() {
        try {
            // Capture console output
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));

            // Do not change the value!
            // Simulate a valid user input (example dis16tance 16.5) in the end, to break the loop
            System.setIn(new ByteArrayInputStream("16.5".getBytes()));

            // Set any value for distance that you want to test
            heptShotPut.calculateResult(16.5);

            // Check the console output
            String output = outputStream.toString();

            // If the output contains "Value too low" or "Value too high", the test will fail
            if ((output.contains("Value too low") || output.contains("Value too high"))) {
                fail("Set a value for distance that is within 5 to 100");
            }

            // Catch any unexpected errors
        } catch (Exception e) {
            fail("Test failed due to an unexpected error: " + e.getMessage());
        }
    }
}
