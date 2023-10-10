//package 삼성기출문제;
//
//import java.io.IOException;
//import java.util.Scanner;
//
//public class 삼성터렛_다시풀기_1 {
//
//    static class Turret implements Comparable<Turret> {
//
//    }
//
//    // 우하좌상 -> 방향 우선순위
//
//
//    // 상하좌우, 대각선 -> 포탄 공격에 사용
//
//
//    static int N, M;
//    static int[][] map; // 포탑 공격력 저장
//    static int[][] attack; // 공격시점 저장
//    static boolean[][] effect; // 영향 받았는지 확인 -> 공격 받았거나 공격자이거나 그 주변에 공격 받은 포탑인지 확인
//    static Turret start;
//    static Turret target;
//
//
//    public static void main(String[] args) throws IOException {
//
//        // TODO 입력
//        Scanner sc = new Scanner(System.in);
//
//        N = sc.nextInt();
//        M = sc.nextInt();
//        int K = sc.nextInt();
//
//        map = new int[N][M];
//        attack = new int[N][M]; // 공격시점
//
//        for (int i = 0; i < N; i++) {
//            for (int j = 0; j < M; j++) {
//                map[i][j] = sc.nextInt();
//            }
//        }
//
//        // TODO 풀이 시작
//
//        // K번의 턴이 종료된 수 남아있는 포탑 중 가장 강한 포탑의 공격력 출력
//        for (int time = 1; time <= K; time++) {
//            // 0. 종료 조건 체크 (포탑이 하나만 있을 경우 종료)
//            if (isFisnish()) { // cnt가 1이면 true
//                break;
//            }
//            // 1. 공격력이 가장 낮고, 높은 포탑 찾기
//            find(time);
//
//            // 2. 포탑 공격(레이저공격 또는 포탄공격) -> 레이저공격을 하지 못하는 경우에 포탄공격
//            if (!laser(start, target)) {
//                bomb(start, target);
//            }
//
//            // 3. 포탑 부서짐 -> 공격 받아서 공격력이 0이하인 포탑은 0을 넣어줌
//
//
//            // 4. 포탑정비 -> 공격자가 아님, 공격에 피해입은 포탑 아님, 부서지지 않은 포탑이라면 공격력 1 증가
//
//        }
//
//
//        // TODO 출력
//        // 남아있는 포탑 중 가장 강한 포탑의 공격력 출력
//        int max = 0;
//
//
//        System.out.println(max);
//    }
//
//    private static void find(int time) {
//        // 1. 공격력이 가장 낮고 높은 포탑 찾기
//
//        // 정렬
//
//        // 공격자 -> 가장 앞에 있는게 가장 작은 수
//
//        // 공격 대상 -> 가장 뒤에 있는게 가장 큰 수
//
//        // 반영
//
//    }
//
//    // 레이저 공격 : bfs 사용 -> 공격자의 위치부터 시작 -> 공격 대상 위치까지 탐색 -> 만약 가는 길이 없다면 false 반환
//    private static boolean laser(Turret start, Turret target) {
//        // 경로 저장
//
//        // BFS 시작
//        while (!q.isEmpty()) {
//
//        }
//
//        // 공격대상 위치까지 못가는 경우 false 반환
//
//
//        // 역추적해서 경로에 있는 포탑 공격 -> 공격 대상 위치에서 시작하면됨(공격 대상 위치에 처음 도착하면 그게 최단경로)
//    }
//
//    // 포탄 공격 -> 매개변수 : 공격자, 공격 대상
//    private static void bomb(Turret start, Turret target) {
//
//        // 초반 설정
//
//        // 공격 대상의 상하좌우, 대각선 위치의 포탄 공격력 감소
//        for (int i = 0; i < 8; i++) {
//
//
//            // 공격자의 위치가 아닌 경우 (공격자는 포탄 공격에 영향 받지 않음)
//
//        }
//    }
//
//    // 남아있는 포탑 개수 세기
//    private static boolean isFisnish() {
//
//    }
//
//}