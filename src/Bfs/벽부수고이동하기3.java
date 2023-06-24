package Bfs;

import java.io.*;
import java.util.*;


public class 벽부수고이동하기3 {
    static class Node {
        int x;
        int y;
        int cnt;
        int destory;
        int dayLight;

        public Node(int x, int y, int cnt, int destroy, int dayLight) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.destory = destroy;
            this.dayLight = dayLight;

        }
    }

    static int n, m, k, ans;
    static int[][] arr;
    static boolean[][][][] visited;
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
        visited = new boolean[n][m][k + 1][2];

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
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 1, 0, 0));
        visited[0][0][0][0] = true;


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


                // TODO Solution

                // 길이 갈 수 있을 때
                if (arr[nx][ny] == 0) {
                    // 낮 일때 (밤으로 바꿔줘야 하니까)
                    if (node.dayLight == 0 && !visited[nx][ny][node.destory][node.dayLight + 1]) {
                        q.add(new Node(nx, ny, node.cnt + 1, node.destory, node.dayLight + 1));
                        visited[nx][ny][node.destory][node.dayLight + 1] = true;
                    }
                    // 밤 일때 (낮으로 바꿔줘야하니까)
                    else if (node.dayLight == 1 && !visited[nx][ny][node.destory][node.dayLight - 1]) {
                        q.add(new Node(nx, ny, node.cnt + 1, node.destory, node.dayLight - 1));
                        visited[nx][ny][node.destory][node.dayLight - 1] = true;
                    }
                }

                // 벽일 때 수 없을 때
                else {
                    // 낮일 때
                    if (node.destory < k && node.dayLight == 0 && !visited[nx][ny][node.destory + 1][node.dayLight + 1]) {
                        visited[nx][ny][node.destory + 1][node.dayLight + 1] = true;
                        q.add(new Node(nx, ny, node.cnt + 1, node.destory + 1, node.dayLight + 1));
                    }

                    // 밤일 때
                    else if (node.destory < k && node.dayLight == 1 && !visited[node.x][node.y][node.destory][node.dayLight - 1]) {
                        visited[node.x][node.y][node.destory][node.dayLight - 1] = true;
                        q.add(new Node(node.x, node.y, node.cnt + 1, node.destory, node.dayLight - 1));
                    }
                }
            }


        }

    }
}
