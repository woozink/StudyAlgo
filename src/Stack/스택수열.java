package Stack;
import java.io.*;
import java.util.*;


public class 스택수열 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();


        int n = Integer.parseInt(br.readLine());
        Stack <Integer> stack = new Stack<>();

        int start = 0;

        while(n-- > 0){
            int value = Integer.parseInt(br.readLine());

            if(value > start){
                for(int i =start+1; i<= value; i++){
                    stack.push(i);
                    sb.append('+').append('\n'); //
                }
                start = value;
            }


            // NO 출력 할 때
            // 위에 for문에서 판별하고 내려 오니까.
            else if(stack.peek() != value){
                System.out.println("NO");
                return;
            }

            stack.pop();
            sb.append('-').append('\n');
        }
        System.out.println(sb);
    }

}
