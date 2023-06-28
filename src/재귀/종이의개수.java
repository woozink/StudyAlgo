package 재귀;

import java.io.*;
import java.util.*;

public class 종이의개수 {

    static int grayy, white, black = 0;
    static int[][] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 만약 종이가 모두 같은 수로 되어 있다면 이 종이를 그대로 사용한다.
        // (1)이 아닌 경우에는 종이를 같은 크기의 종이 9개로 자르고, 각각의 잘린 종이에 대해서 (1)의 과정을
        // 반복한다.

        int n = Integer.parseInt(br.readLine());
        arr = new int[n][n];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        recur(0, 0, n);

        System.out.println(grayy);
        System.out.println(white);
        System.out.println(black);

    }

    private static void recur(int row, int col, int size) {

        // 만약 같은 색상으로 이루어져 있다면 
        if (colorCheck(row, col, size)) {
            if (arr[row][col] == -1) {
                grayy++;
            } else if (arr[row][col] == 0) {
                white++;
            } else if (arr[row][col] == 1) {
                black++;
            }
            return;
        }

        int newSize = size / 3;


        recur(row, col, newSize); // 왼쪽 위
        recur(row, col + newSize, newSize); // 중앙위
        recur(row, col + 2 * newSize, newSize); // 오른쪽위

        recur(row + newSize, col, newSize); //왼쪽 중간
        recur(row + newSize, col + newSize, newSize);
        recur(row + newSize, col + 2 * newSize, newSize);

        recur(row + 2 * newSize, col, newSize); //왼쪽 중간
        recur(row + 2 * newSize, col + newSize, newSize);
        recur(row + 2 * newSize, col + 2 * newSize, newSize);


    }

    private static boolean colorCheck(int row, int col, int size) {

        int color = arr[row][col];

        // 각 블럭의 시작점 부터 끝까지 검사
        for (int i = row; i < row + size; i++) {
            for (int j = col; j < col + size; j++) {
                if (color != arr[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }
}
