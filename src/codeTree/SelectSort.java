package codeTree;

import java.util.Scanner;

public class SelectSort {
    public static final int MAX_N = 100;

    public static int n;
    public static int[] arr = new int[MAX_N];

    public static void selectionSort() {
        for(int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for(int k = i + 1; k < n; k++)
                if(arr[minIndex] > arr[k])
                    minIndex = k;

            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 입력
        n = sc.nextInt();
        for(int i = 0; i < n; i++)
            arr[i] = sc.nextInt();

        selectionSort();

        for(int i = 0; i < n; i++)
            System.out.print(arr[i] + " ");
    }
}