package gui;

import javax.swing.*;

public class GUITestAutomation {
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
            JTextArea outputArea = mainGUI.getOutputArea();
            //Set discipline and result
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



            //Capturing output from JTextArea
            String output = outputArea.getText();
            // Split the output string based on the occurrence of "John"
            int count = output.split("John").length -1 ;

            //Prints out the number of participants found in GUI
            if (count == 40) {
                outputArea.append(count + " Participants found. Verification passed.\n");
            } else {
                outputArea.append(count + " Participants found. Verification failed.\n");
                Thread.sleep(500);
                System.exit(0);
            }

            //When the Thread is interrupted, it throws an InterruptedException with log details
        } catch (InterruptedException e) {
            System.out.println( "Thread interrupted: " + e);
        }
    }
}
