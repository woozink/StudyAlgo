package Deque;

import java.io.*;
import java.util.*;

public class AC {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        ArrayDeque<Integer> deque;
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());


        while (T-- > 0) {

            String command = br.readLine();

            // 배열의 개수
            int n = Integer.parseInt(br.readLine());

            st = new StringTokenizer(br.readLine(), "[],");
            deque = new ArrayDeque<Integer>();

            for (int i = 0; i < n; i++) {
                deque.add(Integer.parseInt(st.nextToken()));
            }


            Calulator(command, deque);
        }
        System.out.println(sb);
    }


    public static void Calulator(String command, ArrayDeque<Integer> deque) {
        boolean isRight = true;

        for (char cmd : command.toCharArray()) {
            if(cmd == 'R'){
                isRight = !isRight;
                continue;
            }

            if(isRight){
                if(deque.pollFirst() == null){
                    sb.append("error").append('\n');
                    return;
                }
            }

            else{
                if(deque.pollLast() == null){
                    sb.append("error").append('\n');
                    return;
                }
            }
        }

        // 모든 함수들이 정상적으로 작동했다면, 덱의 남은 요소들을 출력문으로 만들어준다.
        makePrintString(deque, isRight);
    }

    private static void makePrintString(ArrayDeque<Integer> deque, boolean isRight) {
        sb.append('['); // 대괄호를 저장

        if(deque.size() >0){
            if(isRight){ // 정방향
                sb.append(deque.pollFirst());
                while(!deque.isEmpty()){
                    sb.append(',').append(deque.pollFirst());
                }
            }
            else{ // 역방향
                sb.append(deque.pollLast());
                while(!deque.isEmpty()){
                    sb.append(',').append(deque.pollLast());
                }
            }
        }
        sb.append(']').append('\n');
    }
}