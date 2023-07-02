package 백트래킹;

import java.io.*;
import java.util.*;

public class Nqueen {

    public static int[] col;
    public static int n;
    public static int count = 0;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        col = new int[n];

        nQueen(0);
        System.out.println(count);
    }

    private static void nQueen(int row) {

        // 기저 조건
        if (row == n) {
            count ++;
            return;
        }
        for(int i = 0; i < n; i++){
            col[row] =i; // 현재 행에서 i번째 열에 퀸을 놓는다.
            if(isSafe(row)){
                nQueen(row +1);
            }
            //nQueen(row +1);
        }
    }

    private static boolean isSafe(int row) {
        for(int i = 0; i < row; i++){
            //같은 열에 있는 경우, 또는 대각선 상에 위치한 경우 유효하지 않음
            if (col[row] == col[i] || Math.abs(col[row] - col[i]) == row - i) {
                return false;
            }
        }
        return true;
    }

}
