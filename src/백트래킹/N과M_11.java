package 백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class N과M_11 {

    static int n, m;
    static int[] arr;
    static int[] num;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];
        num = new int[n];

        HashSet<Integer> set = new HashSet<>();
        int idx = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int value = Integer.parseInt(st.nextToken());
            if (set.contains(value)) {
                continue;
            }
            set.add(value);
            num[idx++] = value;
        }
        n = idx;
        Arrays.sort(num, 0, n);
        back(0,0);
        System.out.println(sb);
    }

    private static void back(int index, int st) {

        // m개를 골랐다면,
        if (index == m) {
            for (int i = 0; i < m; i++) {
                sb.append(arr[i]).append(' ');
            }
            sb.append('\n');
            return;
        }
        int tmp = -1;
        for (int i = st; i < n; i++) {
            if(num[i] != tmp){

                arr[index] = num[i];
                tmp = arr[index];
                back(index + 1, i);
            }
        }
    }
}
