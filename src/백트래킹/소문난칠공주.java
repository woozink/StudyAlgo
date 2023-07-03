package 백트래킹;

import java.io.*;
import java.util.*;

public class 소문난칠공주 {
    static char[][] students;
    static int[] member;
    static boolean[][] isMember;
    static int total = 0;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        // 학생 좌석 배치도 초기화
        students = new char[5][5];
        // 7명의 좌석을 담을 임시 배열 초기화
        member = new int[7];
        // 연결 여부를 검사할 배열 초기화
        isMember = new boolean[5][5];
        // 학생 좌석 배치도 입력 받기

        for (int i = 0; i < 5; i++) {
            String str = br.readLine();
            for (int j = 0; j < 5; j++) {
                students[i][j] = str.charAt(j);
            }
        }

        // 백트래킹 시작
        dfs(0, 0);
        // 출력
        System.out.println(total);


    }


    // 5 *5 배열에서 7명을 뽑는다.
    // 조건 1 : 7명이 연결이 되어있는가?
    // 조건 2 : 이다솜 파 'S'가 4명 이상 포함이 되어 있는가 ?
    // 지금 이게 딱 조합 코드야
    private static void dfs(int depth, int index) {
        if (depth == 7) {
            if (getScnt() >= 4 && isConnected()) {
                total++;
            }
            return;
        }

        for (int i = index; i < 25; i++) {
            member[depth] = i;
            isMember[i/5][i%5] = true;
            dfs(depth + 1, i + 1);
            isMember[i/5][i%5] = false;

        }
    }

    private static boolean isConnected() {
        Queue<int[]> queue = new LinkedList<>();
        boolean visited[][] = new boolean[5][5];

        int x = member[0] / 5;
        int y = member[0] % 5;

        queue.add(new int[]{x, y});
        visited[x][y] = true;
        int cnt = 1;

        while (!queue.isEmpty()) {
            int[] point = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = point[0] + dx[i];
                int ny = point[1] + dy[i];

                if (!isRange(nx, ny) || visited[nx][ny] || !isMember[nx][ny]) {
                    continue;
                }
                queue.add(new int[]{nx, ny});
                visited[nx][ny] = true;
                cnt++;
            }
        }
        return cnt == 7;
    }

    private static boolean isRange(int nx, int ny) {

        if(0>nx || 4 < nx || 0 >ny || 4<ny){
            return false;
        }
        return true;
    }

    private static int getScnt() {
        // 처음 초기화
        int Scnt = 0;
        // for 문으로 돌면 S를 찾는다.
        for (int index : member) {
            int x = index / 5;
            int y = index % 5;
            if (students[x][y] == 'S') Scnt++;
        }
        return Scnt;
    }


}
