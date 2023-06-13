package Stack활용;

import java.io.*;
import java.util.*;

public class 균형잡힌세상 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            String str = br.readLine();

            if (str.equals(".")) {
                break;
            }
            sb.append(slove(str)).append('\n');
        }
        System.out.println(sb);
    }

    private static String slove(String str) {

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {

            char c = str.charAt(i);

            if (c == '(' || c == '[') {
                stack.push(c);
            }

            else if (c == ')') {
                if (stack.isEmpty() || stack.peek() != ('(')) {
                    return "no";
                } else {
                    stack.pop();
                }
            }


            else if (c == ']') {
                if (stack.isEmpty() || stack.peek()!='[') {
                    return "no";
                } else {
                    stack.pop();
                }
            }

        }
        if (stack.isEmpty()) {
            return "yes";
        }
        else{
            return"no";
        }

    }
}
