package programmers.dfs_bfs;

import java.util.LinkedList;
import java.util.Queue;

public class 네트워크2 {
    /**
     * [!!] computer[i][i] = 1 (항상)
     * @param n 컴퓨터 수
     * @param computers 연결 정보
     *        ex) [i][j] = 1: i 컴퓨터와 j 컴퓨터는 연결되어 있다.
     * @return answer 네트워크 수
     */
    boolean[][] visited;
    int[][] computers;
    public int solution(int n, int[][] computers) {
        int answer = 0;
        this.visited = new boolean[n][n];
        this.computers = computers;
        // 방문하지 않았고, 연결되어 있으면 bfs를 통해 네트워크 카운트
        for (int i = 0; i < n; i++) {

            for (int j = 0; j < n; j++) {
                if (visited[i][j]) continue;
                if (computers[i][j] == 0) continue;
                answer++;
                Queue<ConnectedComputer> connectedComputers = new LinkedList<>();
                ConnectedComputer connectedComputer = new ConnectedComputer(i, j);
                visited[i][j] = true;
                connectedComputers.add(connectedComputer);
                bfs(connectedComputers, n);
            }
        }
        return answer;
    }

    /**
     * 1. firstComputer랑 연결된 컴퓨터를 방문한다.
     * 2. secondComputer랑 연결된 컴퓨터를 방문한다.
     */
    private void bfs(Queue<ConnectedComputer> connectedComputers, int n) {
        while (!connectedComputers.isEmpty()) {
            ConnectedComputer currentConnectedComputer = connectedComputers.poll();
            int firstComputer = currentConnectedComputer.firstComputer;
            int secondComputer = currentConnectedComputer.secondComputer;
            visit(n, firstComputer, connectedComputers);

            if (firstComputer != secondComputer) {
                visit(n, secondComputer, connectedComputers);
            }
        }
    }

    private void visit(int n, int computer, Queue<ConnectedComputer> connectedComputers) {
        for (int i = 0; i < n; i++) {
            if (visited[computer][i] || visited[i][computer]) continue;
            if (computers[computer][i] == 1 && computers[i][computer] == 1) {
                visited[computer][i] = true;
                visited[i][computer] = true;
                connectedComputers.add(new ConnectedComputer(computer, i));
            }
        }
    }

    class ConnectedComputer {
        int firstComputer;
        int secondComputer;

        public ConnectedComputer(int i, int j) {
            this.firstComputer = i;
            this.secondComputer = j;
        }
    }
}
