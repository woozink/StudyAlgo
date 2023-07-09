package 시뮬레이션;

import java.io.*;
import java.util.*;

public class 감시 {

    static class CCTV {
        int num;
        int x;
        int y;

        public CCTV(int num, int x, int y) {
            this.num = num;
            this.x = x;
            this.y = y;

        }
    }

    public static int n, m;
    public static int[][] map;
    public static int[][] copyMap;
    public static int[] output;
    public static ArrayList<CCTV> cctvList;
    public static int[] dx = {-1, 0, 1, 0}; // 상 우 하 좌 시계방향 순서
    public static int[] dy = {0, 1, 0, -1};
    public static int answer = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        cctvList = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                if (map[i][j] != 0 && map[i][j] != 6) {
                    cctvList.add(new CCTV(map[i][j], i, j));
                }
            }
        }
        output = new int[cctvList.size()]; // 순열을 담을 배열
        permutation(0, cctvList.size());

        System.out.println(answer);
    }


    public static void permutation(int depth, int r) {
        if (depth == r) {
            copyMap = new int[n][m];
            for (int i = 0; i < map.length; i++) {
                System.arraycopy(map[i], 0, copyMap[i], 0, map[i].length);
            }

            //cctv 번호와 순열로 뽑혀진 방향에 맞는 상하좌우 방향 설정

            for (int i = 0; i < cctvList.size(); i++) {
                direction(cctvList.get(i), output[i]);
            }

            // 사각지대 구하기
            getBlindSpot();

            return;

        }

        for(int i = 0; i < 4; i++) {
            output[depth] = i;
            permutation(depth+1, r);
        }
    }

    private static void getBlindSpot() {
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (copyMap[i][j] == 0) {
                    cnt++;
                }
            }
        }
        answer = Math.min(answer, cnt);
    }

    public static void direction(CCTV cctv, int d) {
        int cctvNum = cctv.num;

        if(cctvNum == 1) {
            if(d == 0) watch(cctv, 0); // 상
            else if(d == 1) watch(cctv, 1); // 우
            else if(d == 2) watch(cctv, 2); // 하
            else if(d == 3) watch(cctv, 3); // 좌
        } else if(cctvNum == 2) {
            if(d == 0 || d == 2) {
                watch(cctv, 0); watch(cctv, 2); // 상하
            } else {
                watch(cctv, 1); watch(cctv, 3); // 좌우
            }
        } else if(cctvNum == 3) {
            if(d == 0) {
                watch(cctv, 0); // 상우
                watch(cctv, 1);
            } else if(d == 1) {
                watch(cctv, 1); // 우하
                watch(cctv, 2);
            } else if(d == 2) {
                watch(cctv, 2); // 하좌
                watch(cctv, 3);
            } else if(d == 3) {
                watch(cctv, 0); // 좌상
                watch(cctv, 3);
            }
        } else if(cctvNum == 4) {
            if(d == 0) {
                watch(cctv, 0); // 좌상우
                watch(cctv, 1);
                watch(cctv, 3);
            } else if(d == 1) {
                watch(cctv, 0); // 상우하
                watch(cctv, 1);
                watch(cctv, 2);
            } else if(d == 2) {
                watch(cctv, 1); // 좌하우
                watch(cctv, 2);
                watch(cctv, 3);
            } else if(d == 3) {
                watch(cctv, 0); // 상좌하
                watch(cctv, 2);
                watch(cctv, 3);
            }
        } else if(cctvNum == 5) { // 상우하좌
            watch(cctv, 0);
            watch(cctv, 1);
            watch(cctv, 2);
            watch(cctv, 3);
        }
    }

    private static void watch(CCTV cctv, int d) {

        Queue<CCTV> q = new LinkedList<>();
        boolean[][] visited = new boolean[n][m];

        q.add(cctv);
        visited[cctv.x][cctv.y] = true;

        while (!q.isEmpty()) {
            int nx = q.peek().x + dx[d];
            int ny = q.poll().y + dy[d];

            if (nx < 0 || nx >= n || ny < 0 || ny >= m || copyMap[nx][ny] == 6) {
                break;
            }

            if (copyMap[nx][ny] == 0) {
                copyMap[nx][ny] = -1; //빈칸이면 감시 가능 마커 남기기
                q.add(new CCTV(cctv.num, nx, ny));
            } else {
                q.add(new CCTV(cctv.num, nx, ny));
            }
        }
    }


}
