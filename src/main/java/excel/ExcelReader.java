package excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.JFileChooser;

import common.Competitor;

public class ExcelReader {

	public List<Competitor> readExcelData() throws IOException {
		List<Competitor> competitors = new ArrayList<>();

		try {
			// File chooser to select the Excel file
			JFileChooser fileChooser = new JFileChooser("C:/Eclipse/");
			fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("Excel Files", "xls", "xlsx"));

			if (fileChooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
				File excelFile = fileChooser.getSelectedFile();
				FileInputStream fis = new FileInputStream(excelFile);

				Workbook workbook = new XSSFWorkbook(fis);
				Sheet sheet = workbook.getSheetAt(0);  // Get the first sheet

				for (Row row : sheet) {
					// first column is "name"
					String name = row.getCell(0).getStringCellValue();
					Competitor competitor = new Competitor(name);

					//Mapping for Decathlon
					competitor.setScore("Deca 100m", (int) row.getCell(1).getNumericCellValue());
					competitor.setScore("Deca 400m", (int) row.getCell(2).getNumericCellValue());
					competitor.setScore("Deca 1500m", (int) row.getCell(3).getNumericCellValue());
					competitor.setScore("Deca 110m Hurdles", (int) row.getCell(4).getNumericCellValue());
					competitor.setScore("Deca Long Jump", (int) row.getCell(5).getNumericCellValue());
					competitor.setScore("Deca High Jump", (int) row.getCell(6).getNumericCellValue());
					competitor.setScore("Deca Pole Vault", (int) row.getCell(7).getNumericCellValue());
					competitor.setScore("Deca Discus Throw", (int) row.getCell(8).getNumericCellValue());
					competitor.setScore("Deca Javelin Throw", (int) row.getCell(9).getNumericCellValue());
					competitor.setScore("Deca Shot Put", (int) row.getCell(10).getNumericCellValue());

					//Mapping for Heptathlon
					competitor.setScore("Heptathlon 100m hurdles", (int) row.getCell(11).getNumericCellValue());
					competitor.setScore("Heptathlon 200m", (int) row.getCell(12).getNumericCellValue());
					competitor.setScore("Heptathlon 800m", (int) row.getCell(13).getNumericCellValue());
					competitor.setScore("Heptathlon High Jump", (int) row.getCell(14).getNumericCellValue());
					competitor.setScore("Heptathlon Javelin Throw", (int) row.getCell(15).getNumericCellValue());
					competitor.setScore("Heptathlon Long Jump", (int) row.getCell(16).getNumericCellValue());
					competitor.setScore("Heptathlon Shot Put", (int) row.getCell(17).getNumericCellValue());

					// Add the competitor to the list
					competitors.add(competitor);
				}

				workbook.close();
				//close file input stream
				fis.close();
			}

		} catch (Exception e) {
			throw new IOException("Error: " + e.getMessage());
		}

		return competitors;
	}
}