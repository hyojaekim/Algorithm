package programmers.dfs_bfs;

public class 타겟넘버 {

    int[] numbers;
    int target;

    public int solution(int[] numbers, int target) {
        this.numbers = numbers;
        this.target = target;
        return dfs(0, 0);
    }

    public int dfs(int index, int number) {
        if (index >= numbers.length) {
            return (number == target) ? 1 : 0;
        }
        return dfs(index + 1, number + numbers[index])
                + dfs(index + 1, number - numbers[index]);
    }
}
