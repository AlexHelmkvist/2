package decathlon;

import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.fail;

public class DecaTestField {

    DecaDiscusThrow decaDiscusThrow = new DecaDiscusThrow();
    DecaJavelinThrow decaJavelinThrow = new DecaJavelinThrow();
    DecaHighJump decaHighJump = new DecaHighJump();
    DecaLongJump decaLongJump = new DecaLongJump();
    DecaPoleVault decaPoleVault = new DecaPoleVault();
    DecaShotPut decaShotPut = new DecaShotPut();

    @Test(timeOut = 2000)
    public void testScoreDecaDiscusThrow() {
        try {
            // Score = INT(A(P — B)C)
            // P = distance A = 12.91;  B = 4; C = 1.1;
            // Example distance 80: INT(12.91(80 - 4)^1.1) = 1512

            int expected = 1512;

            //Set a value 0 - 85
            //Call calculateResult to get the score
            int actual = decaDiscusThrow.calculateResult(80);

            // Checks that expected and actual are equal
            assertEquals(expected, actual);

            // Catch any unexpected errors
        } catch (Exception e) {
            fail("Test failed due to an unexpected error: " + e.getMessage());
        }
    }

    @Test(timeOut = 2000)
    public void testDistanceDecaDiscusThrow() {
        try {
            // Capture console output
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));

            // Do not change the value!
            // Simulate a valid user input (example distance 80) in the end, to break the loop
            System.setIn(new ByteArrayInputStream("80".getBytes()));

            // Set any value for distance that you want to test
            decaDiscusThrow.calculateResult(80);

            // Check the console output
            String output = outputStream.toString();

            // If the output contains "Value too low" or "Value too high", the test will fail
            if ((output.contains("Value too low") || output.contains("Value too high"))) {
                fail("Set a value for distance that is within 0 to 85");
            }

            // Catch any unexpected errors
        } catch (Exception e) {
            fail("Test failed due to an unexpected error: " + e.getMessage());
        }
    }

    @Test(timeOut = 2000)
    public void testScoreDecaJavelinThrow() {
        try {
            // Score = INT(A(P — B)C)
            // P = distance A = 10.14 B = 7 C = 1.08;
            // Example distance 70 : INT(10.14(70 - 7)^1.08) = 889

            int expected = 889;
            //Acceptable values for DecaJavelinThrow are from 0 to 100
            int actual = decaJavelinThrow.calculateResult(70);

            assertEquals(expected, actual);

        } catch (Exception e) {
            fail("Test failed due to an unexpected error: " + e.getMessage());
        }
    }

    @Test(timeOut = 2000)
    public void testDistanceDecaJavelinThrow() {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            System.setIn(new ByteArrayInputStream("70".getBytes()));

            decaJavelinThrow.calculateResult(70);

            String output = outputStream.toString();

            if ((output.contains("Value too low") || output.contains("Value too high"))) {
                fail("Set a value for distance that is within 0 to 110");
            }

        } catch (Exception e) {
            fail("Test failed due to an unexpected error: " + e.getMessage());
        }
    }

    @Test(timeOut = 2000)
    public void testScoreDecaHighJump() {
        try {
            // Score = INT(A(P — B)C)
            // P = distance A = 0.8465 B = 75 C = 1.42
            // Example distance 80 : INT(0.8465(80 - 75)^1.42) = 8

            int expected = 8;
            //Acceptable values for DecaHighJump are 75 to 250
            int actual = decaHighJump.calculateResult(80);

            assertEquals(expected, actual);

        } catch (Exception e) {
            fail("Test failed due to an unexpected error: " + e.getMessage());
        }
    }

    @Test(timeOut = 2000)
    public void testDistanceDecaHighJump() {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            System.setIn(new ByteArrayInputStream("70".getBytes()));

            decaHighJump.calculateResult(70);

            String output = outputStream.toString();

            if ((output.contains("Value too low") || output.contains("Value too high"))) {
                fail("Set a value for distance that is within 0 to 100");
            }

        } catch (Exception e) {
            fail("Test failed due to an unexpected error: " + e.getMessage());
        }
    }

    @Test(timeOut = 2000)
    public void testScoreDecaLongJump() {
        try {
            // Score = INT(A(P — B)C)
            // P = distance A = 0.13454 B = 220 C = 1.4
            // Example distance 500 : INT(0.13454(500 - 220)^1.4) = 358

            int expected = 358;
            //Acceptable values for DecaLongJump are from 0 to 1000
            int actual = decaLongJump.calculateResult(500);

            assertEquals(expected, actual);

        } catch (Exception e) {
            fail("Test failed due to an unexpected error: " + e.getMessage());
        }
    }

    @Test(timeOut = 2000)
    public void testDistanceDecaLongJump() {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            System.setIn(new ByteArrayInputStream("750".getBytes()));

            decaLongJump.calculateResult(750);

            String output = outputStream.toString();

            if ((output.contains("Value too low") || output.contains("Value too high"))) {
                fail("Set a value for distance that is within 250 to 1000");
            }

        } catch (Exception e) {
            fail("Test failed due to an unexpected error: " + e.getMessage());
        }
    }

    @Test(timeOut = 2000)
    public void testScoreDecaPoleVault() {
        try {
            // Score = INT(A(P — B)C)
            // P = distance A = 0.2797 B = 100 C = 1.35
            // Example distance 500 : INT(0.2797(500 - 100)^1.35) = 910

            int expected = 910;
            //Acceptable values for DecaPoleVault are 0 to 600
            int actual = decaPoleVault.calculateResult(500);

            assertEquals(expected, actual);

        } catch (Exception e) {
            fail("Test failed due to an unexpected error: " + e.getMessage());
        }
    }

    @Test(timeOut = 2000)
    public void testDistanceDecaPoleVault() {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            System.setIn(new ByteArrayInputStream("500".getBytes()));

            decaPoleVault.calculateResult(500);

            String output = outputStream.toString();

            if ((output.contains("Value too low") || output.contains("Value too high"))) {
                fail("Set a value for distance that is within 2 to 1000");
            }

        } catch (Exception e) {
            fail("Test failed due to an unexpected error: " + e.getMessage());
        }
    }

    @Test(timeOut = 2000)
    public void testScoreDecaShotPut() {
        try {
            // Score = INT(A(P — B)C)
            // P = distance A = 51.39 B = 1.5 C = 1.05
            // Example distance 15.5 : INT(51.39(16.5 - 1.5)^1.05) = 882

            int expected = 882;
            //Acceptable values for DecaShotPut are from 0 to 30
            int actual = decaShotPut.calculateResult(16.5);

            assertEquals(expected, actual);

        } catch (Exception e) {
            fail("Test failed due to an unexpected error: " + e.getMessage());
        }
    }

    @Test(timeOut = 2000)
    public void testDistanceDecaShotPut() {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            System.setIn(new ByteArrayInputStream("16.5".getBytes()));

            decaShotPut.calculateResult(16.5);

            String output = outputStream.toString();

            if ((output.contains("Value too low") || output.contains("Value too high"))) {
                fail("Set a value for distance that is within 0 to 30");
            }

        } catch (Exception e) {
            fail("Test failed due to an unexpected error: " + e.getMessage());
        }
    }
}