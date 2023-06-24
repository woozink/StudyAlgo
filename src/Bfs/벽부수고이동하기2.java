package Bfs;

import java.io.*;
import java.util.*;


public class 벽부수고이동하기2 {
    static class Node {
        int x;
        int y;
        int cnt;
        int destory;

        public Node(int x, int y, int cnt, int destroy) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.destory = destroy;

        }
    }

    static int n, m, k, ans;
    static int[][] arr;
    static boolean[][][] visited;
    static Queue<Node> q = new LinkedList<>();
    static int dx[] = {-1, 1, 0, 0};
    static int dy[] = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        arr = new int[n][m];
        visited = new boolean[n][m][k + 1];//*

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                arr[i][j] = str.charAt(j) - '0';
            }
        }

        ans = -1;

        bfs();
        System.out.println(ans);

    }

    private static void bfs() {
        q = new LinkedList<>();
        q.add(new Node(0, 0, 1, 0));
        visited[0][0][0] = true;


        while (!q.isEmpty()) {
            Node node = q.poll();

            // 도착
            if (node.x == n - 1 && node.y == m - 1) {
                ans = node.cnt;
                return;
            }


            // 사방탐색
            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];


                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }

                // 길이 갈 수 있을 때
                if (arr[nx][ny] == 0 && !visited[nx][ny][node.destory]) {
                    visited[nx][ny][node.destory] = true;
                    q.add(new Node(nx, ny, node.cnt + 1, node.destory));
                }

                // 갈 수 없을 때

                else {
                    if (node.destory < k && !visited[nx][ny][node.destory + 1]) {
                        visited[nx][ny][node.destory + 1] = true;
                        q.add(new Node(nx, ny, node.cnt + 1, node.destory + 1));
                    }
                }
            }


        }

    }
}
