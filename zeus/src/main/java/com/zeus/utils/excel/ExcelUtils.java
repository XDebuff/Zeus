package com.zeus.utils.excel;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/***************************************************
 * Author: Debuff 
 * Data: 2017/5/21
 * Description: 
 ***************************************************/
public class ExcelUtils {

    private static void readExcel(String filePath) {
        System.out.println(filePath);
        try {
            Workbook workbook = getWorkbookCompat(filePath);
            if (workbook == null) {
                return;
            }
            int totalSheetNum = 1;
            for (int sheetNum = 0; sheetNum < totalSheetNum; sheetNum++) {
                Sheet sheet = workbook.getSheetAt(sheetNum);
                if (sheet == null) {
                    continue;
                }
                // 获取行(从0开始计数)
                int totalRowNum = sheet.getLastRowNum();
                System.out.println("行数：" + (totalRowNum + 1));
                //渠道文件的第一行为说明，第二行起为渠道数据
                for (int rowNum = 1; rowNum <= totalRowNum; rowNum++) {
                    Row row = sheet.getRow(rowNum);
                    if (row != null) {
                        // 获取第一列和第二列
                        Cell chanelCode = row.getCell(1);
                        Cell chanelName = row.getCell(2);
                        // 添加##'
                        if (chanelCode != null && chanelName != null) {
                            String code = "";
                            String name = "";
//                            String code = NotificationLite.getValue(chanelCode);
//                            String name = NotificationLite.getValue(chanelName);
                            if (!"".equals(code) && !"".equals(name)) {
                                String result = code + "##" + name;
                            } else {
                                System.out.println("空数据，行数：" + (rowNum + 1));
                            }
                        } else {
                            System.out.println("空行，行数：" + (rowNum + 1));
                        }
                    } else {
                        System.out.println("NULL行，行数：" + (rowNum + 1));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Workbook getWorkbookCompat(String filePath) {
        if ("".equals(filePath) || filePath == null) {
            return null;
        }
        InputStream inputStream;
        try {
            inputStream = new FileInputStream(filePath);
            String[] fileStrArr = filePath.split("\\.");
            String ext = fileStrArr[fileStrArr.length - 1];
            if ("xls".equals(ext)) {
                return new HSSFWorkbook(inputStream);
            } else if ("xlsx".equals(ext)) {
                return new XSSFWorkbook(inputStream);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    public static List<Object> getRowData(Row row) {
        int firstCellNum = row.getFirstCellNum();
        int lastCellNum = row.getLastCellNum();
        List<Object> datum = new ArrayList<>();
        for (int j = firstCellNum; j < lastCellNum; j++) {
            Cell cell = row.getCell(j);
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_BOOLEAN:
                    datum.add(cell.getBooleanCellValue());
                    break;
                case Cell.CELL_TYPE_NUMERIC:
                    datum.add(cell.getNumericCellValue());
                    break;
                case Cell.CELL_TYPE_STRING:
                    datum.add(cell.getStringCellValue());
                    break;
            }
        }
        return datum;
    }

    public static Workbook createExcel(String filePath, Workbook workbook) {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            workbook.write(fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return workbook;
    }


    public static void createSheet(String filePath) {
        Workbook workbook = new HSSFWorkbook();
        try {
            workbook.createSheet("1");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createSheet(Workbook workbook, String sheetStr) {
        try {
            workbook.createSheet(sheetStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
