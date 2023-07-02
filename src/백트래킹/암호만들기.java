package 백트래킹;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 암호만들기 {

    static int l, c;
    static char[] arr;
    static boolean[] visit;
    static char[] result;
    static StringBuilder sb = new StringBuilder();
    static char[] VOWELS = {'a', 'e', 'i', 'o', 'u'};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        l = Integer.parseInt(st.nextToken()); // 암호의 길이
        c = Integer.parseInt(st.nextToken()); // 입력 받은 문자열

        arr = new char[c];
        visit = new boolean[c];

        String str = br.readLine();
        st = new StringTokenizer(str);
        for (int i = 0; i < c; i++) {
            arr[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(arr);

        result = new char[l];
        back(0, 0, 0, 0);
        System.out.println(sb);
    }

    private static void back(int start, int depth, int vowelCount, int consonantCount) {
        if (depth == l) {
            if (vowelCount >= 1 && consonantCount >= 2) {
                for (int i = 0; i < result.length; i++) {
                    sb.append(result[i]);
                }
                sb.append('\n');
            }
            return;
        }


        for (int i = start; i < c; i++) {
            if (!visit[i]) {
                visit[i] = true;
                result[depth] = arr[i];

                if (isVowel(arr[i])) {
                    back(i + 1, depth + 1, vowelCount + 1, consonantCount);
                } else {
                    back(i + 1, depth + 1, vowelCount, consonantCount + 1);
                }

                visit[i] = false;

            }
        }
    }

    private static boolean isVowel(char ch) {

        for (char vowel : VOWELS) {
            if (vowel == ch) {
                return true;
            }
        }
        return false;
    }
}
