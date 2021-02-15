package practice.boj;

import org.junit.jupiter.api.Test;

import java.util.*;

public class BOJ11780 {

	@Test
	void test() {
		solution(5, 14, new int[][]{
						{1, 2, 2},
						{1, 3, 3},
						{1, 4, 1},
						{1, 5, 10},
						{2, 4, 2},
						{3, 4, 1},
						{3, 5, 1},
						{4, 5, 3},
						{3, 5, 10},
						{3, 1, 8},
						{1, 4, 2},
						{5, 1, 7},
						{3, 4, 2},
						{5, 2, 4},
		});
	}

	@Test
	void input() {
//		Scanner sc = new Scanner(System.in);
//		int n = Integer.parseInt(sc.nextLine());
//		int m = Integer.parseInt(sc.nextLine());
//
//		int[][] board = new int[m][3];
//		for (int i = 0; i < m; i++) {
//			String[] line = sc.nextLine().split(" ");
//			board[i][0] = Integer.parseInt(line[0]);
//			board[i][1] = Integer.parseInt(line[1]);
//			board[i][2] = Integer.parseInt(line[2]);
//		}
//
//		Main main = new Main();
//		main.solution(n, m, board);
	}

	int INF = 10000005;

	/**
	 * [문제] https://www.acmicpc.net/problem/11780
	 * [분류] 플루이드 와샬 알고리즘
	 *
	 * @param n 정점 개수
	 * @param e 간선 길이
	 * @param info [0]->[1] 가중치:[2]
	 */
	public void solution(int n, int e, int[][] info) {
		int[][] board = new int[n][n]; //초기화
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (i == j) continue;
				if (board[i][j] == 0) board[i][j] = INF;
			}
		}
		for (int i = 0; i < e; i++) {
			int x = info[i][0] - 1;
			int y = info[i][1] - 1;
			int c = info[i][2];
			board[x][y] = Math.min(c, board[x][y]);
		}

		int[][] kBoard = new int[n][n]; //k 정보 저장, k == -1이면 없다는 의미
		for (int[] temp : kBoard) {
			Arrays.fill(temp, -1);
		}

		floyd(n, board, kBoard); //플로이드 와샬

		//board 출력하기
		for (int[] arr : board) {
			for (int a : arr) {
				System.out.print(a + " ");
			}
			System.out.println();
		}

		//경로 저장하기
		Map<Key, List<Integer>> result = new HashMap<>();
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (kBoard[i][j] != -1) {
					//i k j 초기화
					List<Integer> linkedList = new LinkedList<>();
					linkedList.add(i);
					linkedList.add(kBoard[i][j]);
					linkedList.add(j);

					//중간에 뭐가 있으면 끼워넣기
					for (int index = 0; index < linkedList.size() - 1; index++) {
						int x = linkedList.get(index);
						int y = linkedList.get(index + 1);
						if (kBoard[x][y] == -1) continue;
						else {
							linkedList.add(index + 1, kBoard[x][y]);
							index--;
						}
					}
					result.put(new Key(i, j), linkedList);
				}
			}
		}

		//경로 출력하기
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				Key key = new Key(i, j);
				if (i == j) System.out.println("0");
				else if (!result.containsKey(key)) {
					System.out.println(2 + " " + (i + 1) + " " + (j + 1));
				} else {
					List<Integer> line = result.get(key);
					System.out.print(line.size() + " ");
					for (int num : line) {
						num++;
						System.out.print(num + " ");
					}
					System.out.println();
				}
			}
		}
	}

	private void floyd(int n, int[][] board, int[][] kBoard) {
		for (int k = 0; k < n; k++) {
			for (int i = 0; i < n; i++) {
				for (int j = 0; j < n; j++) {
					if (board[i][k] + board[k][j] < board[i][j]) {
						board[i][j] = board[i][k] + board[k][j];
						kBoard[i][j] = k;
					}
				}
			}
		}
	}

	class Key {
		int x;
		int y;

		public Key(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o) return true;
			if (o == null || getClass() != o.getClass()) return false;
			Key key = (Key) o;
			return x == key.x && y == key.y;
		}

		@Override
		public int hashCode() {
			return Objects.hash(x, y);
		}
	}
}
