package 백트래킹;

import java.io.*;
import java.util.*;

public class 소문난칠공주 {
    static char[][] students;
    static int[] member;
    static boolean[][] isMember;
    static int total = 0;


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
    private static void dfs(int depth, int index) {
        if(depth == 7){
            total++;
            return;
        }

        for(int i =index; i < 25; i++){
            member[depth] = i;
            dfs(depth+1, i+1);
        }
    }

}
