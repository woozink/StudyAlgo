package codeTree;

public class 단위별로완탐예제 {
    public static int n = 5;
    public static int[][] segments = new int[][]{
            {1, 3}, {2, 4}, {5, 8}, {6, 9}, {7, 10}
    };
    public static void main(String[] args) {
        int ans = 100;
        for(int i = 0; i < n; i++) {
            int[] counting = new int[11]; // initialize
            for(int j = 0; j < n; j++) {
                if(j == i)
                    continue;

                int x1 = segments[j][0], x2 = segments[j][1];
                for(int k = x1; k <= x2; k++)
                    counting[k]++;
            }

            int maxCnt = 0;
            for(int j = 0; j < 11; j++)
                maxCnt = Math.max(maxCnt, counting[j]);
            ans = Math.min(ans, maxCnt);
        }

        System.out.println(ans);
    }
}
