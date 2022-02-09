package club.neters.common.util.excel;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ExcelUtil {
    private static DateFormat shortPattern = new SimpleDateFormat("yyyy-MM-dd");

    public static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        if (cell.getCellTypeEnum() == CellType.NUMERIC) {
            return new BigDecimal(String.valueOf(cell.getNumericCellValue())).stripTrailingZeros().toPlainString();
        }
        return cell.getStringCellValue();
    }

    public static String getDateCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        if (cell.getCellTypeEnum() == CellType.NUMERIC) {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            return format.format(cell.getDateCellValue());
        }
        return cell.getStringCellValue();
    }

    public static Date getDateCellValueByCell(Cell cell) throws ParseException {
        if (cell == null) {
            return null;
        }
        if (cell.getCellTypeEnum() == CellType.NUMERIC) {
            return cell.getDateCellValue();
        }
        if (cell.getCellTypeEnum() == CellType.STRING) {
            shortPatternStringToDate(cell.getStringCellValue());
        }
        return cell.getDateCellValue();
    }

    public static Workbook getWorkbookTemplate(String sheetName, List<String> columnNameList) {
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet(sheetName);
        sheet.createRow(0);
        for (int i = 0; i < columnNameList.size(); i++) {
            sheet.getRow(0).createCell(i).setCellValue(columnNameList.get(i));
        }
        return workbook;
    }

    public static Workbook translateMultipartFileToWorkbook(MultipartFile file) throws IOException,
            InvalidFormatException {
        return WorkbookFactory.create(file.getInputStream());
    }

    public static boolean checkTemplateHead(Workbook workbook, List<String> columnNameList) {
        if (workbook == null || workbook.getSheetAt(0) == null || workbook.getSheetAt(0).getRow(0) == null) {
            return false;
        }
        Row row = workbook.getSheetAt(0).getRow(0);
        for (int i = 0; i < columnNameList.size(); i++) {
            if (row.getCell(i) == null || !columnNameList.get(i).equals(row.getCell(i).getStringCellValue())) {
                return false;
            }
        }
        return true;
    }

    public static Date shortPatternStringToDate(String date) throws ParseException {
        return shortPattern.parse(date);
    }
}
