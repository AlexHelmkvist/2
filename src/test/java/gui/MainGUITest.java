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
        mainGUI.getNameField().setText("John");
        mainGUI.getDisciplineBox().setSelectedItem("Deca 100m");
        mainGUI.getResultField().setText("11");
        mainGUI.getCalculateButton().doClick();

        // Verify that the new competitor was added
        assertEquals("John" + 11.0, mainGUI.getCompetitors()[0]);
        assertEquals(1, mainGUI.getCompetitorCount());
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