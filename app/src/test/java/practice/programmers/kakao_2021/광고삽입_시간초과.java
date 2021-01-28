package practice.programmers.kakao_2021;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class 광고삽입_시간초과 {
	@Test
	void test() {
		String result = solution("02:03:55", "00:14:15", new String[]{"01:20:15-01:45:14", "00:40:31-01:00:00", "00:25:50-00:48:29", "01:30:59-01:53:29", "01:37:44-02:02:30"});
		Assertions.assertEquals(result, "01:30:59");
	}

	public String solution(String play_time, String adv_time, String[] reqLogs) {
		PlayTime totalPlayTime = convertPlayTime("00:00:00", play_time);
		Adv adv = new Adv(convertTime(adv_time));
		Comparator<PlayTime> playTimeComparator = (o1, o2) -> {
			int n = o1.start - o2.start;
			if (n == 0) return o1.end - o2.end;
			else return n;
		};

		List<PlayTime> logs = initLogs(reqLogs, playTimeComparator);

		for (int curPoint = totalPlayTime.start; curPoint <= totalPlayTime.end; curPoint++) {
			//TODO [O] logs가 없으면 종료
			if (logs.isEmpty()) break;

			//TODO [O] 광고 시작 시간부터 끝나는 시간 구하기 -> if end가 최대 길이보다 큰 경우 break;
			PlayTime advPlayTime = adv.getPlayTime(curPoint);
			if (advPlayTime.end > totalPlayTime.end) break;

			//TODO [o] 몇개 포함하는지 확인한다. (사용자 start가 광고 end보다 크면 종료, 사용자 end가 광고 s보다 작으면 pass)
			List<PlayTime> removedLogs = new ArrayList<>();
			int maxPlayTime = 0;
			for (PlayTime log : logs) {
				if (log.start > advPlayTime.end) break;
				if (log.end < advPlayTime.start) {
					removedLogs.add(log);
					continue;
				}
				//TODO [o] 현 시점에 모든 재생시간 구하기
				maxPlayTime += log.getPlayTime(advPlayTime);
			}

			if (maxPlayTime == 0) continue;
			adv.update(maxPlayTime, advPlayTime.start); //TODO [o] 포함된 개수와 광고 시작 시간을 넘겨, max보다 크면 max와 광고 시작 시간을 갱신한다.

			logs.removeAll(removedLogs);
		}
		return toStringTime(adv.possibleStartPlayTime); //TODO [o] 다시 시간을 변경한다.
	}

	private String toStringTime(int possibleStartPlayTime) {
		int hour = possibleStartPlayTime / 3600;
		int minute = (possibleStartPlayTime % 3600) / 60;
		int second = (possibleStartPlayTime % 3600) % 60;
		LocalDateTime localDateTime = LocalDateTime.of(2020, 1, 5, hour, minute, second);
		LocalTime localTime = localDateTime.toLocalTime();
		return localTime.format(DateTimeFormatter.ISO_LOCAL_TIME);
	}

	private List<PlayTime> initLogs(String[] reqLogs, Comparator<PlayTime> playTimeComparator) {
		List<PlayTime> result = new ArrayList<>();
		for (String reqLog : reqLogs) {
			String[] spl = reqLog.split("-");
			PlayTime playTime = convertPlayTime(spl[0], spl[1]);
			result.add(playTime);
		}
		result.sort(playTimeComparator);
		return result;
	}

	private PlayTime convertPlayTime(String start, String end) {
		int startNumber = convertTime(start);
		int endNumber = convertTime(end);
		return new PlayTime(startNumber, endNumber);
	}

	private int convertTime(String time) {
		String[] spl = time.split(":");
		int hour = Integer.parseInt(spl[0]) * 3600;
		int minute = Integer.parseInt(spl[1]) * 60;
		int second = Integer.parseInt(spl[2]);
		return hour + minute + second;
	}

	static class Adv {
		int totalPlayTime;
		int maxIncludedPlayCnt;
		int possibleStartPlayTime;

		public Adv(int totalPlayTime) {
			this.totalPlayTime = totalPlayTime;
			this.maxIncludedPlayCnt = 0;
			this.possibleStartPlayTime = 0;
		}

		public PlayTime getPlayTime(int start) {
			return new PlayTime(start, start + totalPlayTime);
		}

		public void update(int cnt, int start) {
			if (maxIncludedPlayCnt < cnt) {
				possibleStartPlayTime = start;
				maxIncludedPlayCnt = cnt;
			}
		}
	}

	static class PlayTime {
		int start;
		int end;

		public PlayTime(int start, int end) {
			this.start = start;
			this.end = end;
		}

		/**
		 * 광고.end 작거나 같은 경우 -> end = 광고.end;
		 * 광거.end가 큰 경우 -> end = this.end;
		 *
		 * 광고.start가 크거나 같은 경우 -> start = 광고.start;
		 * 광고.start가 작은 경우 -> start = this.start;
		 */
		public int getPlayTime(PlayTime playTime) {
			int start = this.start;
			int end = this.end;
			if (playTime.start >= start) start = playTime.start;
			if (playTime.end <= end) end = playTime.end;
			return end - start;
		}
	}
}
