package gui;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import common.Competitor;
import decathlon.*;
import heptathlon.*;
import excel.*;


public class MainGUI {

    private JTextField nameField;
    private JTextField resultField;
    private JTable competitorTable;  // Table to display competitors and their results
    private DefaultTableModel tableModel;  // Model for the table
    private JComboBox<String> disciplineBox;
    private JTextArea outputArea;
    private JButton calculateButton, excelPrintButton, excelReadButton, saveDataButton, resumeDataButton, resetDataButton;
    //Array to store competitor information
    private ArrayList<Competitor> competitors = new ArrayList<>();
    //Count of competitors
    private int competitorCount = 0;
    // Instance variable for ExcelPrinter
    private ExcelPrinter excelPrinter;
    // Instance variable for ExcelReader
    private ExcelReader excelReader = new ExcelReader();

    public static void main(String[] args) {
        new MainGUI().createAndShowGUI();
    }

    void createAndShowGUI() {
        JFrame frame = new JFrame("Track and Field Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        JPanel panel = new JPanel(new GridLayout(8, 1)); // Updated to 7 rows

        // Input for competitor's name
        nameField = new JTextField(20);
        panel.add(new JLabel("Enter Competitor's Name:"));
        panel.add(nameField);

        // Dropdown for selecting discipline
        String[] disciplines = {
                //Deca
                "Deca 100m", "Deca 400m", "Deca 1500m", "Deca 110m Hurdles",
                "Deca Long Jump", "Deca High Jump", "Deca Pole Vault",
                "Deca Discus Throw", "Deca Javelin Throw", "Deca Shot Put",
                //Hept
                "Hept 100M Hurdles", "Hept 200M", "Hept 800M",
                "Hept High Jump", "Hept Javelin Throw", "Hept Long Jump", "Hept Shot Put"
        };

        disciplineBox = new JComboBox<>(disciplines);
        panel.add(new JLabel("Select Discipline:"));
        panel.add(disciplineBox);

        // Input for result
        resultField = new JTextField(10);
        panel.add(new JLabel("Enter Result:"));
        panel.add(resultField);

        // New Button to calculate and display result
        calculateButton = new JButton("Calculate Score");
        calculateButton.addActionListener(new CalculateButtonListener());
        panel.add(calculateButton);

        //Button to reset data
        resetDataButton = new JButton("Reset Data");
        resetDataButton.addActionListener(new resetDataButtonListener());
        panel.add(resetDataButton);

        // Button to read Excel file
        excelReadButton = new JButton("Read Excel File");
        excelReadButton.addActionListener(new readExcelButtonListener());
        panel.add(excelReadButton);

        // Button to print result to excel file
        excelPrintButton = new JButton("Print to Excel");
        excelPrintButton.addActionListener(new ExcelPrintButtonListener());
        panel.add(excelPrintButton);

        //Button to save data
        saveDataButton = new JButton("Save Data");
        saveDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveData(); // Save data to file
            }
        });
        panel.add(saveDataButton);

        //Button to resume data
        resumeDataButton = new JButton("Resume Data");
        resumeDataButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resumeData(); // Resume data from file
            }
        });
        panel.add(resumeDataButton);

        // Tooltips for input fields and buttons
        nameField.setToolTipText("Enter a valid name for the competitor, the name must start with a capital letter");
        resultField.setToolTipText("Enter a valid result in numbers for the selected discipline");
        excelPrintButton.setToolTipText("This button will save the results shown on the screen to an Excel file");
        excelReadButton.setToolTipText("This button will read the data from an Excel file and display it on the screen");

        // Output area
        outputArea = new JTextArea(5, 40);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        panel.add(scrollPane);

        // Table for displaying competitors and their results
        String[] columnNames = {"Name", "Deca 100m", "Deca 400m", "Deca 1500m", "Deca 110m Hurdles",
                "Deca Long Jump", "Deca High Jump", "Deca Pole Vault",
                "Deca Discus Throw", "Deca Javelin Throw", "Deca Shot Put",
                "Hept 100M Hurdles", "Hept 200M", "Hept 800M", "Hept High Jump",
                "Hept Javelin Throw", "Hept Long Jump", "Hept Shot Put", "Total Score"};
        tableModel = new DefaultTableModel(columnNames, 0);
        competitorTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(competitorTable);
        panel.add(tableScrollPane);

        // Attempts to create Excel file and display error if it fails
        try {
            excelPrinter = new ExcelPrinter("");
        } catch (IOException ex) {
            outputArea.append("Error: " + ex.getMessage() + "\n");
        }

        frame.add(panel);
        frame.setVisible(true);
        //Resume at start
        resumeData();
    }

    private void saveData() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("savedData.dat"))) {
            for (Competitor competitor : competitors) {
                // Construct line with competitor name and scores
                //Write data to file
                StringBuilder line = new StringBuilder();
                line.append(competitor.getName()).append(",");
                for (int score : competitor.getScores()) {
                    line.append(score).append(",");
                }
                line.deleteCharAt(line.length() - 1); // Delete "-" at the end of line
                writer.write(line.toString());
                writer.newLine(); // New row for next competitor
            }
        } catch (IOException e) {
            //Display error message if there is an error
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void resumeData() {
        try (BufferedReader reader = new BufferedReader(new FileReader("savedData.dat"))) {
            String line;
            competitors.clear(); // Clear current competitors
            // Read data from file
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(","); // Split line into parts with delimiter ","
                String name = parts[0];
                int[] scores = new int[17];
                for (int i = 1; i < parts.length; i++) {
                    scores[i - 1] = Integer.parseInt(parts[i]);
                }
                Competitor competitor = new Competitor(name);
                competitor.setScores(scores); // Set score
                competitors.add(competitor);
            }
            updateTable(); // Update the table with the new data
        } catch (IOException e) {
            //Display error message if there is an error
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "File Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText().trim();
            String discipline = (String) disciplineBox.getSelectedItem();
            String resultText = resultField.getText();

            try {
                double result = Double.parseDouble(resultText);

                if (name.isEmpty() || !Character.isUpperCase(name.charAt(0))) {
                    JOptionPane.showMessageDialog(null, "Please enter a valid name for the competitor starting with an uppercase letter", "Invalid Name", JOptionPane.ERROR_MESSAGE);
                    return; // Exit the method if name is empty
                }

                int score = 0;
                switch (discipline) {
                    case "Deca 100m":
                        Deca100M deca100M = new Deca100M();
                        score = deca100M.calculateResult(result);
                        break;
                    case "Deca 400m":
                        Deca400M deca400M = new Deca400M();
                        score = deca400M.calculateResult(result);
                        break;
                    case "Deca 1500m":
                        Deca1500M deca1500M = new Deca1500M();
                        score = deca1500M.calculateResult(result);
                        break;
                    case "Deca 110m Hurdles":
                        Deca110MHurdles deca110MHurdles = new Deca110MHurdles();
                        score = deca110MHurdles.calculateResult(result);
                        break;
                    case "Deca Long Jump":
                        DecaLongJump decaLongJump = new DecaLongJump();
                        score = decaLongJump.calculateResult(result);
                        break;
                    case "Deca High Jump":
                        DecaHighJump decaHighJump = new DecaHighJump();
                        score = decaHighJump.calculateResult(result);
                        break;
                    case "Deca Pole Vault":
                        DecaPoleVault decaPoleVault = new DecaPoleVault();
                        score = decaPoleVault.calculateResult(result);
                        break;
                    case "Deca Discus Throw":
                        DecaDiscusThrow decaDiscusThrow = new DecaDiscusThrow();
                        score = decaDiscusThrow.calculateResult(result);
                        break;
                    case "Deca Javelin Throw":
                        DecaJavelinThrow decaJavelinThrow = new DecaJavelinThrow();
                        score = decaJavelinThrow.calculateResult(result);
                        break;
                    case "Deca Shot Put":
                        DecaShotPut decaShotPut = new DecaShotPut();
                        score = decaShotPut.calculateResult(result);
                        break;
                    case "Hept 100M Hurdles":
                        Hep100MHurdles hep100MHurdles = new Hep100MHurdles();
                        score = hep100MHurdles.calculateResult(result);
                        break;
                    case "Hept 200M":
                        Hep200M hep200M = new Hep200M();
                        score = hep200M.calculateResult(result);
                        break;
                    case "Hept 800M":
                        Hep800M hep800M = new Hep800M();
                        score = hep800M.calculateResult(result);
                        break;
                    case "Hept High Jump":
                        HeptHightJump heptHighJump = new HeptHightJump();
                        score = heptHighJump.calculateResult(result);
                        break;
                    case "Hept Javelin Throw":
                        HeptJavelinThrow heptJavelinThrow = new HeptJavelinThrow();
                        score = heptJavelinThrow.calculateResult(result);
                        break;
                    case "Hept Long Jump":
                        HeptLongJump heptLongJump = new HeptLongJump();
                        score = heptLongJump.calculateResult(result);
                        break;
                    case "Hept Shot Put":
                        HeptShotPut heptShotPut = new HeptShotPut();
                        score = heptShotPut.calculateResult(result);
                        break;
                }

                // Find an existing competitor or create a new one
                Competitor competitor = findCompetitorByName(name);
                if (competitor == null) {
                    competitor = new Competitor(name);  // Create new competitor
                    competitors.add(competitor);  // Add to list
                    addCompetitorToTable(competitor);  // Add a new row to the table
                }

                // Update the competitor's score for the current discipline
                competitor.setScore(discipline, score);

                // Update the table with the new score
                updateCompetitorInTable(competitor);

                outputArea.append("Competitor: " + name + "\n");
                outputArea.append("Discipline: " + discipline + "\n");
                outputArea.append("Result: " + result + "\n");
                outputArea.append("Score: " + score + "\n\n");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter a valid number for the result.", "Invalid Input", JOptionPane.ERROR_MESSAGE);
            } catch (InvalidResultException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Invalid Result", JOptionPane.ERROR_MESSAGE);
            }
        }

        // Find a competitor by name
        private Competitor findCompetitorByName(String name) {
            for (Competitor competitor : competitors) {
                if (competitor.getName().equalsIgnoreCase(name)) {
                    return competitor;
                }
            }
            return null;  // No competitor found
        }
        // Method to add a competitor to the table
        private void addCompetitorToTable(Competitor competitor) {
            Object[] rowData = competitor.getRowData();
            tableModel.addRow(rowData);
        }

        // Method to update an existing competitor's row in the table
        private void updateCompetitorInTable(Competitor competitor) {
            int rowIndex = findCompetitorRow(competitor.getName());
            if (rowIndex != -1) {
                Object[] rowData = competitor.getRowData();
                for (int i = 0; i < rowData.length; i++) {
                    tableModel.setValueAt(rowData[i], rowIndex, i);
                }
            }
        }

        // Find the row index of the competitor in the table
        private int findCompetitorRow(String name) {
            for (int i = 0; i < tableModel.getRowCount(); i++) {
                if (tableModel.getValueAt(i, 0).equals(name)) {
                    return i;
                }
            }
            return -1;
        }
    }

    //ActionListener for the "Reset Data" button
    private class resetDataButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            nameField.setText("");
            resultField.setText("");
            disciplineBox.setSelectedIndex(0);
            outputArea.setText("");
            competitorCount = 0;
            competitors.clear();
            tableModel.setRowCount(0);
        }
    }
    // ActionListener for the "Read Excel File" button
    private class readExcelButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // Get the list of competitors from the Excel file
                List<Competitor> competitorsFromExcel = excelReader.readExcelData();

                // Add these competitors to the current list of competitors
                competitors.addAll(competitorsFromExcel);

                // Update the table with the new competitors
                updateTable();

            } catch (IOException ex) {
                // Display an error message
                outputArea.append("Error: " + ex.getMessage() + "\n");
                JOptionPane.showMessageDialog(null, "Error reading Excel file!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
            // ActionListener for the "Print to Excel" button
            private class ExcelPrintButtonListener implements ActionListener {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        // Attempt to save the data to Excel
                        Object[][] data = convertCompetitorsToData();
                        excelPrinter.add(data, "Results");
                        excelPrinter.write();
                        outputArea.append("Results saved to Excel!\n");
                    } catch (IOException ex) {
                        outputArea.append("Error: " + ex.getMessage() + "\n");
                        JOptionPane.showMessageDialog(null, "Error saving to Excel!", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }

                //Prepares the data for the Excel sheet
                private Object[][] convertCompetitorsToData() {
                    Object[][] data = new Object[competitors.size()][18];  // 18 columns (17 events + name)
                    for (int i = 0; i < competitors.size(); i++) {
                        Competitor competitor = competitors.get(i);
                        Object[] rowData = competitor.getRowData();  // Get competitor row data
                        data[i] = rowData;
                    }
                    // Return the converted data
                    return data;
                }
            }
    private void updateTable() {
        tableModel.setRowCount(0); // Empty the table before adding new competitors
        for (Competitor competitor : competitors) {
            tableModel.addRow(competitor.getRowData());
        }
    }


        //Getters to get interaction outside the class
        public JTextField getNameField() {
            return nameField;
        }

        public JComboBox<String> getDisciplineBox() {
            return disciplineBox;
        }

        public JTextField getResultField() {
            return resultField;
        }

        public JButton getCalculateButton() {
            return calculateButton;
        }

        public JTextArea getOutputArea() {
            return outputArea;
        }

        public JButton getExcelPrintButton() {
            return excelPrintButton;
        }

        public int getCompetitorCount() {
        return competitorCount;
    }
    }

