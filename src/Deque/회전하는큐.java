package Deque;

import java.io.*;
import java.util.*;

public class 회전하는큐 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());


        int n = Integer.parseInt(st.nextToken()); // 큐의 크기
        int m = Integer.parseInt(st.nextToken()); // 뽑아내는 수

        LinkedList<Integer> deque = new LinkedList<>();
        List<Integer> list = new LinkedList<>();

        for(int i = 1; i <= n; i++){
            deque.offer(i);
        }

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < m; i++){
            list.add(Integer.parseInt(st.nextToken()));
        }

        int count = 0;

        for(int i = 0; i < m; i++){

            int targetIdx = deque.indexOf(list.get(i));
            int halfIdx;


            if(deque.size() %2 == 0){
                halfIdx = (deque.size()/2)-1;
            }else{
                halfIdx = (deque.size()/2);
            }


            if(targetIdx <= halfIdx){
                for(int j = 0; j < targetIdx; j++){
                    int tmp = deque.pollFirst();
                    deque.offerLast(tmp);
                    count++;
                }
            }
            // 위치 (중간 뒤)
            else{
                // 몇개 ?
                for(int j = 0; j < deque.size() - targetIdx; j++){
                    int tmp = deque.pollLast();
                    deque.offerFirst(tmp);
                    count++;
                }
            }


            deque.pollFirst();

        }

        System.out.println(count);




    }
}
