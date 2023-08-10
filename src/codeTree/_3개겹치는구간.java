package codeTree;

public class _3개겹치는구간{
    public static int n = 5;
    public static int[][] segments = new int[][]{
            {1, 3}, {2, 4}, {5, 8}, {6, 9}, {7, 10}
    };

    public static int getMaxOverlappedCnt(int i1, int i2, int i3) {
        int[] counting = new int[11]; // initialize
        for(int i = 0; i < n; i++) {
            if(i == i1 || i == i2 || i == i3)
                continue;

            int x1 = segments[i][0], x2 = segments[i][1];
            for(int j = x1; j <= x2; j++)
                counting[j]++;
        }

        int maxCnt = 0;
        for(int j = 0; j < 11; j++)
            maxCnt = Math.max(maxCnt, counting[j]);

        return maxCnt;
    }

    public static void main(String[] args) {
        int ans = 100;
        for(int i = 0; i < n; i++)
            for(int j = i + 1; j < n; j++)
                for(int k = j + 1; k < n; k++) {
                    int maxCnt = getMaxOverlappedCnt(i, j, k);
                    ans = Math.min(ans, maxCnt);
                }

        System.out.println(ans);
    }
}

