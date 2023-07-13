package 시뮬레이션;

import java.io.*;
import java.util.*;

public class 로봇청소기 {
    static int n, m;
    static int[][] map;
    static int cnt = 0;
    static int dr[] = {-1, 0, 1, 0};
    static int dc[] = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];

        st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        clean(r, c, d);
        System.out.println(cnt);

    }

    private static void clean(int row, int col, int direction) {

        // 1현재 위치를 청소한다.
        if (map[row][col] == 0) {
            map[row][col] = 2;
            cnt++;
        }

        // 2. 왼쪽 방향 부터 차례대로 탐색을 한다.
        boolean flag = false;
        int origin = direction;

        for (int i = 0; i < 4; i++) {
            int next_d = (direction + 3) % 4;
            int next_r = row + dr[next_d];
            int next_c = col + dc[next_d];

            if (next_r > 0 && next_c > 0 && next_r < n && next_c < m) {
                if (map[next_r][next_c] == 0) {
                    clean(next_r, next_c, next_d);
                    flag = true;
                    break;
                }
            }
            direction = (direction + 3) % 4;
        }


        // 네 방향 전부 청소가 되어 있거나 벽인 경우
        if (!flag) {
            int next_d = (origin + 2) % 4;
            int next_br = row + dr[next_d];
            int next_bc = col + dc[next_d];


            if(next_br > 0 && next_bc > 0 && next_br<n && next_bc <m){
                if(map[next_br][next_bc] != 1){
                    clean(next_br, next_bc, origin);
                }
            }
        }
    }
}
