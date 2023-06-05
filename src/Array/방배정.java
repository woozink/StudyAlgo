package Array;

import java.io.*;
import java.util.*;

public class 방배정 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken()); // 총 인원 수
        int k = Integer.parseInt(st.nextToken()); // 방배정 최대 수

        int[][] student = new int[7][2];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int gender = Integer.parseInt(st.nextToken());
            int grade = Integer.parseInt(st.nextToken());

            student[grade][gender]++;
        }

        int room = 0;


        for (int i = 1; i < 7; i++) {
            for (int j = 0; j < 2; j++) {

                if (student[i][j] >= k) {
                    room = room + (student[i][j] / k);

                    // 핵심 놓치기 쉬움
                    if (student[i][j] % k >= 1) {
                        room++;
                    }
                    continue;
                }

                if (student[i][j] == 0) {
                    continue;
                } else {
                    room++;
                }
            }
        }

        System.out.println(room);


    }
}
