package com.project.online_book_store.helper;

import com.project.online_book_store.entity.Medicine;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Helper {

    public static boolean checkExcelFormat(MultipartFile file){
        String contentType = file.getContentType();
        return contentType!=null && contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public static List<Medicine> convertExcelToListOfMedicine(InputStream is) {
        List<Medicine> list = new ArrayList<>();
        try (XSSFWorkbook workbook = new XSSFWorkbook(is)) {
            XSSFSheet sheet = workbook.getSheetAt(0);

            if (sheet == null) {
                throw new RuntimeException("Excel file doesn't contain any sheets");
            }

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // skip header

                Medicine medicine = new Medicine();
                for (Cell cell : row) {
                    switch (cell.getColumnIndex()) {
                        case 0:
                            medicine.setName(getCellValueAsString(cell));
                            break;
                        case 1:
                            medicine.setManufacturer(getCellValueAsString(cell));
                            break;
                        case 2:
                            medicine.setPrice(BigDecimal.valueOf(parseCellAsDouble(cell)));
                            break;
                        case 3:
                            medicine.setStockQuantity((int) parseCellAsDouble(cell));
                            break;
                        case 4:
                            medicine.setManufactureDate(parseCellAsLocalDate(cell));
                            break;
                        case 5:
                            medicine.setExpiryDate(parseCellAsLocalDate(cell));
                            break;
                        case 6:
                            break;
                        default:
                            break;
                    }
                }
                if (medicine.getName() != null) {
                    list.add(medicine);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse Excel file: " + e.getMessage());
        }
        return list;
    }

    public static String getCellValueAsString(Cell cell){
        return cell.getCellType() == CellType.STRING ?
                cell.getStringCellValue() :
                String.valueOf(cell.getNumericCellValue());
    }

    private static LocalDate parseCellAsLocalDate(Cell cell){
        if (cell.getCellType()==CellType.NUMERIC){
            return cell.getLocalDateTimeCellValue().toLocalDate();
        } else if (cell.getCellType()==CellType.STRING){
            return LocalDate.parse(cell.getStringCellValue());
        }
        return null;
    }

    private static double parseCellAsDouble(Cell cell){
        if (cell.getCellType()==CellType.NUMERIC){
            return cell.getNumericCellValue();
        } else if (cell.getCellType()==CellType.STRING) {
            return Double.parseDouble(cell.getStringCellValue());
        }
        return 0;
    }

}
