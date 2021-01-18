package practice.programmers.lv2;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class 방금그곡_3차 {

	@Test
	void test() {
		String solution = solution("CC#BCC#BCC#BCC#B", new String[]{"03:00,03:30,FOO,CC#B", "04:00,04:08,BAR,CC#BCC#BCC#B"});
		Assertions.assertEquals(solution, "FOO");
	}

	/**
	 * [문제] https://programmers.co.kr/learn/courses/30/lessons/17683
	 * [분류] 구현
	 *
	 * @param m 주어진 악보
	 * @param musicinfos 0:시작시간, 1:끝난시간, 2:제목, 3:악보
	 * @return 조건에 일치하는 음악의 제목을 반환하라.
	 */
	public String solution(String m, String[] musicinfos) {
		m = getScore(m);
		List<Music> musicList = getMusicList(musicinfos);
		List<Music> result = new ArrayList<>();

		for (Music music : musicList) {
			if (music.time < m.length()) continue;
			if (music.possible(m)){
				result.add(music);
			}
		}

		if (result.size() == 0) return "(None)";

		result.sort((o1, o2) -> {
			int n = o2.time - o1.time;
			if (n != 0) return n;
			return o1.index - o2.index;
		});
		return result.get(0).title;
	}

	private String getScore(String s) {
		char[] result = s.toCharArray();
		for (int i = 0; i < s.length(); i++) {
			if (s.charAt(i) != '#') continue;
			result[i - 1] = Character.toLowerCase(result[i - 1]);
		}
		return new String(result).replace("#", "");
	}

	private List<Music> getMusicList(String[] musicinfos) {
		List<Music> musicList = new ArrayList<>();
		for (int i = 0; i < musicinfos.length; i++) {
			String[] temp = musicinfos[i].split(",");
			musicList.add(new Music(temp[0], temp[1], temp[2], getScore(temp[3]), i));
		}
		return musicList;
	}

	class Music {
		int time;
		String title;
		String score;
		int index;

		public Music(String startTime, String endTime, String title, String score, int index) {
			this.time = getTime(startTime, endTime);
			this.title = title;
			this.score = score;
			this.index = index;
		}

		private int getTime(String startTime, String endTime) {
			String[] convertedStartTime = startTime.split(":");
			String[] convertedEndTime = endTime.split(":");

			int start = (Integer.parseInt(convertedStartTime[0]) * 60) + Integer.parseInt(convertedStartTime[1]);
			int end = (Integer.parseInt(convertedEndTime[0]) * 60) + Integer.parseInt(convertedEndTime[1]);
			return end - start;
		}

		public boolean possible(String m) {
			StringBuilder sb = new StringBuilder(score);
			if (time < score.length()) sb = new StringBuilder(score.substring(0, time));
			else if (time > score.length()) {
				for (int i = 1; i < time / score.length(); i++) {
					sb.append(score);
				}
				int n = time % score.length();
				if (n != 0) sb.append(score, 0, n);
			}
			String s = sb.toString();
			return s.contains(m);
		}
	}
}
