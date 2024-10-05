package gui;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import java.awt.*;
import java.io.*;

import decathlon.*;
import heptathlon.*;
import excel.*;


public class MainGUI {

    private JTextField nameField;
    private JTextField resultField;
    private JComboBox<String> disciplineBox;
    private JTextArea outputArea;
    private JButton calculateButton;
    //Array to store competitor information
    private String[] competitors = new String[40];
    //Count of competitors
    private int competitorCount = 0;
    // Button to print Excel file
    private JButton excelPrintButton;
    // Button to read Excel file
    private JButton excelReadButton;
    // Button to save current data
    private JButton saveDataButton;
    // Instance variable for ExcelPrinter
    private ExcelPrinter excelPrinter;
    // Instance variable for ExcelReader
    private ExcelReader excelReader;

    public static void main(String[] args) {
        new MainGUI().createAndShowGUI();
    }

    void createAndShowGUI() {
        JFrame frame = new JFrame("Track and Field Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 400);

        JPanel panel = new JPanel(new GridLayout(6, 1));

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

        // Output area
        outputArea = new JTextArea(5, 40);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);
        panel.add(scrollPane);

        // Button to read Excel file
        excelReadButton = new JButton("Read Excel File");
        excelReadButton.addActionListener(new ReadExcelButtonListener());
        panel.add(excelReadButton);

        // Button to print result to excel file
        excelPrintButton = new JButton("Print to Excel");
        excelPrintButton.addActionListener(new ExcelPrintButtonListener());
        panel.add(excelPrintButton);

        //Button to save data
        saveDataButton = new JButton("Save Data");
        saveDataButton.addActionListener(new SaveDataButtonListener());
        panel.add(saveDataButton);

        // Attempts to create Excel file named "final results" and display error if it fails
        try {
            excelPrinter = new ExcelPrinter("");
        } catch (IOException ex) {
            outputArea.append("Error: " + ex.getMessage() + "\n");
        }

        excelReader = new ExcelReader();
        frame.add(panel);
        frame.setVisible(true);

        // Load saved data when the program starts
        loadData();

        // Save data when the program is stopped
        Runtime.getRuntime().addShutdownHook(new Thread(() -> saveData()));

    }


    // Save competitors' data to a file when the program stops
    private void saveData() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("programdata.dat"))) {
            out.writeObject(competitors);
            out.writeInt(competitorCount);  // Save the number of competitors
            outputArea.append("Data saved successfully! \n");
        } catch (IOException e) {
            outputArea.append("Error saving data: " + e.getMessage());
        }
    }

    // Load competitors' data from a file when the program starts
    private void loadData() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("programdata.dat"))) {
            competitors = (String[]) in.readObject();
            competitorCount = in.readInt();
            outputArea.append("Data loaded successfully! \n");
            // Optionally, you can append loaded data to outputArea here
            for (int i = 0; i < competitorCount; i++) {
                outputArea.append(competitors[i] + "\n");
            }
        } catch (IOException | ClassNotFoundException e) {
            outputArea.append("No saved data found. \n");
        }
    }

    private class CalculateButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String discipline = (String) disciplineBox.getSelectedItem();
            String resultText = resultField.getText();

            try {
                double result = Double.parseDouble(resultText);

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


                // Save the competitor's information to the array
                if (competitorCount < competitors.length) {
                    competitors[competitorCount] = name + "-" + discipline + "-" + result + "-" + score;
                    competitorCount++;
                } else {
                    JOptionPane.showMessageDialog(null, "Maximum number of competitors reached.", "Error", JOptionPane.ERROR_MESSAGE);
                    return; // Exit the method if the maximum number of competitors is reached
                }

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
    }

    // ActionListener for the "Save Data" button
    private class SaveDataButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            saveData();
        }
    }

    // ActionListener for the "Read Excel File" button
    private class ReadExcelButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                // attempt to read the data from the Excel file
                excelReader = new ExcelReader();
                String excelData = excelReader.getCellInfo( 0, 0, 0,1,2,3);
                outputArea.append("Excel Data: " + excelData + "\n");
            } catch (IOException ex) {
                outputArea.append("Error reading Excel file: " + ex.getMessage() + "\n");
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
                        outputArea.append("Error saving to Excel: " + ex.getMessage() + "\n");
                    }
                }

                //Prepares the data for the Excel sheet
                private Object[][] convertCompetitorsToData() {
                    Object[][] data = new Object[competitorCount][4];
                    for (int i = 0; i < competitorCount; i++) {
                        String[] parts = competitors[i].split("-");
                        data[i][0] = parts[0]; // Name
                        data[i][1] = parts[1]; // Discipline
                        data[i][2] = parts[2]; // Result
                        data[i][3] = parts[3]; // Score
                    }
                    // Return the converted data
                    return data;
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

        public String[] getCompetitors() {
            return competitors;
        }

        public int getCompetitorCount() {
            return competitorCount;
        }
    }

