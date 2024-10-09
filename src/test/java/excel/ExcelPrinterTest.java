package excel;
import static org.junit.Assert.*;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class ExcelPrinterTest {
    private ExcelPrinter excelPrinter1;
    private ExcelPrinter excelPrinter2;

    @Test
    public void UniqueExcelNameTest() {
        try {
            //Give the same name to both ExcelPrinters
            excelPrinter1 = new ExcelPrinter("test ");
            excelPrinter2 = new ExcelPrinter("test ");

            //Write both files to disk
            excelPrinter1.write();
            Thread.sleep(1000);
            excelPrinter2.write();

            //Get the paths to the files
            String filePath1 = excelPrinter1.getFilePath();
            String filePath2 = excelPrinter2.getFilePath();

            //Check if the files exist
            File file1 = new File(filePath1);
            File file2 = new File(filePath2);

            assertTrue(file1.exists());
            assertTrue(file2.exists());

            //Check if the files have the same name
            assertNotEquals("Filerna har samma namn.", filePath1, filePath2);

        } catch (IOException | InterruptedException ex) {
            fail("Error creating ExcelPrinter: " + ex.getMessage());
        }
    }
}
