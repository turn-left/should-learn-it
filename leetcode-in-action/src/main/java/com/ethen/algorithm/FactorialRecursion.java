package com.ethen.algorithm;

/**
 * 用递归计算 n！
 */
public class FactorialRecursion {

    /**
     * 计算n的阶乘
     *
     * @param n
     * @return
     */
    public static long recursion(long n) {
        if (n <= 1)
            return n;
        else
            return n * recursion(n - 1);
    }

    public static void main(String[] args) {
        System.err.println(recursion(10));
        System.err.println(recursion(15));
    }

}
