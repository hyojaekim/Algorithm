package practice.sample.dijkstra;

public class FloydWarshall {

	int INF = 10000005; //TODO 조절

	public void solution(int n, int e, int[][] info) {
		int[][] board = new int[n][n];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j) continue; //TODO i에서 다시 i로 오는 경우 구할려면 주석
				board[i][j] = INF;
			}
		}
		for (int i = 0; i < e; i++) {
			int x = info[i][0];
			int y = info[i][1];
			int c = info[i][2];
			board[x][y] = Math.min(c, board[x][y]);
		}
	}

	private void floyd(int n, int[][] board) {
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (board[i][k] + board[k][j] < board[i][j]) board[i][j] = board[i][k] + board[k][j];
				}
			}
		}
	}
}
