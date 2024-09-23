package decathlon;

import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.testng.AssertJUnit.assertEquals;
import static org.testng.AssertJUnit.fail;

public class DecaTestTrack {
    Deca100M deca100M = new Deca100M();
    Deca400M deca400M = new Deca400M();
    Deca110MHurdles deca110MHurdles = new Deca110MHurdles();
    Deca1500M deca1500M = new Deca1500M();

    @Test(timeOut = 2000)
    public void testScoreDeca100m() {
        try {
            // Poäng = INT(A(B — P)C)
            // A=25.4347; B=18; C=1.81

            int expected = 2640;

            //Set a value 10 - 30
            //Call calculateResult to get the score 1
            int actual = deca100M.calculateResult(5);

            // Checks that expected and actual are equal
            assertEquals(expected, actual);

            // Catch any unexpected errors
        } catch (Exception e) {
            fail("Test failed due to an unexpected error: " + e.getMessage());
        }
    }

    @Test(timeOut = 2000)
    public void testDistanceDeca100m() {
        try {
            // Capture console output
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));

            // Do not change the value!
            // Simulate a valid user input (example distance 5) in the end, to break the loop
            System.setIn(new ByteArrayInputStream("5".getBytes()));

            // Set any value for distance that you want to test
            deca100M.calculateResult(5);

            // Check the console output
            String output = outputStream.toString();

            // If the output contains "Value too low" or "Value too high", the test will fail
            if ((output.contains("Value too low") || output.contains("Value too high"))) {
                fail("Set a value for distance that is within 5 to 20");
            }

            // Catch any unexpected errors
        } catch (Exception e) {
            fail("Test failed due to an unexpected error: " + e.getMessage());
        }
    }

    @Test(timeOut = 2000)
    public void testScoreDeca110MHurdles() {
        try {
            // Poäng = INT(A(B — P)C)
            // A=5.74352; B=28.5; C=1.92

            int expected = 1556;
            //Acceptable values for DecaJavelinThrow are from 10 to 30
            int actual = deca110MHurdles.calculateResult(10);

            assertEquals(expected, actual);

        } catch (Exception e) {
            fail("Test failed due to an unexpected error: " + e.getMessage());
        }
    }

    @Test(timeOut = 2000)
    public void testDistanceDeca110MHurdles() {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            System.setIn(new ByteArrayInputStream("10".getBytes()));

            deca110MHurdles.calculateResult(10);

            String output = outputStream.toString();

            if ((output.contains("Value too low") || output.contains("Value too high"))) {
                fail("Set a value for distance that is within 10 to 30");
            }

        } catch (Exception e) {
            fail("Test failed due to an unexpected error: " + e.getMessage());
        }
    }

    @Test(timeOut = 2000)
    public void testScoreDeca400M() {
        try {
            // Poäng = INT(A(B — P)C)
            // A=1.53775; B=82; C=1.81

            int expected = 2698;
            //Acceptable values for DecaHighJump are 20 to 100
            int actual = deca400M.calculateResult(20);
            assertEquals(expected, actual);

        } catch (Exception e) {
            fail("Test failed due to an unexpected error: " + e.getMessage());
        }
    }

    @Test(timeOut = 2000)
    public void testDistance400M() {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            System.setIn(new ByteArrayInputStream("20".getBytes()));

            deca400M.calculateResult(20);

            String output = outputStream.toString();

            if ((output.contains("Value too low") || output.contains("Value too high"))) {
                fail("Set a value for distance that is within 20 to 100");
            }

        } catch (Exception e) {
            fail("Test failed due to an unexpected error: " + e.getMessage());
        }
    }

    @Test(timeOut = 2000)
    public void testScore1500M() {
        try {
            // Poäng = INT(A(B — P)C)
            // A= 0.03768; B=480; C=1.85

            int expected = 1719;
            //Acceptable values for DecaLongJump are from 150 to 400
            int actual = deca1500M.calculateResult(150);

            assertEquals(expected, actual);

        } catch (Exception e) {
            fail("Test failed due to an unexpected error: " + e.getMessage());
        }
    }

    @Test(timeOut = 2000)
    public void testDistance1500M() {
        try {
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            System.setOut(new PrintStream(outputStream));
            System.setIn(new ByteArrayInputStream("150".getBytes()));

            deca1500M.calculateResult(150);

            String output = outputStream.toString();

            if ((output.contains("Value too low") || output.contains("Value too high"))) {
                fail("Set a value for distance that is within 150 to 400");
            }

        } catch (Exception e) {
            fail("Test failed due to an unexpected error: " + e.getMessage());
        }
    }



}
