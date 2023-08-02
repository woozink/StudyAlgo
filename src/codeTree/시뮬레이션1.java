import java.util.Scanner;

public class 시뮬레이션1 {
    public static final int MAX_K = 100000;

    public static int n;
    public static int[] a = new int[2 * MAX_K + 1];
    public static int[] cntB = new int[2 * MAX_K + 1];
    public static int[] cntW = new int[2 * MAX_K + 1];
    public static int b, w, g;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 변수 입력
        n = sc.nextInt();

        int cur = MAX_K;
        for(int i = 1; i <= n; i++) {
            int x = sc.nextInt();
            char c = sc.next().charAt(0);
            if(c == 'L') {
                // x칸 왼쪽으로 칠합니다.
                while(x-- > 0) {
                    a[cur] = 1;
                    cntW[cur]++;
                    if(x > 0) cur--;
                }
            }
            else {
                // x칸 오른쪽으로 칠합니다.
                while(x-- > 0) {
                    a[cur] = 2;
                    cntB[cur]++;
                    if(x > 0) cur++;
                }
            }
        }

        for(int i = 0; i <= 2 * MAX_K; i++) {

            if(a[i] == 1) w++;
            else if(a[i] == 2) b++;
        }

        // 정답을 출력합니다.
        System.out.print(w + " " + b);
    }
}