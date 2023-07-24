package codeTree;

import java.util.*;

public class 부분문자열위치구하기 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String str1 = sc.next();
        String str2 = sc.next();

        boolean asd = false;


        for(int i = 0; i <= str1.length() - str2.length(); i++){
            int count = 0;


            for(int j = 0; j < str2.length(); j++){

                if(str1.charAt(i+j) == str2.charAt(j)){
                    count++;
                }else{
                    break;
                }
            }

            if(count == str2.length()){
                System.out.println(i);
                asd =true;
                break;
            }

        }

        if(asd == false){
            System.out.println(-1);
        }

    }
}