package com.zeus.utils.excel;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***************************************************
 * Author: Debuff 
 * Data: 2019/3/21
 * Description: 
 ***************************************************/
public class ExcelProcessor {

    private String TAG = "ExcelProcessor";
    private String filePath;
    private int mFirstRowNum;
    private int mSheetNum;

    public ExcelProcessor() {
        super();
    }

    public ExcelProcessor(String filePath) {
        super();
        this.filePath = filePath;
    }

    public <D, T> Map<D, T> readExcelMap(Class<D> idClass, Class<T> dataClass) {
        Map<D, T> result = new HashMap<>();
        try {
            //读取Excel
            Workbook workbook = ExcelUtils.getWorkbookCompat(filePath);
            System.out.print("开始遍历" + filePath + "文件");
            if (workbook == null) {
                return null;
            }
            Sheet sheet = workbook.getSheetAt(mSheetNum);
            if (sheet == null) {
                return null;
            }
            List<Object> rowDatum = new ArrayList<>();
            for (int i = mFirstRowNum; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                List<Object> rowData = ExcelUtils.getRowData(row);
                rowDatum.addAll(rowData);
                //循环查看属性上的注解
                T data = dataClass.newInstance();
                //获取类的所有属性
                Field[] fields = dataClass.getDeclaredFields();
                D id = null;
                for (Field field : fields) {
                    ExcelCell annotation = field.getAnnotation(ExcelCell.class);
                    if (annotation == null) {
                        continue;
                    }
//                    System.out.println(annotation.type() == 1 ? "列为基础" : "行为基础");
                    System.out.print("当前遍历第" + (i + 1) + "行" + ",");
                    System.out.println("第" + annotation.value() + "列");
//                    int cellNum = ExcelUtils.toColumnIndex(annotation.value());
                    int cellNum = 0;
                    if (cellNum == -1) {
                        continue;
                    }
                    boolean isID = annotation.id();
                    if (isID) {
                        id = (D) rowData.get(cellNum);
                    }
                    //读取Excel的一行值
                    field.setAccessible(true);
                    field.set(data, rowData.get(cellNum));
                }
                result.put(id, data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //初始化实体类
        return result;
    }

    public <T> List<T> readExcel(Class<T> clazz) {
        if (filePath == null || "".equals(filePath)) {
            throw new IllegalArgumentException("filePath is null or empty");
        }
        List<T> result = new ArrayList<>();
        try {
            //读取Excel
            Workbook workbook = ExcelUtils.getWorkbookCompat(filePath);
            System.out.print("开始遍历" + filePath + "文件");
            if (workbook == null) {
                return null;
            }
            Sheet sheet = workbook.getSheetAt(mSheetNum);
            if (sheet == null) {
                return null;
            }
            for (int i = mFirstRowNum; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                List<Object> rowData = ExcelUtils.getRowData(row);
                //循环查看属性上的注解
                T data = clazz.newInstance();
                //获取类的所有属性
                Field[] fields = clazz.getDeclaredFields();
                for (Field field : fields) {
                    ExcelCell annotation = field.getAnnotation(ExcelCell.class);
                    if (annotation == null) {
                        continue;
                    }
//                    System.out.println(annotation.type() == 1 ? "列为基础" : "行为基础");
                    System.out.print("当前遍历第" + (i + 1) + "行" + ",");
                    System.out.println("第" + annotation.value() + "列");
                    int cellNum = Hex26.compareHex26(annotation.value());
                    if (cellNum == -1) {
                        continue;
                    }
                    //读取Excel的一行值
                    field.setAccessible(true);
                    field.set(data, rowData.get(cellNum));
                }
                result.add(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //初始化实体类
        return result;
    }

    public void setFirstRowNum(int firstRowNum) {
        mFirstRowNum = firstRowNum;
    }

    public void setSheetNum(int sheetNum) {
        mSheetNum = sheetNum;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
