package codeTree;

public class PaperDecoration {

    public static void main(String[] args) {
        PaperDecoration solution = new PaperDecoration();

        int[][] result1 = solution.solution(8, 6, new int[]{1, -1, -1}, new int[][]{{1, 1}, {2, 2}, {4, 4}});
        printResult(result1);

        int[][] result2 = solution.solution(4, 4, new int[]{1, 1}, new int[][]{{3, 1}});
        printResult(result2);
    }

    public static void printResult(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("------");
    }


    public int[][] solution(int n, int m, int[] fold, int[][] cut) {
        int[][] paper = new int[n][m];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                paper[i][j] = 1;
            }
        }

        for (int f : fold) {
            if (f == 1) {  // 가로로 접기
                for (int i = 0; i < n / 2; i++) {
                    for (int j = 0; j < m; j++) {
                        paper[i][j] = paper[i][j] & paper[n - 1 - i][j];
                        paper[n - 1 - i][j] = paper[i][j];
                    }
                }
            } else {  // 세로로 접기
                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < m / 2; j++) {
                        paper[i][j] = paper[i][j] & paper[i][m - 1 - j];
                        paper[i][m - 1 - j] = paper[i][j];
                    }
                }
            }
        }

        for (int[] c : cut) {
            paper[c[0] - 1][c[1] - 1] = 0;
        }

        for (int i = fold.length - 1; i >= 0; i--) {
            int f = fold[i];
            if (f == 1) {  // 가로로 접기
                for (int j = 0; j < n / 2; j++) {
                    for (int k = 0; k < m; k++) {
                        paper[n - 1 - j][k] = paper[j][k];
                    }
                }
            } else {  // 세로로 접기
                for (int j = 0; j < n; j++) {
                    for (int k = 0; k < m / 2; k++) {
                        paper[j][m - 1 - k] = paper[j][k];
                    }
                }
            }
        }

        return paper;
    }
}
