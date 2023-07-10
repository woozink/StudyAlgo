package 시뮬레이션;

import java.io.*;
import java.util.*;

public class 톱니바퀴 {

    static int[][] gear;
    static int d[]; // 기어의 회전 정보를 담고 있다.
    static int n, m;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        gear = new int[4][8];

        for (int i = 0; i < 4; i++) {
            String line = br.readLine();
            for (int j = 0; j < 8; j++) {
                gear[i][j] = line.charAt(j) - '0';
            }
        }
        int k = Integer.parseInt(br.readLine());


        for (int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            // 배열에 담긴 gear는 1부터 시작이 아닌 0부터 시작이라 -1을 해줌.
            int gearN = Integer.parseInt(st.nextToken()) - 1;
            int turn = Integer.parseInt(st.nextToken());

            // 톱니 바퀴의 회전 방향 정보를 담음
            d = new int[4];

            d[gearN] = turn;
            checkDir(gearN);
            gearTurn();

        }

        int total = 0;

        for (int i = 0; i < 4; i++) {
            total += Math.pow(2, i) * gear[i][0];
        }

        System.out.println(total);

    }

    private static void gearTurn() {
        int temp = 0;

        // 모든 톱니 바퀴에 대해서
        for(int i =0; i < 4; i++){
            // 시계 방향 회전
            if(d[i] == 1){
                temp = gear[i][7];
                for(int j =7; j >0; j--){
                    gear[i][j] = gear[i][j-1];
                }
                gear[i][0] = temp;
            }


            if(d[i] == -1){
                temp = gear[i][0];
                for(int j = 0; j < 7; j++){
                    gear[i][j] = gear[i][j+1];
                }
                gear[i][7] = temp;
            }
        }


    }

    private static void checkDir(int gearN) {

        //좌측 톱니 회전 방향 검사
        for (int i = gearN - 1; i >= 0; i--) {
            if (gear[i][2] != gear[i + 1][6]) {
                d[i] = -d[i + 1];
            }else{
                //회전을 하지 않으면 다음 톱니도 회전 x
                break;
            }
        }
        //우측 톱니 회전 방향 검사
        for (int i = gearN + 1; i <4; i++) {
            if (gear[i][6] != gear[i - 1][2]) {
                d[i] = -d[i - 1];
            }else{
                //회전을 하지 않으면 다음 톱니도 회전 x
                break;
            }
        }

    }

}
