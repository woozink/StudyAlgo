package programmers;
import java.util.*;

class 최대최소값 {
    public String solution(String s) {
        String answer = "";
        String str = s.replaceAll(" ", ",");
        String[] strArr = str.split(",");


        char[] arr = str.toCharArray();
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        System.out.println(Arrays.toString(strArr));

        for(int i = 0; i < strArr.length; i++){
            if(Integer.parseInt(strArr[i]) > max){

                max = Integer.parseInt(strArr[i]);
            }

            if(Integer.parseInt(strArr[i]) < min){

                min = Integer.parseInt(strArr[i]);
            }
        }

        answer = min +" " + max;


        return answer;
    }
}