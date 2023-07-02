package 백트래킹;

import java.io.*;
import java.util.*;

public class 로또 {

    static int n;
    static int[] arr;
    static boolean[] visit;
    static int[] result;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());

            if (n == 0) {
                break;
            }

            arr = new int[n];

            //st = new StringTokenizer(br.readLine());
            for (int i = 0; i < n; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }
            result = new int[6];
            visit = new boolean[n];
            back(0, 0);
            sb.append('\n');

        }
        System.out.println(sb);

    }

    private static void back(int start, int depth) {

        if (depth == 6) {
            for (int i = 0; i < result.length; i++) {
                sb.append(result[i]).append(" ");
            }
            sb.append('\n');
            return;
        }


        for(int i = start; i< n; i++){
            if(!visit[i]){
                visit[i] = true;
                result[depth] = arr[i];
                back(i, depth +1);
                visit[i] = false;

            }
        }
    }
}
