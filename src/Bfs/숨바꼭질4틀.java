package Bfs;

import java.io.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class 숨바꼭질4틀 {
    static class Node {
        int x;
        int count;

        public Node(int x, int count) {
            this.x = x;
            this.count = count;
        }
    }

    static int n, m;
    static Queue<Node> q = new LinkedList<>();
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();
    static int min = Integer.MAX_VALUE;
    static int max = 100000;
    static int[] prev = new int[max + 1]; // 이전

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // sol
        visited = new boolean[max + 1];
        bfs();

        // 출력
        System.out.println(min);

        Stack<Integer> stack = new Stack<>();
        int node = m;
        while (node != n) { // 역이동
            stack.push(node);
            node = prev[node];
        }
        stack.push(n);

        while (!stack.isEmpty()) {
            sb.append(stack.pop()).append(" ");
        }
        System.out.println(sb.toString());
    }

    private static void bfs() {
        q.add(new Node(n, 0));

        while (!q.isEmpty()) {
            Node node = q.poll();
            visited[node.x] = true;

            if (node.x == m) {
                min = Math.min(min, node.count);
                break;
            }

            // 순간이동
            if (node.x * 2 <= max && !visited[node.x * 2]) {
                q.add(new Node(node.x * 2, node.count + 1));
                prev[node.x * 2] = node.x;
            }

            // 앞으로 1 이동
            if (node.x + 1 <= max && !visited[node.x + 1]) {
                q.add(new Node(node.x + 1, node.count + 1));
                prev[node.x + 1] = node.x;
            }

            // 뒤로 1이동
            if (node.x - 1 >= 0 && !visited[node.x - 1]) {
                q.add(new Node(node.x - 1, node.count + 1));
                prev[node.x - 1] = node.x;
            }
        }
    }
}
