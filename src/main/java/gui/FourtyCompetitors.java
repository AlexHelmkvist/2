package gui;

import javax.swing.*;

public class FourtyCompetitors {
    public static void main(String[] args) {
        // Lambda expression
        // Event Dispatch Thread
        // This launches the GUI
        SwingUtilities.invokeLater(() -> {
            MainGUI mainGUI = new MainGUI();
            mainGUI.createAndShowGUI();
            // This simulates user interaction with the GUI
            simulateUserInteraction(mainGUI);
        });
    }

    private static void simulateUserInteraction(MainGUI mainGUI) {
        try {
            // Access GUI components
            JTextField nameField = mainGUI.getNameField();
            JComboBox<String> disciplineBox = mainGUI.getDisciplineBox();
            JTextField resultField = mainGUI.getResultField();
            JButton calculateButton = mainGUI.getCalculateButton();

            //Set discipline and result
            //Example Deca 400m with result 20
            disciplineBox.setSelectedItem("Deca 400m");
            resultField.setText("20");

            //Enter 40 participants with different names
            int j = 0;
            for (int i = 0; i < 40; i++) {
                if (i < 26) {
                    char c = (char) ('A' + i);
                    nameField.setText("John" + c);
                    calculateButton.doClick();
                } else if (i > 25) {
                    char c = (char) ('A' + j);
                    nameField.setText("John" + c + c);
                    calculateButton.doClick();
                    j++;
                }
            }

        }
             //Unknown error occurred
             catch (Exception e) {
                System.out.println( "Error occurred: " + e.getMessage());
        }
    }
}
