import java.util.Scanner;

public class 개발자순위 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int K = scanner.nextInt();
        int N = scanner.nextInt();

        int[][] ranks = new int[K][N];

        // 각 경기의 순위 정보를 입력받습니다.
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < N; j++) {
                ranks[i][scanner.nextInt() - 1] = j;
            }
        }

        int count = 0;

        // 모든 개발자 쌍 (a, b)에 대해 확인합니다.
        for (int a = 0; a < N; a++) {
            for (int b = 0; b < N; b++) {
                if (a != b) {
                    boolean alwaysHigher = true;

                    for (int k = 0; k < K; k++) {
                        if (ranks[k][a] > ranks[k][b]) {
                            alwaysHigher = false;
                            break;
                        }
                    }

                    if (alwaysHigher) {
                        count++;
                    }
                }
            }
        }

        System.out.println(count);
        scanner.close();
    }
}
