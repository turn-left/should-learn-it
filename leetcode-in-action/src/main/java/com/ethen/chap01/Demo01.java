package com.ethen.chap01;

public class Demo01 {
    public static void main(String[] args) {
        System.err.println("杨".hashCode());
        System.err.println("杨逍".hashCode());
        char ch = '假';
        System.err.println((int) ch);
        ok:
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 200; j++) {
                break ok;
            }
        }
        Object str = "a";
        int hashCode = str.hashCode();
        System.err.println(hashCode);
    }
}
