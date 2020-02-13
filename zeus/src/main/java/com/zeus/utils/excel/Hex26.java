package com.zeus.utils.excel;

import java.util.Scanner;

public class Hex26 {

    private static char A = 'A';


    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("请确认：");
        if (input.hasNextLine() && input.nextLine().toUpperCase().equals("Y")) {
            System.out.print("请输入Excel文件的列名（例:A,AA,AB...）：");
            if (input.hasNextLine()) {
                System.out.println("转换后的Index为：" + compareHex26(input.nextLine()));
            }
            System.out.print("请输入Excel列索引：（例:1,12,123...）：");
            if (input.hasNextInt()) {
                System.out.println(toHex26(Integer.parseInt(input.nextLine())));
            }
        }
        input.close();
    }

    public static int compareHex26(String hex) {
        hex = hex.toUpperCase();
        int result = 0, j = 0;
        for (; j < hex.length(); j++) {
            char c = hex.charAt(j);
            int diff = c - A;
            result += (diff + 1) * Math.pow(26, hex.length() - j - 1);
        }
        return result - 1;
    }

    public static String toHex26(int i) {
        StringBuilder sb = new StringBuilder();
        i = i + 1;
        if (i == 0) {
            sb.append(A);
            return sb.toString();
        }
        while (i != 0) {
            int m = i % 26;
            char c = (char) (m + 64);
            sb.insert(0, c);
            i = (i - m) / 26;
        }
        return sb.toString();
    }

}
