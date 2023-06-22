package Bfs;

import java.io.*;
import java.util.*;



public class 나이트의이동 {
    static int n;
    static int[][] arr;
    static boolean[][] visited;
    static int startX;
    static int startY;
    static int finishX;
    static int finishY;
    static Queue<Node> q = new LinkedList<>();
    static int[] dx = {-2, -2, -1, 1, 2, 2, -1, 1};
    static int[] dy = {-1, 1, 2, 2, -1, 1, -2, -2};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            n = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            startX = Integer.parseInt(st.nextToken());
            startY = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            finishX = Integer.parseInt(st.nextToken());
            finishY = Integer.parseInt(st.nextToken());

            arr = new int[n][n];
            visited = new boolean[n][n];

            bfs();

            System.out.println(arr[finishX][finishY]);
        }


    }

    private static void bfs() {
        q.add(new Node(startX, startY));
        visited[startX][startY] = true;

        while (!q.isEmpty()) {
            Node node = q.poll();

            for (int i = 0; i < 8; i++) {
                int nx = node.x + dx[i];
                int ny = node.y + dy[i];

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }

                if(visited[nx][ny]){
                    continue;
                }

                q.add(new Node(nx, ny));
                visited[nx][ny] = true;
                arr[nx][ny] = arr[node.x][node.y]+ 1;

            }


        }

    }
}
