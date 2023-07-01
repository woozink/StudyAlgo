package 백트래킹;
/*
현재 상태에서 가능한 모든 후보군을 따라 들어가며 탐색하는 알고리즘
 */

import java.io.IOException;
import java.io.*;
import java.util.*;

public class N과M_1 {

    static int n;
    static int m;
    static int[] arr = new int[10];
    static boolean[] isuued = new boolean[10];


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 4개 중 2개를 중복 없이 M개를 고른 수열
        // 비어있는 리스트에서 시작해 수를 하나씩 추가하면서 길이가 M인 수열이 완성되면 출력하는 방식으로 구현할 수 있다.

        back(0);
    }

    private static void back(int k) {

        // m개를 골랐다면,
        if (k == m) {
            for (int i = 0; i < m; i++) {
                System.out.print(arr[i] + " ");
            }
            System.out.println();
        }


        for(int i = 1; i <= n; i++){
            if(!isuued[i]){
                arr[k] = i;
                isuued[i] = true;
                back(k+1);
                isuued[i] = false;
            }
        }

    }
}
