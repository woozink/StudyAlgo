package 재귀;

import java.io.*;
import java.util.*;

public class 하노이의탑이동순서 {

    static int cnt;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()); //3

        move(N, 1, 3, 2); // 3개를 하는 것이고, 시작은 1번 끝은 3번 임시 2번
        System.out.println(cnt);
        System.out.println(sb);
    }

    private static void move(int N, int start, int end, int sub) {

        // 기저 조건
        // N이 1이면 start 를 넣고, 끝을 넣는다.
        // count 업 시켜주고
        if (N == 1) {
            sb.append(start).append(" ").append(end).append("\n");
            cnt++;
            return;
        }

        move(N - 1, start, sub, end);
        move(1, start, end, sub);
        move(N - 1, sub, end, start);
    }

}

