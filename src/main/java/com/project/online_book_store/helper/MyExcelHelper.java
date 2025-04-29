package com.project.online_book_store.helper;

import com.project.online_book_store.entity.Book;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MyExcelHelper {

    public static boolean checkExcelFormat(MultipartFile file) {
        String contentType = file.getContentType();
        return contentType != null && contentType.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public static List<Book> convertExcelToListOfBook(InputStream is) {
        List<Book> list = new ArrayList<>();
        try (XSSFWorkbook workbook = new XSSFWorkbook(is)) {
            XSSFSheet sheet = workbook.getSheetAt(0);

            if (sheet == null) {
                throw new RuntimeException("Excel file doesn't contain any sheets");
            }

            for (Row row : sheet) {
                if (row.getRowNum() == 0) continue; // Skip header

                Book book = new Book();
                for (Cell cell : row) {
                    switch (cell.getColumnIndex()) {
                        case 0:
                            book.setBookId((int) cell.getNumericCellValue());
                            break;
                        case 1:
                            book.setTitle(getCellValueAsString(cell));
                            break;
                        case 2:
                            book.setAuthor(getCellValueAsString(cell));
                            break;
                        case 3:
                            book.setGenre(getCellValueAsString(cell));
                            break;
                        case 4:
                            book.setPrice(cell.getNumericCellValue());
                            break;
                        case 5:
                            book.setAvailability((int) cell.getNumericCellValue());
                            break;
                    }
                }
                if (book.getBookId() != 0) { // Basic validation
                    list.add(book);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse Excel file: " + e.getMessage());
        }
        return list;
    }

    private static String getCellValueAsString(Cell cell) {
        return cell.getCellType() == CellType.STRING ?
                cell.getStringCellValue() :
                String.valueOf(cell.getNumericCellValue());
    }
}
