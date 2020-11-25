package programmers.찾아라_프로그래밍_마에스터;

import java.util.HashSet;
import java.util.Set;

public class 포켓몬 {
  public int solution(int[] nums) {
    Set<Integer> totalNumbers = new HashSet<>();
    for (Integer num : nums) {
      totalNumbers.add(num);
    }

    if (totalNumbers.size() >= nums.length / 2) {
      return nums.length / 2;
    } else {
      return totalNumbers.size();
    }
  }
}
