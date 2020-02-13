package com.zeus.utils.java;

import java.util.Random;

/***************************************************
 * Author: Debuff
 * Data: 2019/7/8
 * Description:
 ***************************************************/
public class Test {
    public static void main(String[] args) {
        for (int i = 0; i <= 100; i++) {
            String sources = "0123456789"; // 加上一些字母，就可以生成pc站的验证码了
            Random rand = new Random();
            StringBuffer flag = new StringBuffer();
            for (int j = 0; j < 6; j++) {
                flag.append(sources.charAt(rand.nextInt(9)) + "");
            }
            System.out.println(flag.toString());
        }
    }

}
