package 시뮬레이션;
import java.util.*;

class 치킨배달 {
    private static int[] rewards; // 친구 단계별 보상 금액을 저장할 배열
    private static int[][] graph; // 친구 관계를 저장할 그래프
    private static boolean[] visited; // 방문한 노드를 체크할 배열
    private static int target; // 보상을 받을 특정 사람
    private static int limit; // 친구로 인정되는 최대 단계

    public static void main(String[] args) {
        치킨배달 solution = new 치킨배달();
        int[][] relationships = {{1, 2}, {2, 3}, {2, 6}, {3, 4}, {4, 5}};
        int target = 2;
        int limit = 3;
        int result = solution.solution(relationships, target, limit);
        System.out.println(result); // 37

        target = 1;
        limit = 2;
        result = solution.solution(relationships, target, limit);
        System.out.println(result); // 27
    }

    public int solution(int[][] relationships, int target, int limit) {
        int answer = 0;
        this.target = target;
        this.limit = limit;

        // 친구 단계별 보상 금액을 초기화합니다.
        rewards = new int[limit + 1];
        for (int i = 1; i <= limit; i++) {
            rewards[i] = i == 1 ? 5 : rewards[i - 1] + 5;
        }

        // 친구 관계를 저장할 그래프를 생성합니다.
        int maxPerson = getMaxPerson(relationships);
        graph = new int[maxPerson + 1][maxPerson + 1];
        for (int[] relationship : relationships) {
            int person1 = relationship[0];
            int person2 = relationship[1];
            graph[person1][person2] = 1;
            graph[person2][person1] = 1;
        }

        // DFS를 이용하여 보상을 계산합니다.
        visited = new boolean[maxPerson + 1];
        answer = dfs(target, 1);

        return answer;
    }

    private static int getMaxPerson(int[][] relationships) {
        int maxPerson = 0;
        for (int[] relationship : relationships) {
            maxPerson = Math.max(maxPerson, Math.max(relationship[0], relationship[1]));
        }
        return maxPerson;
    }

    private static int dfs(int person, int level) {
        visited[person] = true;
        int totalReward = 0;

        // 현재 단계의 보상을 계산합니다.
        totalReward += rewards[level];

        // 현재 단계가 최대 단계보다 작거나 같은 경우에만 계속 탐색합니다.
        if (level <= limit) {
            for (int i = 1; i < graph.length; i++) {
                if (graph[person][i] == 1 && !visited[i]) {
                    // 새롭게 알게된 친구인 경우 다음 단계로 탐색합니다.
                    totalReward += dfs(i, level + 1);
                }
            }
        }

        return totalReward;
    }
}
