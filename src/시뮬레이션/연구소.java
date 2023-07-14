package 시뮬레이션;

import java.io.*;
import java.util.*;

public class 연구소 {
    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;

        }
    }

    static int n, m;
    static int map[][];
    static boolean visited[][];
    static int max = Integer.MIN_VALUE;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);

        System.out.println(max);


    }

    private static void dfs(int wall) {

        if (wall == 3) {
            bfs();
            return;
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 0) {
                    map[i][j] = 1;
                    dfs(wall + 1);
                    map[i][j] = 0;
                }
            }
        }
    }

    private static void bfs() {

        Queue<Node> q = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 2) {
                    q.add(new Node(i, j));
                }
            }
        }

        //원본 연구소를 바꾸지 않기 위한 카피맵 사용
        int visitMap[][] = new int[n][m];

        for (int i = 0; i < n; i++) {
            visitMap[i] = map[i].clone();
        }

        while (!q.isEmpty()) {
            Node node = q.poll();

            int x = node.x;
            int y = node.y;

            for (int d = 0; d < 4; d++) {

                int nx = x + dx[d];
                int ny = y + dy[d];
                if (nx >= 0 && ny >= 0 && nx < n && ny < m && visitMap[nx][ny] == 0) {
                    q.add(new Node(nx, ny));
                    visitMap[nx][ny] = 2;
                }
            }
        }
        int count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (visitMap[i][j] == 0) {
                    count++;
                }
            }
        }

        max = Math.max(count, max);
    }

}
