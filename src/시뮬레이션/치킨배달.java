package 시뮬레이션;

import java.io.*;
import java.util.*;

/*
1. 집과 치킨집의 좌표를 각각의 LIST에 저장을 해둔다.
2. 치킨집이 open한 개수가 m과 같다면 , 모든 집에 대해서 m개의 치킨집 중 최단 거리를 구한다.
3. 탐색을 시작하는 지점이 치킨집의 list size가 벗어나게 되면 종료한다.
 */

public class 치킨배달 {

    static class Node {

        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n, m;
    static int[][] map;
    static ArrayList<Node> person;
    static ArrayList<Node> chicken;
    static int answer;
    static boolean[] open;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        // 초기화
        map = new int[n][n];
        person = new ArrayList<>();
        chicken = new ArrayList<>();

        // 초기 입력
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());

                // list들에다가 값 넣어 줘야지.
                if (map[i][j] == 1) {
                    person.add(new Node(i, j));
                } else if (map[i][j] == 2) {
                    chicken.add(new Node(i, j));
                }
            }
        }
        // 출력
        answer = Integer.MAX_VALUE;
        open = new boolean[chicken.size()];

        dfs(0, 0);

        System.out.println(answer);
    }

    private static void dfs(int start, int cnt) {

        // 기저 조건
        if (cnt == m) {
            int result = 0;
            for (int i = 0; i < person.size(); i++) {
                int temp = Integer.MAX_VALUE;
                for (int j = 0; j < chicken.size(); j++) {
                    if (open[j]) {  // 선택된 치킨집만 고려
                        int distance = Math.abs(person.get(i).x - chicken.get(j).x) + Math.abs(person.get(i).y - chicken.get(j).y);
                        temp = Math.min(temp, distance);
                    }
                }
                result += temp;
            }
            answer = Math.min(answer, result);
            return;
        }

        for (int i = start; i < chicken.size(); i++) {
            open[i] = true;
            dfs(i + 1, cnt + 1);
            open[i] = false;

        }

    }
}
