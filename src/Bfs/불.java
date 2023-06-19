package Bfs;

import java.io.*;
import java.util.*;


class Pos {
    int x;
    int y;

    public Pos(int x, int y) {
        this.x = x;
        this.y = y;
    }
}


public class 불 {

    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static int n, m;
    static Queue<Pos> fq = new LinkedList<>();
    static Queue<Pos> jq = new LinkedList<>();
    static char[][] map;
    static boolean[][] jVisited;
    static boolean[][] fVisited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new char[n][m];
        jVisited = new boolean[n][m];
        fVisited = new boolean[n][m];

        for (int i = 0; i < n; i++) {
            String s = br.readLine();
            for (int j = 0; j < m; j++) {
                map[i][j] = s.charAt(j);

                if (map[i][j] == 'J') {
                    map[i][j] = '.';
                    jq.add(new Pos(i, j));
                    jVisited[i][j] = true;
                } else if (map[i][j] == 'F') {
                    fq.add(new Pos(i, j));
                    fVisited[i][j] = true;
                }

            }
        }


        int result = bfs();
        if(result == -1){
            System.out.println("IMPOSSIBLE");
        }else{
            System.out.println(result);
        }

    }

    private static int bfs() {

        // 경과 시간 초기화
        int time = 0;

        // 지훈이의 위치를 기준으로 BFS 실행
        while (!jq.isEmpty()) {
            time ++; // 경과 시간 증가

            // 현재 시간에 지훈이의 위치에서 대기 중인 큐의 크기 저장
            int jihunLen = jq.size();

            // 현재 시간에 불이 번지고 있는 큐의 크기 저장
            int fireLen = fq.size();


            // 현재 시간에 불이 번진 위치에서 상하좌우로 번지는 것을 처리
            for (int i = 0; i < fireLen; i++) {

                // 불이 번진 위치를 큐에서 가져옴
                Pos fire = fq.poll();


                // 상하좌우 탐색
                for (int j = 0; j < 4; j++) {
                    int nx = fire.x + dr[j];
                    int ny = fire.y + dc[j];

                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                        continue;
                    }

                    if (map[nx][ny] == '#' || fVisited[nx][ny]) {
                        continue;
                    }

                    fVisited[nx][ny] = true; // 방문 표시
                    map[nx][ny] = 'F'; // 불로 표사
                    fq.add(new Pos(nx, ny)); // 다음에 불이 번질 위치를 큐에 츄가
                }
            }

            for (int i = 0; i < jihunLen; i++) {
                Pos jihun = jq.poll(); // 지훈이의 현재 위치

                for (int j = 0; j < 4; j++) {
                    int nx = jihun.x + dr[j];
                    int ny = jihun.y + dc[j];


                    // 범위를 벗어난 경우 지훈이는 탈출한 것임
                    if (nx < 0 || nx >= n || ny < 0 || ny >= m) {
                        return time;
                    }

                    if (map[nx][ny] != '.' || jVisited[nx][ny]) {
                        continue;
                    }


                    jVisited[nx][ny] = true; //방문표시
                    jq.add(new Pos(nx, ny)); // 다음에 이동할 위치를 큐에 추가
                }
            }
        }
        return -1; // 지훈이 탈출 불가

    }
}
