package practice.boj;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class BOJ16235 {

	@Test
	void test() {
		int solution = solution(5, 2, 1, new int[][]{
						{2, 3, 2, 3, 2},
						{2, 3, 2, 3, 2},
						{2, 3, 2, 3, 2},
						{2, 3, 2, 3, 2},
						{2, 3, 2, 3, 2},
		}, new int[][]{
						{2, 1, 3},
						{3, 2, 3}
		});

		Assertions.assertEquals(solution, 2);
	}

	@Test
	void test2() {
		int solution = solution(5, 2, 2, new int[][]{
						{2, 3, 2, 3, 2},
						{2, 3, 2, 3, 2},
						{2, 3, 2, 3, 2},
						{2, 3, 2, 3, 2},
						{2, 3, 2, 3, 2},
		}, new int[][]{
						{2, 1, 3},
						{3, 2, 3}
		});

		Assertions.assertEquals(solution, 15);
	}

	class Tree {
		int x, y, age;

		public Tree(int x, int y, int age) {
			this.x = x;
			this.y = y;
			this.age = age;
		}
	}

	int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
	int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};
	PriorityQueue<Tree> trees;
	int[][] map;

	public int solution(int n, int m, int k, int[][] A, int[][] initialTrees) {
		this.map = initMap(n);
		this.trees = initTrees(initialTrees);

		while (k > 0) {
			List<Tree> deadTrees = spring();
			summer(deadTrees);
			fall(n);
			winter(n, A);
			k--;
		}
		return trees.size();
	}

	private int[][] initMap(int n) {
		int[][] map = new int[n][n];
		for (int[] line : map) {
			Arrays.fill(line, 5);
		}
		return map;
	}

	private PriorityQueue<Tree> initTrees(int[][] initialTrees) {
		PriorityQueue<Tree> trees = new PriorityQueue<>(Comparator.comparingInt(o -> o.age));
		for (int[] initialTree : initialTrees) {
			int x = initialTree[0] - 1;
			int y = initialTree[1] - 1;
			int age = initialTree[2];
			trees.add(new Tree(x, y, age));
		}
		return trees;
	}

	private List<Tree> spring() { //죽은 나무를 반환한다.
		List<Tree> deadTrees = new ArrayList<>();
		PriorityQueue<Tree> trees = new PriorityQueue<>(Comparator.comparingInt(o -> o.age));

		for (Tree tree : this.trees) { //어린 순으로
			if (map[tree.x][tree.y] >= tree.age) { //나이 만큼 양분을 먹는다.
				map[tree.x][tree.y] -= tree.age;
				tree.age++;
				trees.add(tree);
			} else { //먹지 못하면 죽는다.
				deadTrees.add(tree);
			}
		}
		this.trees = trees;
		return deadTrees;
	}

	private void summer(List<Tree> deadTrees) {
		for (Tree deadTree : deadTrees) {
			map[deadTree.x][deadTree.y] += (deadTree.age / 2);
		}
	}

	private void fall(int n) { //8방향으로 번식한다.
		PriorityQueue<Tree> trees = new PriorityQueue<>(Comparator.comparingInt(o -> o.age));
		for (Tree tree : this.trees) {
			trees.add(tree);
			if (tree.age % 5 != 0) continue;
			for (int i = 0; i < 8; i++) {
				int nx = tree.x + dx[i];
				int ny = tree.y + dy[i];

				if (nx < 0 || ny < 0 || nx >= n || ny >= n) continue;
				Tree createdTree = new Tree(nx, ny, 1);
				trees.add(createdTree);
			}
		}
		this.trees = trees;
	}

	private void winter(int n, int[][] A) { //양분 추가하기
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				map[i][j] += A[i][j];
			}
		}
	}
}
