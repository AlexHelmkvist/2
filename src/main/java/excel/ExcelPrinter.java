package excel;

import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


public class ExcelPrinter {

	private XSSFWorkbook workbook;
	private String excelName;

	public ExcelPrinter(String name) throws IOException {
		workbook = new XSSFWorkbook();
		excelName = name;
	}

	public void add(Object[][] data, String sheetName) {

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
	}

	public void write() throws IOException {
		String filePath; // Deklarera filvägen
		String os = System.getProperty("os.name").toLowerCase(); // Hämtar operativsystemets namn och gör det till små bokstäver

		// Kontrollera vilket operativsystem som används
		if (os.contains("win")) {
			filePath = "C:/Eclipse/resultat_" + excelName + ".xlsx"; // För Windows
		} else if (os.contains("mac")) { // För Mac
			filePath = System.getProperty("user.home") + "/Desktop/resultat_" + excelName + ".xlsx";
		} else {
			throw new IOException("Unsupported operating system."); // Felhantering för andra operativsystem
		}


		FileOutputStream out = new FileOutputStream(filePath);
		workbook.write(out);
		out.close();
	}}