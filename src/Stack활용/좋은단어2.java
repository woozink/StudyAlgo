package Stack활용;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class 좋은단어2 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int count = 0;
        for (int i = 0; i < n; i++) {
            String str = br.readLine();

            if (solve(str)) {
                count++;
            }
        }
        System.out.println(count);
    }

    private static boolean solve(String str) {
        Stack<Character> stack = new Stack<>();

        for(int i = 0; i < str.length(); i++){

            char c = str.charAt(i);
            if(stack.isEmpty() || stack.peek() != c){
                stack.push(c);
            }


            else {
                System.out.println(stack.peek());
                stack.pop();
            }

        }
        return stack.isEmpty();
    }
}
