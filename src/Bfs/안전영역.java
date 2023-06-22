package Bfs;

import java.io.*;
import java.util.*;

class Node {
    int x;
    int y;

    public Node(int x, int y) {
        this.x = x;
        this.y = y;

    }
}

public class 안전영역 {

    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static int[][] arr;
    static boolean[][] visited;
    static int n, count, tmpCount;
    static int maxNum = 0;
    static Queue<Node> q;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());

                maxNum = Math.max(maxNum, arr[i][j]);
            }
        }

        int result = 1; // *
        for (int i = 1; i < maxNum; i++) {
            visited = new boolean[n][n]; // *
            count =0;
            for (int j = 0; j < n; j++) {
                for (int x = 0; x < n; x++) {
                    if (arr[j][x] > i && !visited[j][x]) {
                        bfs(j,x,i);
                        count++;
                    }
                }
            }


            result = Math.max(count, result);
        }

        System.out.println(result);

    }

    private static void bfs(int x, int y, int height) {

        q = new LinkedList<>();

        q.add(new Node(x, y));
        visited[x][y] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }

                if(!visited[nx][ny] && arr[nx][ny] > height){
                    visited[nx][ny] =true;
                    q.add(new Node(nx, ny));
                }

            }
        }

    }


}
