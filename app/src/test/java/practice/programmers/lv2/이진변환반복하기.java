package practice.programmers.lv2;

public class 이진변환반복하기 {

	/**
	 * [문제] https://programmers.co.kr/learn/courses/30/lessons/70129
	 * [분류] 구현
	 * [조건]
	 * 1. 모든 0을 제거
	 * 2. x의 길이를 c라고 하면, c를 2진법으로 표현한 문자열로 바꾼다.
	 * 3. 1이 될때 까지 반복
	 *
	 * @param s 문자열
	 * @return 0:횟수, 1:0이 없어진 횟수
	 */
	public int[] solution(String s) {
		int[] answer = new int[2];
		while (!s.equals("1")) {
			String temp = s.replace("0", "");
			answer[1] += s.length() - temp.length();
			s = Integer.toBinaryString(temp.length());
			answer[0]++;
		}
		return answer;
	}
}
