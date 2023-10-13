package 삼성기출문제;
import java.util.*;
import java.io.*;

// 플레이어 클래스 정의
class Player {
    int num, x, y, d, s, a; // 플레이어 번호, 위치(x, y), 방향, 초기 능력치, 공격력

    // 플레이어 생성자
    public Player(int num, int x, int y, int d, int s, int a) {
        this.num = num;
        this.x = x;
        this.y = y;
        this.d = d;
        this.s = s;
        this.a = a;
    }
}

// 좌표와 방향을 함께 저장하는 Tuple 클래스 정의
class Tuple {
    int x, y, dir; // 좌표(x, y) 및 방향

    // Tuple 생성자
    public Tuple(int x, int y, int dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }
}

public class 싸움땅 {
    // 좌표만 저장하는 Pair 클래스 정의
    static class Pair {
        int x, y; // 좌표(x, y)

        // Pair 생성자
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        // 다른 Pair 객체와 같은지 비교하는 메서드
        public boolean isSame(Pair p) {
            return this.x == p.x && this.y == p.y;
        }
    }
    // 상수 정의
    public static final Player EMPTY = new Player(-1, -1, -1, -1, -1, -1); // 빈 플레이어 객체
    public static final int DIR_NUM = 4; // 가능한 방향의 수
    public static final int MAX_M = 30; // 플레이어의 최대 수
    public static final int MAX_N = 20; // 격자의 최대 크기

    // 게임 상태 변수
    public static int n, m, k; // 격자의 크기, 플레이어 수, 라운드 수

    // 각 칸에 놓여 있는 총의 목록
    public static ArrayList<Integer>[][] gun = new ArrayList[MAX_N][MAX_N];

    // 모든 플레이어의 정보
    public static Player[] players = new Player[MAX_M];

    // 이동 방향에 따른 좌표 변화량
    public static int[] dx = new int[]{-1, 0, 1,  0}; // 상, 우, 하, 좌
    public static int[] dy = new int[]{ 0, 1, 0, -1}; // 상, 우, 하, 좌

    // 플레이어들의 포인트 정보
    public static int[] points = new int[MAX_M];

    // 주어진 좌표가 격자 내에 있는지 확인하는 함수
    public static boolean inRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n; // 좌표 범위 확인
    }

    // 현재 위치와 방향에서 다음 위치와 방향을 계산하는 함수
    public static Tuple getNext(int x, int y, int d) {
        int nx = x + dx[d], ny = y + dy[d]; // 다음 위치 계산
        // 만약 격자를 벗어나면 반대 방향으로 이동
        if(!inRange(nx, ny)) {
            d = (d < 2) ? (d + 2) : (d - 2); // 방향 전환
            nx = x + dx[d]; ny = y + dy[d]; // 새로운 위치
        }

        return new Tuple(nx, ny, d); // 새로운 위치와 방향 반환
    }

    // 특정 위치에 있는 플레이어를 찾는 함수
    // 플레이어가 없으면 EMPTY 반환
    public static Player findPlayer(Pair pos) {
        for(int i = 0; i < m; i++) {
            int x = players[i].x, y = players[i].y;
            if(pos.isSame(new Pair(x, y)))
                return players[i];
        }

        return EMPTY;
    }

    // 플레이어의 정보를 갱신하는 함수
    public static void update(Player p) {
        int num = p.num;

        // 해당 플레이어의 정보를 새 정보로 갱신
        for(int i = 0; i < m; i++) {
            int numI = players[i].num;
            if(numI == num) {
                players[i] = p;
                break;
            }
        }
    }

    // 플레이어를 새로운 위치로 이동시키는 함수
    public static void move(Player p, Pair pos) {
        int num = p.num, x = p.x, y = p.y, d = p.d, s = p.s, a = p.a;
        int nx = pos.x, ny = pos.y;

        // 해당 위치의 최고 공격력의 총을 선택
        gun[nx][ny].add(a);
        Collections.sort(gun[nx][ny]);
        a = gun[nx][ny].get(gun[nx][ny].size() - 1);
        gun[nx][ny].remove(gun[nx][ny].size() - 1);

        // 플레이어 정보 갱신 및 이동
        p = new Player(num, nx, ny, d, s, a);
        update(p);
    }
    // 진 사람의 움직임을 진행합니다.
    // 결투에서 패배한 위치는 pos입니다.
    public static void loserMove(Player p) {
        int num = p.num, x = p.x, y = p.y, d = p.d, s = p.s, a = p.a;

        // 먼저 현재 위치에 총을 내려놓게 됩니다.
        gun[x][y].add(a);

        // 빈 공간을 찾아 이동하게 됩니다.
        // 현재 방향에서 시작하여
        // 90'씩 시계방향으로
        // 회전하다가
        // 비어있는 최초의 곳으로 이동합니다.
        for(int i = 0; i < 4; i++) {
            int ndir = (d + i) % 4;
            int nx = x + dx[ndir], ny = y + dy[ndir];
            if(inRange(nx, ny) && findPlayer(new Pair(nx, ny)) == EMPTY) {
                p = new Player(num, x, y, ndir, s, 0);
                move(p, new Pair(nx, ny));
                break;
            }
        }
    }

    // p2과 p2가 pos에서 만나 결투를 진행합니다.
    public static void duel(Player p1, Player p2, Pair pos) {
        int num1 = p1.num, d1 = p1.d, s1 = p1.s, a1 = p1.a;
        int num2 = p2.num, d2 = p2.d, s2 = p2.s, a2 = p2.a;

        // (초기 능력치 + 총의 공격력, 초기 능력치) 순으로 우선순위를 매겨 비교합니다.

        // p1이 이긴 경우
        if(s1 + a1 > s2 + a2 || (s1 + a1 == s2 + a2 && s1 > s2)) {
            // p1은 포인트를 얻게 됩니다.
            points[num1] += (s1 + a1) - (s2 + a2);
            // p2는 진 사람의 움직임을 진행합니다.
            loserMove(p2);
            // 이후 p1은 이긴 사람의 움직임을 진행합니다.
            move(p1, pos);
        }
        // p2가 이긴 경우
        else {
            // p2는 포인트를 얻게 됩니다.
            points[num2] += (s2 + a2) - (s1 + a1);
            // p1은 진 사람의 움직임을 진행합니다.
            loserMove(p1);
            // 이후 p2는 이긴 사람의 움직임을 진행합니다.
            move(p2, pos);
        }
    }


    // 1라운드를 진행합니다.
    public static void simulate() {
        // 첫 번째 플레이어부터 순서대로 진행합니다.
        for(int i = 0; i < m; i++) {
            int num = players[i].num;
            int x = players[i].x;
            int y = players[i].y;
            int d = players[i].d;
            int s = players[i].s;
            int a = players[i].a;

            // Step 1-1. 현재 플레이어가 움직일 그 다음 위치와 방향을 구합니다.
            Tuple next = getNext(x, y, d);
            int nx = next.x, ny = next.y, ndir = next.dir;

            // 해당 위치에 있는 전 플레이어 정보를 얻어옵니다.
            Player nextPlayer = findPlayer(new Pair(nx, ny));

            // 현재 플레이어의 위치와 방향을 보정해줍니다.
            Player currPlayer = new Player(num, nx, ny, ndir, s, a);
            update(currPlayer);

            // Step 2. 해당 위치로 이동해봅니다.
            // Step 2-1. 해당 위치에 플레이어가 없다면 그대로 움직입니다.
            if(nextPlayer == EMPTY)
                move(currPlayer, new Pair(nx, ny));
                // Step 2-2. 해당 위치에 플레이어가 있다면 결투를 진행합니다.
            else
                duel(currPlayer, nextPlayer, new Pair(nx, ny));
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 게임 보드의 크기(n), 플레이어 수(m), 진행할 라운드 수(k)를 입력받습니다.
        n = sc.nextInt(); // 보드의 크기
        m = sc.nextInt(); // 플레이어 수
        k = sc.nextInt(); // 라운드 수

        // 각 위치에 있는 총의 정보를 초기화하고 입력받습니다.
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++) {
                gun[i][j] = new ArrayList<>();

                int num = sc.nextInt(); // 총의 파워를 나타내는 숫자를 입력받습니다.
                // 해당 위치에 총이 있다면 정보를 저장합니다.
                if(num != 0)
                    gun[i][j].add(num); // 총의 파워를 리스트에 추가합니다.
            }

        // 플레이어의 초기 위치 및 능력치를 설정합니다.
        for(int i = 0; i < m; i++) {
            int x = sc.nextInt(); // 플레이어의 초기 x 위치
            int y = sc.nextInt(); // 플레이어의 초기 y 위치
            int d = sc.nextInt(); // 플레이어의 초기 방향
            int s = sc.nextInt(); // 플레이어의 초기 능력치
            players[i] = new Player(i, x - 1, y - 1, d, s, 0); // 플레이어 정보를 배열에 저장합니다. 위치는 1부터 시작하므로 1을 빼줍니다.
        }

        // k번의 라운드를 진행합니다.
        while(k-- > 0)
            simulate(); // 각 라운드를 시뮬레이션합니다.

        // 최종적으로 각 플레이어가 획득한 포인트를 출력합니다.
        for(int i = 0; i < m; i++)
            System.out.print(points[i] + " "); // 플레이어별 포인트를 출력합니다.
    }
}

