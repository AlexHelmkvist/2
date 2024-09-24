package gui;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class MainGUITest {
    private MainGUI mainGUI;

    @Before
    public void setUp() {
        // Launch the GUI
        mainGUI = new MainGUI();
        // Set the GUI up
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
        assertEquals("John", mainGUI.getCompetitors()[0]);
        assertEquals(1, mainGUI.getCompetitorCount());
    }
}
