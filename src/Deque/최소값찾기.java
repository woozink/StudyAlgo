package Deque;
import java.io.*;
import java.util.*;

public class 최소값찾기 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int l = Integer.parseInt(st.nextToken());

        Deque<Pair> deq = new ArrayDeque<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++){
            int num = Integer.parseInt(st.nextToken());


            // 덱의 마지막 위치에서 부터 현재 값 보다 큰 값은 덱에서 제거
            while(!deq.isEmpty() && deq.getLast().value >= num){
                deq.removeLast();
            }

            // 덱의 마지막 위치에 현재 값 저장
            deq.addLast(new Pair(i, num));

            // 덱의 1번 위치에서 L의 범위를 벗어난 값을 덱에서 제거
            if(deq.getFirst().idx <= i -l){
                deq.removeFirst();
            }

            sb.append(deq.getFirst().value).append(" ");
        }

        System.out.println(sb);
    }

    public static class Pair{
        int idx;
        int value;

        public Pair(int idx, int value) {
            this.idx = idx;
            this.value = value;
        }
    }
}
