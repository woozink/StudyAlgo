package 삼성기출문제;

import java.io.IOException;
import java.util.*;

class Turret implements Comparable<Turret> {
    int x;
    int y;
    int power; // 공격력
    int attack; // 언제 공격했는지 -> 조건 중에 가장 최근에 공격 받았는지가 중요해서

    public Turret(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Turret(int x, int y, int power, int attack) {
        this.x = x;
        this.y = y;
        this.power = power;
        this.attack = attack;
    }

    @Override
    public int compareTo(Turret o) {
        /*
         * 1. 공격력이 가장 낮은 포탑을 기준으로 오름차순
         * 2. 공격력이 같다면 공격한 시점을 기준으로 내림차순
         * 3. 공격한 시점도 같다면 행과 열의 합이 가장 큰 포탑을 기준으로 내림차순
         * 4. 행과 열의 합도 같다면 열 값을 기준으로 내림차순
         */

        if (this.power == o.power) {
            if (this.attack == o.attack) {
                if (this.x + this.y == o.x + o.y) {
                    return o.y - this.y;
                }

                return (o.x + o.y) - (this.x + this.y);
            }

            return o.attack - this.attack;
        }

        return this.power - o.power;
    }
}

public class 삼성터렛 {

    // 우하좌상 -> 방향 우선순위
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };

    // 상하좌우, 대각선 -> 포탄 공격에 사용
    static int[] bdx = { 0, 1, 0, -1, 1, 1, -1, -1 };
    static int[] bdy = { 1, 0, -1, 0, -1, 1, -1, 1 };

    static int N, M;
    static int[][] map; // 포탑 공격력 저장
    static int[][] attack; // 공격시점 저장
    static boolean[][] effect; // 영향 받았는지 확인 -> 공격 받았거나 공격자이거나 그 주변에 공격 받은 포탑인지 확인

    static Turret start;
    static Turret target;

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        int K = sc.nextInt();

        map = new int[N][M];
        attack = new int[N][M]; // 공격시점

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        // K번의 턴이 종료된 수 남아있는 포탑 중 가장 강한 포탑의 공격력 출력
        for (int time = 1; time <= K; time++) {
            // 포탑이 하나만 있을 경우 종료
            if (isFisnish()) { // cnt가 1이면 true
                break;
            }
            // 1. 공격력이 가장 낮고, 높은 포탑 찾기
            find(time);

            // 2. 포탑 공격(레이저공격 또는 포탄공격) -> 레이저공격을 하지 못하는 경우에 포탄공격
            if (!laser(start, target)) {
                bomb(start, target);
            }

            // 3. 포탑 부서짐 -> 공격 받아서 공격력이 0이하인 포탑은 0을 넣어줌
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] < 0) {
                        map[i][j] = 0;
                    }
                }
            }

            // 4. 포탑정비 -> 공격자가 아님, 공격에 피해입은 포탑 아님, 부서지지 않은 포탑이라면 공격력 1 증가
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (map[i][j] != 0 && !effect[i][j]) {
                        map[i][j]++;
                    }
                }
            }
        }

        // 남아있는 포탑 중 가장 강한 포탑의 공격력 출력
        int max = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                max = Math.max(max, map[i][j]);
            }
        }

        System.out.println(max);

    }

    private static void find(int time) {
        effect = new boolean[N][M];

        // 1. 공격력이 가장 낮고 높은 포탑 찾기
        ArrayList<Turret> list = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0) {
                    list.add(new Turret(i, j, map[i][j], attack[i][j]));
                }
            }
        }

        Collections.sort(list); // 정렬

        start = list.get(0); // 공격자 -> 가장 앞에 있는게 가장 작은 수
        target = list.get(list.size() - 1); // 공격 대상 -> 가장 뒤에 있는게 가장 큰 수

        map[start.x][start.y] += N + M;
        attack[start.x][start.y] = time; // 시점 조건 2
        effect[start.x][start.y] = true; // 공격 들어갔는지. 조건 3
    }

    // 레이저 공격 : bfs 사용 -> 공격자의 위치부터 시작 -> 공격 대상 위치까지 탐색 -> 만약 가는 길이 없다면 false 반환
    private static boolean laser(Turret start, Turret target) {
        Turret[][] route = new Turret[N][M]; // 경로 저장
        Queue<Turret> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];

        q.add(new Turret(start.x, start.y));
        visited[start.x][start.y] = true;

        while (!q.isEmpty()) {
            Turret turret = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = (N + turret.x + dx[i]) % N;
                int ny = (M + turret.y + dy[i]) % M;

                // 방문하지 않았고 부서지지 않은 포탑인 경우
                if (!visited[nx][ny] && map[nx][ny] != 0) {
                    q.add(new Turret(nx, ny));
                    visited[nx][ny] = true;
                    route[nx][ny] = new Turret(turret.x, turret.y);
                }
            }
        }

        // 공격대상 위치까지 못가는 경우 false 반환
        if (!visited[target.x][target.y]) {
            return false;
        }

        // 역추적해서 경로에 있는 포탑 공격 -> 공격 대상 위치에서 시작하면됨(공격 대상 위치에 처음 도착하면 그게 최단경로)
        int x = target.x;
        int y = target.y;

        while (x != start.x || y != start.y) {
            int power = map[start.x][start.y] / 2;
            if (x == target.x && y == target.y) {
                power = map[start.x][start.y];
            }

            map[x][y] -= power;
            effect[x][y] = true;

            Turret turret = route[x][y]; // 역추적

            x = turret.x;
            y = turret.y;
        }

        return true;
    }

    // 포탄 공격 -> 매개변수 : 공격자, 공격 대상
    private static void bomb(Turret start, Turret target) {
        int power = map[start.x][start.y];

        map[target.x][target.y] -= power;
        effect[target.x][target.y] = true;

        power /= 2;

        // 공격 대상의 상하좌우, 대각선 위치의 포탄 공격력 감소
        for (int i = 0; i < 8; i++) {
            int nx = (N + target.x + bdx[i]) % N;
            int ny = (M + target.y + bdy[i]) % M;

            // 공격자의 위치가 아닌 경우 (공격자는 포탄 공격에 영향 받지 않음)
            if (nx != start.x || ny != start.y) {
                map[nx][ny] -= power;
                effect[nx][ny] = true;
            }
        }
    }

    // 남아있는 포탑 개수 세기
    private static boolean isFisnish() {
        int cnt = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0) {
                    cnt++;
                }
            }
        }

        return cnt == 1;
    }

}