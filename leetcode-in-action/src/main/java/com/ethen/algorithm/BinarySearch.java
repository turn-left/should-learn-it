package com.ethen.algorithm;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 二分查找法
 * 数组已排序 {2,3,5,7,10,15,20,27,31,35}
 */
public class BinarySearch {

    public static final int[] SEED = {2, 3, 5, 7, 10, 15, 20, 27, 31, 35};

    public static void main(String[] args) {
        System.err.println(binarySearch(SEED, 27));
        System.err.println(recursionBinarySearch(SEED, 0, SEED.length - 1, 27));
        int[] array2 = {34, 3, 20, 343, 20 - 30, -89, 234, 445};
        sortAsc(array2);
        System.err.println(Arrays.toString(array2));
    }

    /**
     * 循环二分查找实现
     *
     * @param arr 原始数组
     * @param key 带查找数据
     * @return index
     */
    public static int binarySearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int mid = 0;

        while (low <= high) {
            mid = low + high >>> 1;
            if (key > arr[mid]) {
                low = mid + 1;
            } else if (key < arr[mid]) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 递归二分查找实现
     *
     * @param arr 原始数组
     * @param key 带查找数据
     * @return index
     */
    public static int recursionBinarySearch(int[] arr, int start, int end, int key) {
        int mid = start + end >>> 1;
        if (arr[mid] == key) return mid;
        if (key > arr[mid]) {
            return recursionBinarySearch(arr, mid + 1, end, key);
        } else {
            return recursionBinarySearch(arr, start, mid - 1, key);
        }
    }

    /**
     * 给数组排序
     *
     * @param arrayInOutput asc
     */
    public static void sortAsc(int[] arrayInOutput) {
        Objects.requireNonNull(arrayInOutput);
        Arrays.sort(arrayInOutput);
    }
}
