/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sortarray;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortArray {

    private int[] array;

    public SortArray() {
    }

    public SortArray(int[] array) {
        this.array = array;
    }

    // Quicksort
    public void quickSort() {
        quickSort(0, array.length - 1);
    }

    private void quickSort(int low, int high) {
        if (low < high) {
            int pivotIndex = partition(low, high);
            quickSort(low, pivotIndex - 1);
            quickSort(pivotIndex + 1, high);
        }
    }

    private int partition(int low, int high) {
        int pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        return i + 1;
    }

    // Merge Sort
    public void mergeSort() {
        mergeSort(0, array.length - 1);
    }

    private void mergeSort(int left, int right) {
        if (left < right) {
            int middle = left + (right - left) / 2;
            mergeSort(left, middle);
            mergeSort(middle + 1, right);
            merge(left, middle, right);
        }
    }

    private void merge(int left, int middle, int right) {
        int n1 = middle - left + 1;
        int n2 = right - middle;
        int[] L = new int[n1];
        int[] R = new int[n2];
        for (int i = 0; i < n1; ++i) {
            L[i] = array[left + i];
        }
        for (int j = 0; j < n2; ++j) {
            R[j] = array[middle + 1 + j];
        }
        int i = 0, j = 0;
        int k = left;
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                array[k] = L[i];
                i++;
            } else {
                array[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1) {
            array[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            array[k] = R[j];
            j++;
            k++;
        }
    }

    // Heap Sort
    public void heapSort() {
        int n = array.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(n, i);
        }
        for (int i = n - 1; i > 0; i--) {
            int temp = array[0];
            array[0] = array[i];
            array[i] = temp;
            heapify(i, 0);
        }
    }

    private void heapify(int n, int i) {
        int largest = i;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        if (left < n && array[left] > array[largest]) {
            largest = left;
        }
        if (right < n && array[right] > array[largest]) {
            largest = right;
        }
        if (largest != i) {
            int swap = array[i];
            array[i] = array[largest];
            array[largest] = swap;
            heapify(n, largest);
        }
    }

    // Insertion Sort
    public void insertionSort() {
        for (int i = 1; i < array.length; i++) {
            int key = array[i];
            int j = i - 1;

            // Di chuyển các phần tử của array[0..i-1], lớn hơn key
            // một vị trí về phía sau của chúng
            while (j >= 0 && array[j] > key) {
                array[j + 1] = array[j];
                j = j - 1;
            }
            array[j + 1] = key;
        }
    }

    // Phương thức Bucket Sort
    public void bucketSort() {
        int maxVal = getMax();
        int minVal = getMin();

        // Số lượng buckets
        int bucketCount = Math.max((maxVal - minVal) / array.length + 1, 1);
        List<List<Integer>> buckets = new ArrayList<>(bucketCount);
        for (int i = 0; i < bucketCount; i++) {
            buckets.add(new ArrayList<>());
        }

        // Phân phối các phần tử vào các buckets
        int k, i, size;
        for (int item : array) {
            k = (item - minVal) / array.length;
            buckets.get(k).add(item);
        }

        // Sắp xếp các buckets và ghép lại
        int currentIndex = 0;
        for (List<Integer> bucket : buckets) {
            Collections.sort(bucket);
            for (int item : bucket) {
                array[currentIndex++] = item;
            }
        }
    }

    // Phương thức trợ giúp để tìm giá trị nhỏ nhất trong mảng
    private int getMin() {
        int min = array[0];
        for (int value : array) {
            if (value < min) {
                min = value;
            }
        }
        return min;
    }

    //Radix Sort
    // Utility function to get maximum value in array[]
    private int getMax() {
        int mx = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] > mx) {
                mx = array[i];
            }
        }
        return mx;
    }

    // A function to do counting sort of array[] according to
    // the digit represented by exp. (exp is 10^i where i is current digit position)
    private void countSort(int exp) {
        int output[] = new int[array.length]; // output array
        int i;
        int count[] = new int[10];
        java.util.Arrays.fill(count, 0);

        // Store count of occurrences in count[]
        for (i = 0; i < array.length; i++) {
            count[(array[i] / exp) % 10]++;
        }

        // Change count[i] so that count[i] now contains
        // actual position of this digit in output[]
        for (i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array
        for (i = array.length - 1; i >= 0; i--) {
            output[count[(array[i] / exp) % 10] - 1] = array[i];
            count[(array[i] / exp) % 10]--;
        }

        // Copy the output array to array[], so that array[] now
        // contains sorted numbers according to current digit
        for (i = 0; i < array.length; i++) {
            array[i] = output[i];
        }
    }

    // The main function to that sorts array[] of size n using Radix Sort
    public void radixSort() {
        // Find the maximum number to know the number of digits
        int m = getMax();

        // Do counting sort for every digit. Note that instead
        // of passing digit number, exp is passed. exp is 10^i
        // where i is current digit number
        for (int exp = 1; m / exp > 0; exp *= 10) {
            countSort(exp);
        }
    }

    // Utility method to print array elements
    public void printArray() {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    // Getter method for the array
    public int[] getArray() {
        return array;
    }
}
