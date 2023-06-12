package Deque;

import java.io.*;
import java.util.*;

public class AC2 {
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        int T = Integer.parseInt(br.readLine());

        for (int x = 0; x < T; x++) {
            Deque<Integer> list = new LinkedList<>();

            // 명령어 받아주기
            String p = br.readLine();
            char[] ch = p.toCharArray();

            // 배열의 개수
            int n = Integer.parseInt(br.readLine());

            // 숫자 배열 받아주기
            String s = br.readLine();

            // list에 정수 반환해서 넣어주기
            if (n > 0) {
                String[] s1 = s.substring(1, s.length() - 1).split(",");
                for (int i = 0; i < s1.length; i++) {
                    list.add(Integer.parseInt(s1[i]));
                }
            }

            Calculator(ch, list);
        }
        System.out.println(sb);
    }

    private static void Calculator(char[] ch, Deque<Integer> list) {
        // 방향 변수 설정
        Boolean isRight = false; // false일 때 정방향

        for (int i = 0; i < ch.length; i++) {
            if (ch[i] == 'R') {

                isRight = !isRight;

            } else if (ch[i] == 'D') {
                if (list.isEmpty()) {
                    sb.append("error").append('\n');
                    return;
                } else {
                    if (isRight == false) {
                        list.pollFirst();
                    } else if (isRight) {
                        list.pollLast();
                    }
                }
            }
        }

        makePrintString(list, isRight);
    }

    private static void makePrintString(Deque<Integer> list, Boolean isRight) {
        sb.append('[');

        if (!list.isEmpty()) {
            if (!isRight) {
                sb.append(list.pollFirst());
                while (!list.isEmpty()) {
                    sb.append(',').append(list.pollFirst());
                }
            } else {
                sb.append(list.pollLast());
                while (!list.isEmpty()) {
                    sb.append(',').append(list.pollLast());
                }
            }
        }
        sb.append(']').append('\n');
    }
}