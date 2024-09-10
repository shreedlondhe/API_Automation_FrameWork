package Tests.exelSheet;

import DynamicVariables.DynamicVarialbles;
import com.github.javafaker.Faker;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZonedDateTime;

public class AddToExcel {


public static void addIdToSheet(){
    if(!DynamicVarialbles.policyNumber.equals(null)){
        logic();
        System.out.println("ID Added to Sheet");
    }
}



    static void logic() {
        String currentDate = ZonedDateTime.now().toString();
        Faker faker = new Faker();
       // String randomId = faker.idNumber().valid();

        System.out.println("date is  "+currentDate);

        String filePath = "/Users/shrinivaslondhe/Desktop/APIAutomation/src/test/java/Tests/exelSheet/fab.xlsx"; // Path to the existing Excel file

        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fileInputStream)) {

            // Get the existing sheet or create a new one
            Sheet sheet = workbook.getSheet("Sheet1");
            if (sheet == null) {
                sheet = workbook.createSheet("Sheet1");
            }

            // Create a new row and put some cells in it
            int lastRowNum = sheet.getLastRowNum();
            Row row = sheet.createRow(lastRowNum + 1);

            Cell cell1 = row.createCell(0);
            cell1.setCellValue(currentDate);

            Cell cell2 = row.createCell(1);
           cell2.setCellValue(DynamicVarialbles.policyNumber);
           // cell2.setCellValue(randomId);

            // Write the output to the same file
            try (FileOutputStream fileOut = new FileOutputStream(filePath)) {
                workbook.write(fileOut);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}
