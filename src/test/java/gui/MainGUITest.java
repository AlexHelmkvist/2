package gui;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

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
}