package 시뮬레이션;

import java.io.*;
import java.util.*;

public class 스티커붙이기 {
    static int n, m, k, ans;
    static int[][] map, sticker;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 초기화
        n = Integer.parseInt(st.nextToken()); // 노트북 가로
        m = Integer.parseInt(st.nextToken()); // 노브북 세로
        k = Integer.parseInt(st.nextToken()); // 스티커 개수
        ans = 0;

        map = new int[n][m];


        for (int x = 0; x < k; x++) {
            st = new StringTokenizer(br.readLine());

            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            sticker = new int[r][c];

            for (int i = 0; i < r; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < c; j++) {
                    sticker[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            findLocation(sticker);

        }
        System.out.println(ans);
    }

    private static void findLocation(int[][] sticker) {

        int r = sticker.length;
        int c = sticker[0].length;

        // 4방향으로 돌릴 수 있음 90 -> 180 -> 270 -> 360
        for (int d = 0; d < 4; d++) {
            // 처음에는 그대로 한번 넣어봐야하기 때문에
            if (d != 0) {
                sticker = rotate(sticker, r, c);
                r = sticker.length;
                c = sticker[0].length;
            }


            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    // 범위를 초과한다면
                    if (i + r > n || j + c > m) {
                        break;
                    }
                    // 놓을 수 있다면
                    if (putOn(i, j, r, c, sticker)) {
                        return;
                    }
                }
            }
        }
    }


    // x , y = 현재 좌표
    // r , c = 스티커 붙이는 놈 좌표
    private static boolean putOn(int x, int y, int r, int c, int[][] sticker) {
        for (int i = x; i < x + r; i++) {
            for (int j = y; j < y + c; j++) {

                //  현재 위치 (i, j)에 이미 스티커가 붙여져 있고, 동시에 스티커 자체의 위치
                //  (i - x, j - y)에도 스티커가 존재하는 경우
                if (map[i][j] == 1 && sticker[i - x][j - y] == 1) {
                    return false;
                }
            }
        }
        for (int i = x; i < x + r; i++) {
            for (int j = y; j < y + c; j++) {
                if (sticker[i - x][j - y] == 1) {
                    map[i][j] = 1;
                    ans++;
                }
            }
        }


        return true;
    }

    private static int[][] rotate(int[][] sticker, int r, int c) {
        int[][] newSticker = new int[c][r]; // 90도 돌릴 거니까
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                newSticker[j][r - i - 1] = sticker[i][j];
            }
        }
        return newSticker;
    }
}
