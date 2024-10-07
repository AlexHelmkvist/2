package excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;

public class ExcelReader {
	
	public String getCellInfo( int sheetNumber, int rowNumber, int colNumber, int colNumber1, int colNumber2, int colNumber3) throws IOException {
		// Set the path of the Excel file
		JFileChooser fileChooser = new JFileChooser("C:/Eclipse/");
		fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Excel Files", "xls", "xlsx"));

		// The dialog box doesn't use any parent component, so we have to set it to null
		if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			//Changed to getSelectedFile() instead of specific file name
			File excelfile = fileChooser.getSelectedFile();
		    FileInputStream fis = new FileInputStream(excelfile);
		
		@SuppressWarnings("resource")
		XSSFWorkbook wb = new XSSFWorkbook(fis);

		Sheet sheet = wb.getSheetAt(sheetNumber);
		Row row = sheet.getRow(rowNumber);
		Cell cell = row.getCell(colNumber);
		//Changed here
		Cell cell1 = row.getCell(colNumber1);
		Cell cell2 = row.getCell(colNumber2);
		Cell cell3 = row.getCell(colNumber3);

			DataFormatter dataFormatter = new DataFormatter();
			//Changed here
			return dataFormatter.formatCellValue(cell) +  dataFormatter.formatCellValue(cell1) +  dataFormatter.formatCellValue(cell2)  + dataFormatter.formatCellValue(cell3);
		} else {
			return "No file selected.";
		}
	}
}