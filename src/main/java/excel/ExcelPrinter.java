package excel;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelPrinter {

	private XSSFWorkbook workbook;
	private String excelName;
	private String filePath;

	public ExcelPrinter(String name) throws IOException {
		workbook = new XSSFWorkbook();
		excelName = name;
	}

	public void add(Object[][] data, String sheetName) throws IOException {

		try {

			XSSFSheet sheet = workbook.createSheet(sheetName);

			int rowCount = 0;

			for (Object[] aBook : data) {
				Row row = sheet.createRow(rowCount);
				rowCount++;
				int columnCount = 0;

				for (Object field : aBook) {
					Cell cell = row.createCell(columnCount);
					columnCount++;

					if (field instanceof String) {
						cell.setCellValue((String) field);

					} else if (field instanceof Integer) {
						cell.setCellValue((Integer) field);

					} else if (field instanceof Double) {
						cell.setCellValue((Double) field);

					}

				}

			}

		} catch (IllegalArgumentException e) {
			throw new IOException(e.getMessage());  // This exception is thrown if there is an error reading the file
		}
	}
	public void write() throws IOException {
		//String filePath; Deklarera filvägen
		String os = System.getProperty("os.name").toLowerCase(); // Hämtar operativsystemets namn och gör det till små bokstäver

		//Gets the current date and time on the local machine
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh-mm-ss");
		String time = LocalDateTime.now().format(formatter);

		// Kontrollera vilket operativsystem som används
		if (os.contains("mac")) {
			filePath = System.getProperty("user.home") + "/Desktop/resultat_" + excelName + time + ".xlsx";
		} else {
			filePath = "C:/Eclipse/resultat_" + excelName + time + ".xlsx"; // För Windows
		}

		FileOutputStream out = new FileOutputStream(filePath);
		workbook.write(out);
		out.close();
	}

	//Get method
	public String getFilePath() {
		return filePath;
	}
}