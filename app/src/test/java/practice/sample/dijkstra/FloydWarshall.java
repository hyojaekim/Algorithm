package practice.sample.dijkstra;

public class FloydWarshall {

	int INF = 1000000; //TODO 조절

	public void solution(int n, int[][] info) {
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
//				if (i == j) continue; //TODO i에서 다시 i로 오는 경우 구할려면 주석 해제
				if (info[i][j] == 0) info[i][j] = INF;
			}
		}
	}
	private void floyd(int n, int[][] info) {
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (info[i][k] + info[k][j] < info[i][j]) info[i][j] = 1;
				}
			}
		}
	}
}
