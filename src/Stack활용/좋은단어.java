package Stack활용;

import java.io.*;
import java.util.*;

public class 좋은단어 {
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

        Stack<Character> FirstStack = new Stack<>();
        Stack<Character> LastStack = new Stack<>();
        int halfSize = str.length() / 2;

        if (str.length() % 2 != 0) {
            return false;
        }

        for (int i = 0; i < halfSize; i++) {

            char c = str.charAt(i);

            if (c == 'A' || c == 'B') {
                FirstStack.push(c);
            } else {
                return false;
            }
        }

        for (int i = halfSize; i < str.length(); i++) {
            char c = str.charAt(i);

            if (c == 'A' || c == 'B') {
                LastStack.push(c);
            } else {
                return false;
            }
        }
        while (!FirstStack.isEmpty()) {

            if (FirstStack.peek() == LastStack.peek()) {
                FirstStack.pop();
                LastStack.pop();
            }else{

                return false;
            }
        }

        if (FirstStack.isEmpty()){
            return true;
        }else{
            return false;
        }
    }
}
