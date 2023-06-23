package Bfs;

import java.io.*;
import java.util.*;


public class 벽부수고이동하기 {
    static class Point {
        int x;
        int y;
        int cnt;
        boolean destroy;


        public Point(int x, int y, int cnt, boolean destroy) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
            this.destroy = destroy;
        }
    }

    static int n, m;
    static char[][] arr;
    static boolean[][][] visited;
    static Queue<Point> q = new LinkedList<>();
    static Point end;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new char[n][m];
        visited = new boolean[n][m][2];

        for (int i = 0; i < n; i++) {
            String str = br.readLine();
            for (int j = 0; j < m; j++) {
                arr[i][j] = str.charAt(j);
            }
        }
        bfs(0, 0);

    }

    private static void bfs(int x, int y) {
        q.offer(new Point(x, y, 1, false));
        visited[x][y][0] = true;

        while (!q.isEmpty()) {
            Point point = q.poll();

            if(point.x == n-1 && point.y == m-1) {
                System.out.println(point.cnt);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = point.x + dx[i];
                int ny = point.y + dy[i];

                if(nx <0 || nx >= n || ny < 0 || ny >=m){
                    continue;
                }

                int nextCnt = point.cnt+1;


                // 벽이 아닐 때
                if (arr[nx][ny] == '0') {
                    // 부신 벽이 지금까지 없다면
                    if(!point.destroy && !visited[nx][ny][0]){
                        q.add(new Point(nx,ny,nextCnt,false));
                        visited[nx][ny][0] = true;
                    }

                    // 벽을 한번 부신적이 있다면
                    else if(point.destroy && !visited[nx][ny][1]){
                        q.add(new Point(nx,ny, nextCnt, true));
                        visited[nx][ny][1] = true;
                    }
                }
                // 벽일 때
                else if(arr[nx][ny] == '1') {
                    if(!point.destroy) {
                        q.add(new Point(nx,ny, nextCnt, true ));
                        visited[nx][ny][1] = true;
                    }
                }
            }

        }

        System.out.println(-1);
    }
}
