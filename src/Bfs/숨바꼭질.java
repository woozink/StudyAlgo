package Bfs;

import java.io.*;
import java.util.*;

public class 숨바꼭질 {

    static int n, m;
    static Queue<Integer> q = new LinkedList<>();
    static int[] check = new int[100001];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // BFS 시작점 문제

        if (n == m) {
            System.out.println(0);
        } else {
            bfs();
        }

    }

    private static void bfs() {
        q.add(n);

        check[n] = 1;


        while (!q.isEmpty()) {
            int tmp = q.poll();

            for (int i = 0; i < 3; i++) {
                int next;


                if (i == 0) {
                    next = tmp + 1;
                } else if (i == 1) {
                    next = tmp - 1;
                } else {
                    next = 2 * tmp;
                }

                if (next == m) {
                    System.out.println(check[tmp]);
                    return;
                }

                if (next < 0 || next >= check.length || check[next] != 0) {
                    continue;
                }

                q.add(next);
                check[next] = check[tmp] + 1;
            }
        }
    }

}
