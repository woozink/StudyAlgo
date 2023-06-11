package LinkedList;

import org.apache.groovy.json.internal.IO;

import java.io.*;
import java.util.*;

public class 키로거 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        for (int x = 0; x < n; x++) {

            StringBuilder leftSb = new StringBuilder();
            StringBuilder rightSb = new StringBuilder();

            String str = br.readLine();

            for (int i = 0; i < str.length(); i++) {

                char ch = str.charAt(i);

                // 왼쪽으로 커서 이동
                if (ch=='<') {
                    if (leftSb.length() > 0) {
                        rightSb.append(leftSb.charAt(leftSb.length() - 1));
                        leftSb.deleteCharAt(leftSb.length() - 1);
                    }
                }

                // 오른쪽으로 커서 이동
                else if (ch=='>') {
                    if(rightSb.length()>0){
                        leftSb.append(rightSb.charAt(rightSb.length() -1));
                        rightSb.deleteCharAt(rightSb.length() -1);
                    }
                }

                // 왼쪽 문자 제거
                else if (ch=='-') {
                    if(leftSb.length()>0){
                        leftSb.deleteCharAt(leftSb.length() -1);
                    }

                }

                else {
                    leftSb.append(ch);
                }
            }

            System.out.println(leftSb.toString()+ rightSb.reverse().toString());
        }



    }
}
