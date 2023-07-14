package 시뮬레이션;

import java.io.*;
import java.util.*;

public class 구슬탈출2 {

    static class PairBeads {
        int redX;
        int redY;
        int blueX;
        int blueY;
        int cnt;

        public PairBeads(int redX, int redY, int blueX, int blueY, int cnt) {
            this.redX = redX;
            this.redY = redY;
            this.blueX = blueX;
            this.blueY = blueY;
            this.cnt = cnt;
        }
    }


    static int n, m;
    static char[][] board;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new char[n][m];

        for (int i = 0; i < n; i++) {
            board[i] = br.readLine().toCharArray();
        }

        PairBeads pb = new PairBeads(0, 0, 0, 0, 0);

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'R') {
                    pb.redX = i;
                    pb.redY = j;
                    board[i][j] = '.';
                }

                if (board[i][j] == 'B') {
                    pb.blueX = i;
                    pb.blueY = j;
                    board[i][j] = '.';
                }
            }
        }

        System.out.println(bfs(pb));

    }

    private static int bfs(PairBeads pb) {

        Queue<PairBeads> q = new LinkedList<>();
        q.add(pb);

        while (!q.isEmpty()) {
            PairBeads beads = q.poll();

            if (beads.cnt == 10) {
                continue;
            }

            for (int d = 0; d < 4; d++) {
                int redX = beads.redX;
                int redY = beads.redY;
                int blueX = beads.blueX;
                int blueY = beads.blueY;

                boolean isRedHole = false;
                boolean isBlueHole = false;

                while (true) {
                    int newRedX = redX + dx[d];
                    int newRedY = redY + dy[d];
                    if (board[newRedX][newRedY] == '#') {
                        break;
                    }

                    // 빨간 구슬이 구멍에 빠진 경우 체크합니다.
                    if (board[newRedX][newRedY] == 'O') {
                        isRedHole = true;
                        break;
                    }

                    redX = newRedX;
                    redY = newRedY;
                }

                while (true) {
                    int newBlueX = blueX + dx[d];
                    int newBlueY = blueY + dy[d];

                    if (board[newBlueX][newBlueY] == '#') {
                        break;
                    }

                    if (board[newBlueX][newBlueY] == 'O') {
                        isBlueHole = true;
                        break;
                    }
                    blueX = newBlueX;
                    blueY = newBlueY;
                }


                if (isBlueHole) {
                    continue;
                } else if (isRedHole) {
                    return beads.cnt + 1;
                }

                if (beads.redX == redX && beads.redY == redY && beads.blueX == blueX &&
                        beads.blueY == blueY) {
                    continue;
                }

                if (redX == blueX && redY == blueY) {
                    if (d == 0) {
                        if (beads.redX < beads.blueX) {
                            redX--;
                        } else {
                            blueX--;
                        }
                    } else if (d == 1) {
                        if (beads.redX < beads.blueX) {
                            blueX++;
                        } else {
                            redX++;
                        }
                    } else if (d == 2) {
                        if (beads.redY < beads.blueY) {
                            redY--;
                        } else {
                            blueY--;
                        }
                    } else if (d == 3) {
                        if (beads.redY < beads.blueY) {
                            blueY++;
                        } else {
                            redY++;
                        }
                    }
                }

                q.add(new PairBeads(redX, redY, blueX, blueY, beads.cnt+1));
            }
        }

        return -1;
    }
}
