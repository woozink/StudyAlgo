package Bfs;

import java.io.*;
import java.util.*;

public class 열쇠 {
    static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }


    static char[][] map;
    static boolean[][] visited;
    static boolean[] key;
    static ArrayList<Node>[] gate;
    static int h, w, count;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int tc = Integer.parseInt(br.readLine());

        for (int t = 0; t < tc; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            h = Integer.parseInt(st.nextToken());
            w = Integer.parseInt(st.nextToken());

            map = new char[h + 2][w + 2];
            visited = new boolean[h + 2][w + 2];
            key = new boolean[26];
            gate = new ArrayList[26];

            count = 0;

            // 문 초기화
            for (int i = 0; i < 26; i++) {
                gate[i] = new ArrayList<>();
            }

            // 테두리 빈칸으로 채우기
            for (int i = 0; i < h + 2; i++) {
                for (int j = 0; j < w + 2; j++) {
                    map[i][j] = '.';
                }
            }

            for (int i = 1; i <= h; i++) {
                String str = br.readLine();
                for (int j = 1; j <= w; j++) {
                    map[i][j] = str.charAt(j - 1);
                }
            }

            String keyInput = br.readLine();
            if (!keyInput.equals("0")) {
                for (int i = 0; i < keyInput.length(); i++) {
                    int temp = keyInput.charAt(i) - 'a';
                    key[temp] = true;
                }
            }

            bfs();
            System.out.println(count);


        }

    }

    private static void bfs() {

        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0));
        visited[0][0] = true;

        while (!q.isEmpty()) {
            Node p = q.poll();

            for (int i = 0; i < 4; i++) {
                int nx = p.x + dx[i];
                int ny = p.y + dy[i];

                if (nx < 0 || ny < 0 || nx >= h + 2 || ny >= w + 2) {
                    continue;
                }

                if (map[nx][ny] == '*' || visited[nx][ny]) {
                    continue;
                }

                int elem = map[nx][ny];
                if (elem - 'A' >= 0 && elem - 'A' <= 25) {
                    // 문 발견
                    if (key[elem - 'A']) {
                        map[nx][ny] = '.';
                        visited[nx][ny] = true;
                        q.add(new Node(nx, ny));
                    } else {
                        gate[elem - 'A'].add(new Node(nx, ny));
                    }
                } else if (elem - 'a' >= 0 && elem - 'a' <= 25) {
                    // 열쇠 발견
                    key[elem - 'a'] = true;
                    visited[nx][ny] = true;
                    q.add(new Node(nx, ny));

                    for (int j = 0; j <= 25; j++) {
                        if (gate[j].size() != 0 && key[j]) {
                            for (int z = 0; z < gate[j].size(); z++) {
                                Node temp = gate[j].get(z);
                                map[temp.x][temp.y] = '.';
                                visited[temp.x][temp.y] = true;
                                q.add(new Node(temp.x, temp.y));
                            }
                        }
                    }
                } else if (elem == '$') {
                    // 문서 발견
                    count++;
                    visited[nx][ny] = true;
                    q.add(new Node(nx, ny));
                } else {
                    // 빈 칸 '.'
                    visited[nx][ny] = true;
                    q.add(new Node(nx, ny));
                }

            }
        }
    }
}
