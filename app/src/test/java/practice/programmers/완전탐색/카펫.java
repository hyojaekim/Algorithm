package practice.programmers.완전탐색;

public class 카펫 {

    public int[] solution(int brown, int red) {
        int sum = brown + red;

        for (int width = 3; width < sum / 2; width++) {
            if (sum % width == 0) {
                int height = sum / width;
                if (width >= height && (width * 2) + (height * 2) - 4 == brown) {
                    return new int[]{width, height};
                }
            }
        }
        return new int[]{};
    }
}