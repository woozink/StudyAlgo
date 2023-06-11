package Deque;

import java.io.*;
import java.util.*;

public class 덱 {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        ArrayDeque<Integer> deque = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {

            String[] x = br.readLine().split(" ");


            switch (x[0]) {

                case "push_front": {
                    deque.addFirst(Integer.parseInt(x[1]));
                    break;
                }

                case "push_back": {
                    deque.addLast(Integer.parseInt(x[1]));
                    break;
                }

                case "pop_front": {
                    if (!deque.isEmpty()) {
                        sb.append(deque.pollFirst()).append('\n');
                    } else {
                        sb.append(-1).append('\n');
                    }
                    break;
                }

                case "pop_back": {
                    if (!deque.isEmpty()) {
                        sb.append(deque.pollLast()).append('\n');
                    } else {
                        sb.append(-1).append('\n');
                    }
                    break;
                }

                case "size": {
                    sb.append(deque.size()).append('\n');
                    break;
                }

                case "empty": {
                    if (!deque.isEmpty()) {
                        sb.append(0).append('\n');
                    } else {
                        sb.append(1).append('\n');
                    }
                    break;
                }

                case "front": {
                    if (!deque.isEmpty()) {
                        sb.append(deque.peekFirst()).append('\n');
                    } else {
                        sb.append(-1).append('\n');
                    }
                    break;
                }

                case "back": {
                    if (!deque.isEmpty()) {
                        sb.append(deque.peekLast()).append('\n');
                    } else {
                        sb.append(-1).append('\n');
                    }
                    break;
                }

            }

        }
        System.out.println(sb);

    }

}