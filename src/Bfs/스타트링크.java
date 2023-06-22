package Bfs;

import java.io.*;
import java.util.*;

public class 스타트링크 {
    static int f, s, g, u, d;
    static Queue<Integer> q = new LinkedList<>();
    static int count;
    static boolean[] check;
    static int[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        f = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken()); // start
        g = Integer.parseInt(st.nextToken()); // end
        u = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        count = 0;
        arr = new int[1000001];
        check = new boolean[1000001];

        System.out.println(bfs());

    }

    private static String bfs() {

        q.add(s);
        arr[s] = 1;

        while (!q.isEmpty()) {
            int current = q.poll();

            if (current == g) {
                return String.valueOf(arr[current]-1);
            }

            // up 이동 위치가 최대값 보다 작고 방문 x
            if (current + u <= f) {
                if (arr[current + u] == 0) {
                    arr[current + u] = arr[current] + 1;
                    q.add(current + u);
                }
            }

            if (current - d > 0) {
                if (arr[current - d] == 0) {
                    arr[current - d] = arr[current] + 1;
                    q.add(current - d);
                }
            }


        }
        return "use the stairs";

    }
}
