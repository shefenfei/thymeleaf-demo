package com.fisher.mybatis.demo.jvmdemo;

import com.fisher.mybatis.demo.model.Country;

import java.util.ArrayList;

public class Demo1 {




    public static void main(String[] args) {
//        test1();

//        test2();
        int count = 0;

        String s = "s";

        try {
            ArrayList<String> strings = new ArrayList<>();
            while (true) {
                count ++;
                s += "aaa";
                strings.add(s);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(count);
        }
    }

    private static void test2() {
        ArrayList<Country> countries = new ArrayList<>();
        try {
            for (long i = 0; i < 10000; i++) {
                Country country = new Country();
                country.setCountryCode("code"+ i);
                country.setCountryName("name" + i);
                country.setId(i);
                countries.add(country);
            }
            System.out.println(countries.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void test1() {
        int count = 1;
        try {
            method3(count);
        } catch (Exception e) {
//            e.printStackTrace();
            System.out.println(count);
        }
    }


    private static void  method1(int a, int b) {
        a += 1;
        b += 1;
        method2(a, b);
    }


    private static void method2(int a, int b) {
        a += 1;
        b += 1;
        System.out.println(a + "...." + b);
    }

    private static void method3(int count) {
        count ++ ;
        method3(count);
    }


}

