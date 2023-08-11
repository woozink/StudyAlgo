package codeTree;

import java.util.*;

public class 스승의은혜2 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);


        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {

                int cnt = 0;
                int sum = 0;
                int a = 0;
                for (int k = i; k <= j; k++) {
                    if (k == j) {
                        a = arr[k] / 2;
                    }else{
                        a = arr[k];
                    }
                    sum += a;
                    if (sum <= m) cnt++;

                    count = Math.max(cnt, count);
                }
            }
        }

        System.out.println(count);

    }
}