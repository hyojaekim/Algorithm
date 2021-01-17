package practice.programmers.lv2;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 방금그곡_3차 {

	@Test
	void test() {
//		String result = solution("ABCDEFG", new String[]{"12:00,12:14,HELLO,CDEFGAB", "13:00,13:05,WORLD,ABCDEF"});
		String result = solution("ABC", new String[]{"12:00,12:14,HELLO,C#DEFGAB", "13:00,13:05,WORLD,ABCDEF"});
		System.out.println(result);
	}

	Map<String, String> map;
	public String solution(String m, String[] musicinfos) {
		map = new HashMap<>();
		map.put("C#", "*"); map.put("D#", "!"); map.put("F#", "@"); map.put("G#", "$"); map.put("A#", "^");
		for (String key : map.keySet()) {
			if (!m.contains(key)) continue;
			m = m.replace(key, map.get(key));
		}

		List<Music> musicList = init(musicinfos);
		List<Music> result = new ArrayList<>();
		for (Music music : musicList) {
			if (music.score.contains(m)) {
				result.add(music);
			}
		}
		if (result.size() == 0) return "(None)";
		result.sort((o1, o2) -> {
			int n = o2.time - o1.time;
			if (n == 0) return o2.index - o1.index;
			return n;
		});
		return result.get(0).title;
	}

	private List<Music> init(String[] musicinfos) {
		List<Music> result = new ArrayList<>();
		for (int i = 0; i < musicinfos.length; i++) {
			String[] musicinfo = musicinfos[i].split(",");
			result.add(new Music(musicinfo[0], musicinfo[1], musicinfo[2], musicinfo[3], i));
		}
		return result;
	}

	class Music {
		int time; //재생 시간
		String title; //제목
		String score; //악보
		int index; //요청 순서

		public Music(String startTime, String endTime, String title, String score, int index) {
			this.time = calculateTime(startTime, endTime);
			this.title = title;
			this.score = setScore(score);
			this.index = index;
		}

		private int calculateTime(String startTime, String endTime) {
			String[] convertedStartTime = startTime.split(":");
			int startHour = Integer.parseInt(convertedStartTime[0]);
			int startMinute = Integer.parseInt(convertedStartTime[1]);

			String[] convertedEndTime = endTime.split(":");
			int endHour = Integer.parseInt(convertedEndTime[0]);
			int endMinute = Integer.parseInt(convertedEndTime[1]);

			if (startHour == endHour) {
				return endMinute - startMinute;
			} else if (endMinute > startMinute) {
				return ((endHour - startHour) * 60) + (endMinute - startMinute);
			} else if (endMinute < startMinute) {
				return ((endHour - startHour) * 60) - (startMinute - endMinute);
			}
			return 0;
		}

		private String setScore(String score) {
			for (String key : map.keySet()) {
				if (!score.contains(key)) continue;
				score = score.replace(key, map.get(key));
			}
			if (this.time == score.length()) return score;
			if (this.time < score.length()) return score.substring(0, this.time);
			int n = this.time / score.length();
			for (int i = 0; i < n; i++) {
				score += score;
			}
			String m = (this.time % score.length() == 0) ? "" : score.substring(0, this.time % score.length());
			return score + m;
		}
	}

}

