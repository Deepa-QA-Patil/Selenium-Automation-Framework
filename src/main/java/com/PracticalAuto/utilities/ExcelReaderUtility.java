package com.PracticalAuto.utilities;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReaderUtility {

    public static List<String[]> getSheetData(String filePath, String sheetName)
            throws IOException {

        List<String[]> data = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = new XSSFWorkbook(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                throw new IllegalArgumentException(
                        "Sheet '" + sheetName + "' does not exist");
            }

            // Get total columns from header row (Row 0)
            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                throw new IllegalArgumentException("Header row is missing in sheet: " + sheetName);
            }

            int columnCount = headerRow.getLastCellNum();

            // Start reading from row 1 (skip header)
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);

                if (row == null || isRowEmpty(row, columnCount)) {
                    continue; // ✅ Skip empty or blank rows
                }

                List<String> rowData = new ArrayList<>();

                for (int j = 0; j < columnCount; j++) {

                    Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    rowData.add(getCellValue(cell));
                }

                data.add(rowData.toArray(new String[0]));
            }
        }

        return data;
    }
    
    private static boolean isRowEmpty(Row row, int columnCount) {

        for (int j = 0; j < columnCount; j++) {

            Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);

            if (cell.getCellType() != CellType.BLANK &&
                !getCellValue(cell).trim().isEmpty()) {

                return false; // Found some data
            }
        }

        return true; // All cells empty
    }

    private static String getCellValue(Cell cell) {

        if (cell == null) {
            return "";
        }

        switch (cell.getCellType()) {

            case STRING:
                return cell.getStringCellValue().trim();

            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getDateCellValue().toString();
                }
                return String.valueOf(cell.getNumericCellValue());

            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());

            case FORMULA:
                return cell.getCellFormula();

            case BLANK:
                return "";

            default:
                return "";
        }
    }
}