package 재귀;

import java.io.*;
import java.util.*;

public class 별찍기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        final int N = sc.nextInt();

        List<String> p = new ArrayList<>(N);
        //N이 3일때 세팅
        p.add("  *  ");
        p.add(" * * ");
        p.add("*****");

        //N => 3*2^k, 3, 6, 12, 24, 48, ... 이니깐 k가 1번째, 2번째 를 나타냄
        for(int k=1; 3 * (int)Math.pow(2, k) <= N; k++) { //6입력부터 루프 실행
            setStar(p);
        }

        //출력
        for(String s : p){
            System.out.println(s);
        }
    }

    private static void setStar(List<String> p){
        StringBuilder s = new StringBuilder();
        StringBuilder s2 = new StringBuilder();

        int pSize = p.size();

        for(int i=0; pSize>i; i++){
            s.delete(0,s.length());

            //전 단계의 그림을 아래 하나, 옆 하나 이렇게 총 2개 복사
            s.append(p.get(i)); //전 단계의 그림
            s.append(" ");      //공백
            s.append(p.get(i)); //전 단계의 그림

            p.add(s.toString());

            //전 단계 그림의 왼쪽, 오른쪽에 공백 추가
            s2.delete(0,s.length());
            for(int j=0; pSize>j; j++){
                s2.append(" ");
            }

            p.set(i, s2.toString() + p.get(i) + s2.toString());
        }
    }
}