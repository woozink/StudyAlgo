package Bfs;

import java.io.*;
import java.util.*;



public class 미로탐색 {

    static Queue<Node> q = new LinkedList<>();
    static int[] dr = {-1, 1, 0, 0}; // 상하좌우
    static int[] dc = {0, 0, -1, 1};
    static int n, m;
    static int[][] arr;
    static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        visited = new boolean[n][m];

        for(int i = 0; i < n; i++){

            String s = br.readLine();
            for(int j = 0; j < m; j++){
               arr[i][j] = s.charAt(j) - '0';
            }
        }


        bfs(0,0);
        System.out.println(arr[n-1][m-1]);
    }

    public static void bfs(int x, int y) {
        q = new LinkedList<>();
        q.add(new Node(x, y));

        visited[x][y] = true;


        while (!q.isEmpty()) {

            Node node = q.poll();

            for (int dir = 0; dir < 4; dir++) {

                int nx = node.x + dr[dir];
                int ny = node.y + dc[dir];

                if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                    continue;
                }

                if (arr[nx][ny] == 0 || visited[nx][ny]) {
                    continue;
                }

                q.offer(new Node(nx,ny));
                arr[nx][ny] = arr[node.x][node.y]+1;
                visited[nx][ny] = true;

            }
        }
    }


}
