package Bfs;

import java.io.*;
import java.util.*;

class Tomato {
    int x;
    int y;
    int cnt;

    public Tomato(int x, int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}

public class 토마토 {
    static int n, m;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static Queue<Tomato> q = new LinkedList<>();
    static int arr[][];
    static int result = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        arr = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
                if (arr[i][j] == 1) {
                    q.add(new Tomato(i, j, 0));
                }
            }
        }


        bfs();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (arr[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }
            }
        }

        System.out.println(result);
    }

    private static void bfs() {

        while (!q.isEmpty()) {

            Tomato node = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dr[i];
                int ny = node.y + dc[i];
                int cnt = node.cnt;

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }

                if (arr[nx][ny] != 0) {
                    continue;
                }

                arr[nx][ny] = 1;
                q.add(new Tomato(nx, ny, cnt + 1));


            }

            result = Math.max(result, node.cnt);

        }


    }
}
