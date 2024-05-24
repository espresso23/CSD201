/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortarray;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Welcome
 */
public class Main {

    public static void main(String[] args) {
        int size;
        int MAX = 100000000;
        Random random = new Random();
        //Sinh dãy số ngẫu nhiên theo size nhập vào
        System.out.print("Input the size of Array:");
        Scanner sc = new Scanner(System.in);
        size = sc.nextInt();
        int[] original = new int[size];
        for (int i = 0; i < size; i++) {
            original[i] = random.nextInt(MAX) + 1;
        }

        System.out.println("Arrange " + size + " random integers by sorting algorithms:");
        long startTime, endTime;      //khai báo biến để tính thời gian chạy

//        SortArray is = new SortArray(original);
//        startTime = System.currentTimeMillis();
//        is.insertionSort();
////                  is.printArray();
//        endTime = System.currentTimeMillis();
//        System.out.println("Insertion Sort: " + (endTime - startTime) + " ms");
        SortArray qs = new SortArray(original);
        startTime = System.currentTimeMillis();
        qs.quickSort();
//                  qs.printArray();
        endTime = System.currentTimeMillis();
        System.out.println("Quick Sort: " + (endTime - startTime) + " ms");

        SortArray ms = new SortArray(original);
        startTime = System.currentTimeMillis();
        ms.mergeSort();
//                  ms.printArray();
        endTime = System.currentTimeMillis();
        System.out.println("Merge Sort: " + (endTime - startTime) + " ms");

        SortArray hs = new SortArray(original);
        startTime = System.currentTimeMillis();
        hs.heapSort();
//                  hs.printArray();
        endTime = System.currentTimeMillis();
        System.out.println("Heap Sort: " + (endTime - startTime) + " ms");

        SortArray bs = new SortArray(original);
//        bs.printArray();
        startTime = System.currentTimeMillis();
        bs.bucketSort();
//                  bs.printArray();
        endTime = System.currentTimeMillis();
        System.out.println("Bucket Sort: " + (endTime - startTime) + " ms");

        SortArray rs = new SortArray(original);
        startTime = System.currentTimeMillis();
        rs.radixSort();
//                  rs.printArray();
        endTime = System.currentTimeMillis();
        System.out.println("Radix Sort: " + (endTime - startTime) + " ms");

    }
}
