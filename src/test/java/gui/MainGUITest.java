package gui;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class MainGUITest {
    private MainGUI mainGUI;


    @Before
    public void setUp() {
        // Launch and set up the GUI
        mainGUI = new MainGUI();
        mainGUI.createAndShowGUI();
    }

    @Test
    public void testAddCompetitor() {
        // Test adding a new competitor
        String name = "John";
        String descipline = "Deca 100m";
        double result = 11.0;

        mainGUI.getNameField().setText(name);
        mainGUI.getDisciplineBox().setSelectedItem(descipline);
        mainGUI.getResultField().setText(String.valueOf(result));
        mainGUI.getCalculateButton().doClick();
        mainGUI.getNameField().setText("Adam");
        mainGUI.getCalculateButton().doClick();

        // Getting competitor data from the array
        String competitorData = mainGUI.getCompetitors()[0];

        // Split up the String into each part
        String[] parts = competitorData.split("-");

        // Verify that the name is actually added
        assertEquals(2, mainGUI.getCompetitorCount());
        assertEquals(name, parts[0].trim());
    }

    @Test
    public void testMaxCompetitors() {
        // Test adding 40 competitors
        for (int i = 0; i < 40; i++) {
            try {
                String name = "John";
                String result = "11";
                mainGUI.getCompetitors()[i] = name + result;
            } catch (ArrayIndexOutOfBoundsException e) {
                // Fail if the number of competitors exceeds 40
                fail(e.getMessage());
            }
        }
    }
    // Testar att en Excel-fil skapas när knappen trycks
    @Test //Saleh test
    public void testExcelFileCreation() throws IOException, InterruptedException {
        // Testdata
        String testName = "Saleh";
        String testDiscipline = "Deca 100m";
        String testResult = "10.5";

        // Ange testdata i GUI:t
        mainGUI.getNameField().setText(testName);
        mainGUI.getDisciplineBox().setSelectedItem(testDiscipline);
        mainGUI.getResultField().setText(testResult);
        Thread.sleep(1000);
        mainGUI.getCalculateButton().doClick();
        Thread.sleep(1000);
        // Klickar på "Print to Excel"-knappen
        mainGUI.getExcelPrintButton().doClick();

        try {
          //If there is Results saved to Excel! in outputArea
            assertTrue(mainGUI.getOutputArea().getText().contains("Results saved to Excel!"));

        } catch (AssertionError e) {
            // Hantera specifika assertionsfel
            fail("Ett fel inträffade under testet: " + e.getMessage());
        }
    }
}
