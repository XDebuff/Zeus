//package com.debuff.zeus;
//
//import com.zeus.utils.excel.ExcelParser;
//
//import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.util.List;
//
//class Test {
//    public static void main(String args[]) throws IOException, ExcelParser.ParseException, InvalidFormatException {
//        System.out.println("应用程序入口的main方法");
//        File tempFile = new File("C:\\Users\\Zing\\Desktop\\details.xls");
////传入一个路径产生流再将流传入工具类，返回解析对象，Excel的所有数据就被解析到List<String[]> 里面，遍历list任由你处置。
//        FileInputStream inputStream = new FileInputStream(tempFile);
//        ExcelParser excelParser = new ExcelParser();
//        ExcelParser parser = excelParser.parse(inputStream);
//        List<String[]> datas = parser.getDatas();
//        int a = 1;
//    }
//}
