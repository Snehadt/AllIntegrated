package com.utilHandler;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {

    public static String getCellData(String filePath, String sheetName, int rowNumber, int cellNumber) {
        try (FileInputStream fis = new FileInputStream(filePath)) {
            Workbook workbook = WorkbookFactory.create(fis);
            Sheet sheet = workbook.getSheet(sheetName);
            Cell cell = sheet.getRow(rowNumber).getCell(cellNumber);
            return cell.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
