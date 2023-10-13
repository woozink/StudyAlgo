package 삼성기출문제;

import java.util.*;

public class 싸움땅_다시풀기 {
    static public class Tuple {
        int x, y, dir;

        public Tuple(int x, int y, int dir) {
            this.x = x;
            this.y = y;
            this.dir = dir;
        }
    }

    static class Player {
        int num, x, y, d, s, a; // 번호, 위치, 방향, 초기능력치, 공격력

        public Player(int num, int x, int y, int d, int s, int a) {
            this.num = num;
            this.x = x;
            this.y = y;
            this.d = d;
            this.s = s;
            this.a = a;
        }
    }

    static class Pair {
        int x, y;

        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean isSame(Pair p) {
            return this.x == p.x && this.y == p.y;
        }
    }

    static int n, m, k; // 격자 크기, 플레이어수 라운드
    static int MAX_N = 20;
    static int MAX_M = 30;
    static Player EMPTY = new Player(-1, -1, 1, -1, -1, -1);
    static ArrayList<Integer>[][] gun = new ArrayList[MAX_N][MAX_N];
    public static Player[] players = new Player[MAX_M];
    static int[] points = new int[MAX_M];

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        m = sc.nextInt();
        k = sc.nextInt();

        // 각 위치에 있는 총의 정보를 초기화하고 입력 받는다.
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                gun[i][j] = new ArrayList<>();

                int num = sc.nextInt();
                if (num != 0) {
                    gun[i][j].add(num);
                }
            }
        }

        // 플레이어의 초기 위치 및 능력치를 설정한다.
        for (int i = 0; i < m; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            int d = sc.nextInt();
            int s = sc.nextInt();
            players[i] = new Player(i, x - 1, y - 1, d, s, 0);
        }

        while (k-- > 0) {
            simulate();
        }

        for (int i = 0; i < m; i++) {
            System.out.println(points[i] + " ");
        }
    }

    // 1 라운드 진행
    private static void simulate() {
        // 순서대로 진행
        for (int i = 0; i < m; i++) {
            int num = players[i].num;
            int x = players[i].x;
            int y = players[i].y;
            int d = players[i].d;
            int s = players[i].s;
            int a = players[i].a;

            // 현재 플레이어가 움직일 그 다음 위치와 방향을 구한다.
            Tuple next = getNext(x, y, d);
            int nx = next.x;
            int ny = next.y;
            int ndir = next.dir;
            // 해당 위치에 있는 전 플레이어의 정보를 얻어온다.
            Player nextPlayer = findPlayer(new Pair(nx, ny));
            // 현재 플레이어의 위치와 방향을 보정해준다.
            Player currPlayer = new Player(num, nx, ny, ndir, s, a);
            update(currPlayer);
            // 해당 위치로 이동한다.
            // 해당 위치에 플레이어가 없다면 그대로 ㄱㄱ
            if (nextPlayer == EMPTY) {
                move(currPlayer, new Pair(nx,ny));
            }
            // 있다면 결투 진행
            else{
                duel(currPlayer, nextPlayer, new Pair(nx,ny));
            }

        }

    }



    private static void duel(Player p1, Player p2, Pair pos) {
        int num1 = p1.num, d1 = p1.d, s1 = p1.s, a1 = p1.a;
        int num2 = p2.num, d2 = p2.d, s2 = p2.s, a2 = p2.a;
        
        if(s1 + a1 > s2 + a2 || (s1 + a1 == s2 + a2 && s1 >s2)){
            points[num1] += (s1+a1) - (s2 + a2);
            loserMove(p2);
            move(p1,pos);
        }else{
            points[num2] += (s2 + a2) - (s1 +a1);
            loserMove(p1);
            move(p2,pos);
        }
    }

    private static void loserMove(Player p) {
        int num = p.num, x = p.x, y = p.y, d = p.d, s = p.s, a = p.a;

        gun[x][y].add(a);

        for(int i = 0; i < 4; i++){
            int ndir = (d+i) % 4;
            int nx = x + dx[ndir];
            int ny = x + dy[ndir];

            if(inRange(nx,ny) && findPlayer(new Pair(nx, ny))== EMPTY){
                p = new Player(num , x, y, d, s, 0);
                move(p, new Pair(nx, ny));
                break;
            }
        }
    }

    private static void move(Player p, Pair pos) {
        int num = p.num;
        int x = p.x;
        int y= p.y;
        int d = p.d;
        int s = p.s;
        int a = p.a;

        int nx = pos.x;
        int ny = pos.y;

        gun[nx][ny].add(a);
        Collections.sort(gun[nx][ny]);
        a = gun[nx][ny].get(gun[nx][ny].size() -1);
        gun[nx][ny].remove(gun[nx][ny].size()-1);

        //플레이어 정보 갱신 및 이동
        p = new Player(num , nx, ny, d, s,a);
        update(p);


    }

    private static void update(Player p) {
        int num = p.num;

        for(int i = 0; i < m ;i++){
            int numI = players[i].num;

            if(numI == num){
                players[i] = p;
                break;
            }
        }
    }

    // 특정 위치에 있는 플레이어를 찾는 함수
    // 플레이어 없으면 EMPTY반환
    private static Player findPlayer(Pair pos) {
        for(int i = 0; i < m; i++){
            int x = players[i].x;
            int y = players[i].y;
            if(pos.isSame(new Pair(x, y))) return players[i];
        }
        return EMPTY;
    }

    // 현재 위치와 방향에서 다음 위치와 방향을 구하는 함수
    private static Tuple getNext(int x, int y, int d) {
        int nx = x+ dx[d], ny = y+ dy[d]; // 다음 위치 계산
        // 만약 격자를 벗어나면 반대 방향으로 이동
        if(!inRange(nx,ny)){
            d = (d<2) ? (d+2) : (d-2);
            nx = x+dx[d]; ny = y + dy[d];
        }
        return new Tuple(nx,ny,d);
    }

    private static boolean inRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }
}
