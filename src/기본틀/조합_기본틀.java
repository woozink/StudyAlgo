package 기본틀;

import java.io.*;
import java.util.*;

public class 조합_기본틀 {

    static int n, r;
    static int[] arr;
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        arr = new int[n];
        result = new int[r];

        back(0, 1);


    }

    private static void back(int depth, int start) {

        // r개를 다 골랐다면.
        if (depth == r) {
            for (int i = 0; i < r; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
            return;
        }

        // 시작점 depth = 0, start =1;

        for (int i = start; i <= n; i++) {
            arr[depth] = i;
            back(depth + 1, i + 1);
        }
    }


}


