package 백트래킹;
import java.io.*;
import java.util.*;

public class Gaaaaaaaaaarden {
    static int N, M, G, R, len, ans = 0;
    static int[] arrG, arrR;
    static int[] X = { -1, 0, 1, 0 };
    static int[] Y = { 0, 1, 0, -1 };
    static int[][] map;
    static Pos[][] visited;
    static ArrayList<Pos> al = new ArrayList<>();
    static Queue<Pos> q = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());  // 땅의 세로 크기
        M = Integer.parseInt(st.nextToken());  // 땅의 가로 크기
        G = Integer.parseInt(st.nextToken());  // 초록색 배양액의 개수
        R = Integer.parseInt(st.nextToken());  // 빨간색 배양액의 개수
        map = new int[N][M];  // 배양액을 뿌릴 수 있는 땅의 정보를 저장할 배열
        arrG = new int[G];  // 선택한 초록색 배양액의 인덱스를 저장할 배열
        arrR = new int[R];  // 선택한 빨간색 배양액의 인덱스를 저장할 배열

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());  // 배양액을 뿌릴 수 있는 땅인 경우 1, 배양액을 뿌릴 수 없는 땅인 경우 0
                if (map[i][j] == 2) {
                    al.add(new Pos(i, j, 0));  // 배양액을 뿌릴 수 있는 땅인 경우 좌표와 초기 시간(거리) 0을 가지는 객체를 리스트에 추가
                }
            }
        }
        len = al.size();  // 배양액을 뿌릴 수 있는 땅의 개수 저장

        dfs(0, 0, 0);  // 백트래킹을 이용하여 모든 배양액의 조합을 생성하고 BFS 함수 호출

        System.out.println(ans);  // 결과 출력
    }

    // BFS를 사용하여 배양액을 퍼뜨리는 함수
    static void bfs() {
        visited = new Pos[N][M];  // 방문 여부와 배양액의 시간(거리) 및 색상 정보를 저장할 2차원 배열 초기화

        for (int i = 0; i < G; i++) {
            Pos p = al.get(arrG[i]);
            q.offer(p);  // 초록색 배양액을 뿌릴 좌표를 큐에 추가
            visited[p.x][p.y] = new Pos(p.cnt, 'G');  // 해당 좌표의 방문 여부와 시간(거리), 색상 정보를 저장
        }

        for (int i = 0; i < R; i++) {
            Pos p = al.get(arrR[i]);
            q.offer(p);  // 빨간색 배양액을 뿌릴 좌표를 큐에 추가
            visited[p.x][p.y] = new Pos(p.cnt, 'R');  // 해당 좌표의 방문 여부와 시간(거리), 색상 정보를 저장
        }

        int flower = 0;  // 꽃이 피는 개수를 저장할 변수
        while (!q.isEmpty()) {
            Pos now = q.poll();  // 큐에서 좌표를 꺼냄
            int cnt = visited[now.x][now.y].cnt;  // 해당 좌표의 시간(거리) 정보를 가져옴
            char color = visited[now.x][now.y].color;  // 해당 좌표의 색상 정보를 가져옴

            // 꽃이 피었을 경우 배양액을 확장시키면 안됨
            if (visited[now.x][now.y].color == 'F')
                continue;

            for (int d = 0; d < 4; d++) {
                int nx = now.x + X[d];
                int ny = now.y + Y[d];

                if (!check(nx, ny))
                    continue;

                if (visited[nx][ny] == null) {
                    visited[nx][ny] = new Pos(now.cnt + 1, color);  // 새로운 좌표의 방문 여부와 시간(거리), 색상 정보를 저장
                    q.offer(new Pos(nx, ny, now.cnt + 1));  // 큐에 새로운 좌표를 추가하여 BFS를 위해 퍼뜨림
                }
                else if(visited[nx][ny].color == 'G') {
                    if(color == 'G' || visited[nx][ny].cnt != cnt+1)
                        continue;

                    flower++;  // 꽃이 피었을 경우 꽃이 피는 개수를 증가시킴
                    visited[nx][ny].color = 'F';  // 해당 좌표의 색상을 꽃으로 표시
                }
                else if(visited[nx][ny].color == 'R') {
                    if(color == 'R' || visited[nx][ny].cnt != cnt +1)
                        continue;

                    flower++;  // 꽃이 피었을 경우 꽃이 피는 개수를 증가시킴
                    visited[nx][ny].color = 'F';  // 해당 좌표의 색상을 꽃으로 표시
                }
            }
        }
        ans = Math.max(ans, flower);  // 현재까지의 꽃 개수와 새로운 꽃 개수를 비교하여 최대값을 저장
    }

    // 좌표의 유효성을 확인하는 함수
    static boolean check(int nx, int ny) {
        if (nx < 0 || ny < 0 || nx >= N || ny >= M || map[nx][ny] == 0)
            return false;

        return true;
    }

    // 백트래킹을 사용하여 배양액의 조합을 생성하는 함수
    static void dfs(int idx, int g, int r) {
        if (g == G && r == R) {
            bfs();  // 배양액의 조합을 생성한 후 BFS 함수 호출하여 꽃 개수를 계산
            return;
        }

        if (g < G) {
            for (int i = idx; i < len; i++) {
                arrG[g] = i;  // 초록색 배양액의 인덱스를 배열에 저장
                dfs(i + 1, g + 1, r);  // 다음 배양액을 선택하기 위해 재귀 호출
            }
        }

        if (r < R) {
            for (int i = idx; i < len; i++) {
                arrR[r] = i;  // 빨간색 배양액의 인덱스를 배열에 저장
                dfs(i + 1, g, r + 1);  // 다음 배양액을 선택하기 위해 재귀 호출
            }
        }
    }

    // 좌표와 시간(거리), 색상 정보를 저장하는 클래스
    static class Pos {
        int x, y, cnt;
        char color;

        public Pos(int cnt, char color) {
            this.cnt = cnt;
            this.color = color;
        }

        public Pos(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}
