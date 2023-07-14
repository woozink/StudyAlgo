package 시뮬레이션;

import java.io.*;
import java.util.*;

public class 테크노미로 {
    static int max = Integer.MIN_VALUE;
    static int[][] arr;
    static boolean[][] visited;
    static int n, m;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        arr = new int[n][m];
        visited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                visited[i][j] = true;
                solve(i, j, arr[i][j], 1);
                visited[i][j] = false;
            }
        }
        System.out.println(max);


    }

    private static void solve(int row, int col, int sum, int count) {

        if (count == 4) {
            max = Math.max(max, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = row + dx[i];
            int ny = col + dy[i];

            if (nx < 0 || ny < 0 || nx >= n || ny >= m) {
                continue;
            }

            if (!visited[nx][ny]) {

                // ㅗ모양
                if(count == 2){
                    visited[nx][ny] = true;
                    solve(row, col, sum + arr[nx][ny] ,count+1);
                    visited[nx][ny] = false;
                }

                visited[nx][ny] = true;
                solve(nx, ny, sum + arr[nx][ny] , count+1);
                visited[nx][ny] = false;

            }

        }

    }
}

