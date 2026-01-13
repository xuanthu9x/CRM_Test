package helper;
import java.awt.Color;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

public class ExcelHelper {
    private FileInputStream fileIn;
    private FileOutputStream fileOut;
    private Workbook workbook;
    private Sheet sheet;
    private Cell cell;
    private Row row;
    private CellStyle cellstyle;
    private Color mycolor;
    private String excelFilePath;
    private Map<String, Integer> columns = new HashMap<>();

    public void setExcelFile(String ExcelPath, String SheetName){
        try {
            File f = new File(ExcelPath);

            if (!f.exists()) {
                System.out.println("File doesn't exist.");
            }

            fileIn = new FileInputStream(ExcelPath); //read file input stream
            workbook = WorkbookFactory.create(fileIn); //create workbook instance
            sheet = workbook.getSheet(SheetName); //create sheet instance

            //check if sheet name exists
            if (sheet == null) {
                throw new Exception("Sheet" + sheet +" name doesn't exist.");
            }
            //set the excel file path
            this.excelFilePath = ExcelPath;

            //adding all the column header names to the map 'columns'
            sheet.getRow(0).forEach(cell ->{
                columns.put(cell.getStringCellValue(), cell.getColumnIndex());
            });

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public String getCellData(int columnIndex, int rowIndex) {
        try {
            cell = sheet.getRow(rowIndex).getCell(columnIndex); //Lấy dữ liệu từ cột và hàng
            String CellData = null;
            switch (cell.getCellType()) {
                case STRING:
                    CellData = cell.getStringCellValue();
                    break;
                case NUMERIC:
                    if (DateUtil.isCellDateFormatted(cell)) { //Kiểm tra nếu là định dạng ngày tháng
                        CellData = String.valueOf(cell.getDateCellValue()); //Trả về định dạng ngày tháng
                    } else {
                        CellData = String.valueOf((long) cell.getNumericCellValue()); //Trả về định dạng số có ép kiểu từ double sang long
                    }
                    break;
                case BOOLEAN:
                    CellData = Boolean.toString(cell.getBooleanCellValue());
                    break;
                case BLANK:
                    CellData = "";
                    break;
            }
            return CellData;
        } catch (Exception e) {
            return "";
        }
    }

    //Gọi ra hàm này nè
    public String getCellData(String columnName, int rowIndex) {
        if (columns.get(columnName) == null) {
            throw new RuntimeException("Column '" + columnName + "' does not exist in the Excel sheet.");
        }
        return getCellData(columns.get(columnName), rowIndex);
    }

    //set by column index
    public void setCellData(String text, int columnIndex, int rowIndex) {
        try {
            row = sheet.getRow(rowIndex);
            if (row == null) {
                row = sheet.createRow(rowIndex);
            }
            cell = row.getCell(columnIndex);

            if (cell == null) {
                cell = row.createCell(columnIndex);
            }
            cell.setCellValue(text);

            XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
            style.setFillPattern(FillPatternType.NO_FILL);
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setVerticalAlignment(VerticalAlignment.CENTER);

            cell.setCellStyle(style);

            fileOut = new FileOutputStream(excelFilePath);
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    //set by column name
    public void setCellData(String text, String columnName, int rowIndex) {
        try {
            row = sheet.getRow(rowIndex);
            if (row == null) {
                row = sheet.createRow(rowIndex);
            }
            cell = row.getCell(columns.get(columnName));

            if (cell == null) {
                cell = row.createCell(columns.get(columnName));
            }
            cell.setCellValue(text);

            XSSFCellStyle style = (XSSFCellStyle) workbook.createCellStyle();
            style.setFillPattern(FillPatternType.NO_FILL);
            style.setAlignment(HorizontalAlignment.CENTER);
            style.setVerticalAlignment(VerticalAlignment.CENTER);

            cell.setCellStyle(style);

            fileOut = new FileOutputStream(excelFilePath);
            workbook.write(fileOut);
            fileOut.flush();
            fileOut.close();
        } catch (Exception e) {
            e.getMessage();
        }
    }
}
