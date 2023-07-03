package 기본틀;

import java.io.*;
import java.util.*;

public class 순열_기본틀 {

    static int n, r;
    static boolean[] visited;
    static int[] arr;
    static int[] result;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        visited = new boolean[n];
        arr = new int[n];
        result = new int[r];


        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        permutation(0);

    }

    private static void permutation(int depth) {

        // n개 중에 r개를 뽑아야 하므로 r이 되면 리턴(back)
        if (depth == r) {

            for(int i = 0; i <r; i++){

                System.out.print(result[i]+ " ");
            }
            System.out.println();
            return;
        }

        // n개를 전부 탐색을 해야하기 때문에
        // 0 부터 n까지 -> 순열은 순서에 상관이 있기 때문이다.
        // 즉 2 1 / 1 2 는 다른 수이다.
        // 그러나 2 2 / 1 1과 같은 중복을 피하기 위해서 visited를 이용해준다.
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                visited[i] = true;
                result[depth] = arr[i];
                permutation(depth + 1);
                visited[i] = false;
            }
        }
    }
}
