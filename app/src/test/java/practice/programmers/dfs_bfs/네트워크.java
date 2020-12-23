package practice.programmers.dfs_bfs;

public class 네트워크 {

    int n;
    int[][] computers;
    boolean[] visited;

    public int solution(int n, int[][] computers) {
        this.n = n;
        this.computers = computers;
        this.visited = new boolean[computers.length];
        int answer = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i);
                answer++;
            }
        }
        return answer;
    }

    public void dfs(int index) {
        visited[index] = true;
        for (int j = 0; j < n; j++) {
            if (computers[index][j] == 1 && !visited[j]) {
                dfs(j);
            }
        }
    }
}
