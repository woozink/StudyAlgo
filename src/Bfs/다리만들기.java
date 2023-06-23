package Bfs;

import java.io.*;
import java.util.*;

public class 다리만들기 {

    static class Node {
        int x;
        int y;
        int count;

        public Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }
    }

    static int n;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<Node> q = new LinkedList<>();
    static int landNum = 2;
    static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 섬 마다 번호가 달라야 연결하기 쉽다. 섬에 이름을 붙여준다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] == 1 && !visited[i][j]) {
                    makeLand(i, j);
                }
            }
        }

        // 섬에 다리를 연결할 수 있는 경우의 수를 여러개 찾아보자
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (arr[i][j] >= 2) {
                    visited = new boolean[n][n];
                    bfs(i, j);
                }

            }
        }

        System.out.println(answer);
    }

    private static void bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y, 0));
        visited[x][y] = true;
        int currentNum = arr[x][y];

        while (!q.isEmpty()) {

            Node node = q.poll();

            for (int i = 0; i < 4; i++) {

                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }

                if (arr[nx][ny] != 0 && arr[nx][ny] != currentNum && !visited[nx][ny]) {
                    answer = Math.min(answer, node.count);
                    visited[nx][ny] = true;
                }

                if (arr[nx][ny] == 0 && !visited[nx][ny]) {
                    q.offer(new Node(nx, ny, node.count + 1));
                    visited[nx][ny] = true;
                }
            }
        }
    }

    private static void makeLand(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y, 0));
        arr[x][y] = landNum;
        visited[x][y] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();
            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }

                if (visited[nx][ny] || arr[nx][ny] == 0) {
                    continue;
                }

                q.offer(new Node(nx, ny, 0));
                visited[nx][ny] = true;
                arr[nx][ny] = landNum;
            }
        }
        landNum++;
    }


}
