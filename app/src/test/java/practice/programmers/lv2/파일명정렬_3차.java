package practice.programmers.lv2;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class 파일명정렬_3차 {

	@Test
	void test() {
		String[] solution = solution(new String[]{"img12", "img10.png", "img02.png", "img1.png", "IMG01.GIF", "img2.JPG"});
		System.out.println(Arrays.toString(solution));
	}

	/**
	 * [문제] https://programmers.co.kr/learn/courses/30/lessons/17686
	 * [분류] 구현
	 * [조건] head, number, index 순으로 정렬하라.
	 *
	 * @param files 파일명
	 * @return 파일명을 정렬하여 반환하라.
	 */
	public String[] solution(String[] files) {
		String[] result = new String[files.length];
		List<File> convertedFiles = init(files);
		convertedFiles.sort(Comparator.comparing((File o) -> o.head.toLowerCase()).thenComparingInt(o -> Integer.parseInt(o.number)).thenComparingInt(o -> o.index));
		for (int i = 0; i < result.length; i++) {
			result[i] = convertedFiles.get(i).toString();

		}
		return result;
	}

	private List<File> init(String[] files) {
		List<File> result = new ArrayList<>();
		for (int i = 0; i < files.length; i++) {
			result.add(new File(files[i], i));
		}
		return result;
	}

	class File {
		String head;
		String number;
		String tail;
		int index;

		public File(String file, int index) {
			int numberStartIndex = getNumberStartIndex(file);
			int numberEndIndex = getNumberEndIndex(file, numberStartIndex);

			this.head = file.substring(0, numberStartIndex);
			this.number = file.substring(numberStartIndex, numberEndIndex);
			this.tail = numberEndIndex == file.length() ? "" : file.substring(numberEndIndex);
			this.index = index;
		}

		private int getNumberStartIndex(String file) {
			for (int i = 0; i < file.length(); i++) {
				char c = file.charAt(i);
				if (c >= '0' && c <= '9') return i;
			}
			return 0;
		}

		private int getNumberEndIndex(String file, int start) {
			for (int i = start; i < file.length(); i++) {
				char c = file.charAt(i);
				if (!(c >= '0' && c <= '9')) return i;
			}
			return file.length();
		}

		@Override
		public String toString() {
			return this.head + this.number + this.tail;
		}
	}
}
