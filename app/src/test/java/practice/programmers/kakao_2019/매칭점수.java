package practice.programmers.kakao_2019;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 매칭점수 {

	@Test
	void test() {
		int result = solution("blind", new String[]{"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://a.com\"/>\n</head>  \n<body>\nBlind Lorem Blind ipsum dolor Blind test sit amet, consectetur adipiscing elit. \n<a href=\"https://b.com\"> Link to b </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://b.com\"/>\n</head>  \n<body>\nSuspendisse potenti. Vivamus venenatis tellus non turpis bibendum, \n<a href=\"https://a.com\"> Link to a </a>\nblind sed congue urna varius. Suspendisse feugiat nisl ligula, quis malesuada felis hendrerit ut.\n<a href=\"https://c.com\"> Link to c </a>\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://c.com\"/>\n</head>  \n<body>\nUt condimentum urna at felis sodales rutrum. Sed dapibus cursus diam, non interdum nulla tempor nec. Phasellus rutrum enim at orci consectetu blind\n<a href=\"https://a.com\"> Link to a </a>\n</body>\n</html>"});
		Assertions.assertEquals(result, 0);
		int result2 = solution("Muzi", new String[]{"<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://careers.kakao.com/interview/list\"/>\n</head>  \n<body>\n<a href=\"https://programmers.co.kr/learn/courses/4673\"></a>#!MuziMuzi!)jayg07con&&\n\n</body>\n</html>", "<html lang=\"ko\" xml:lang=\"ko\" xmlns=\"http://www.w3.org/1999/xhtml\">\n<head>\n  <meta charset=\"utf-8\">\n  <meta property=\"og:url\" content=\"https://www.kakaocorp.com\"/>\n</head>  \n<body>\ncon%\tmuzI92apeach&2<a href=\"https://hashcode.co.kr/tos\"></a>\n\n\t^\n</body>\n</html>"});
		Assertions.assertEquals(result2, 1);
	}

	Map<Integer, List<Integer>> linkInfo;

	/**
	 * [문제] https://programmers.co.kr/learn/courses/30/lessons/42893
	 * [분류] 구현
	 * [주의점]
	 * 1. 점수 계산 똑바로 하기
	 * 2. 정렬 똑바로 하기 -> max가 같으면 인덱스가 가장 낮은거 반환
	 * 3. meta 정보 정확하게 가져오기
	 *
	 * @param word     단어
	 * @param reqPages 페이지 정보
	 * @return 매칭 점수가 가장 높고, 가장 낮은 인덱스 반환
	 */
	public int solution(String word, String[] reqPages) {
		word = word.toLowerCase();
		List<Page> pages = init(word, reqPages);

		//링크점수 & 매칭점수 구하기
		//링크 건 페이지 -> 기본점수 / 외부 링크수
		for (Page page : pages) {
			if (!linkInfo.containsKey(page.index)) continue;
			for (int idx : linkInfo.get(page.index)) {
				Page difPage = pages.get(idx);
				page.linkPoint += ((double) difPage.basicPoint / (double) difPage.linkCount);
			}
			page.calMatchingPoint();
		}

		double max = -1;
		int idx = 0;
		for (Page page : pages) {
			if (max < page.matchingPoint) {
				max = page.matchingPoint;
				idx = page.index;
			}
		}
		return idx;
	}

	private List<Page> init(String word, String[] pages) {
		List<Page> result = new ArrayList<>();
		this.linkInfo = new HashMap<>();
		Map<String, Integer> idxInfo = new HashMap<>();
		for (int i = 0; i < pages.length; i++) {
			String page = pages[i];
			String url = getUrl(page);
			String body = getBody(page);
			List<String> externalUrl = getExternalUrl(body);
			String contents = getContents(body, externalUrl);
			int basicPoint = getBasicPoint(contents, word);

			idxInfo.put(url, i);
			result.add(new Page(url, externalUrl, i, basicPoint));
		}

		for (Page page : result) {
			for (String url : page.externalUrl) {
				this.linkInfo.computeIfAbsent(idxInfo.get(url), k -> new ArrayList<>()).add(page.index);
			}
		}
		return result;
	}

	private String getUrl(String page) {
		String[] split = page.split("<head>")[1]
						.split("</head>")[0]
						.split("<meta");
		for (String content : split) {
			if (!content.startsWith(" property=\"og:url\" content=\"")) continue;
			return content.split(" property=\"og:url\" content=\"")[1].split("\"")[0];
		}
		return "";
	}

	private String getBody(String page) {
		return page.split("<body>")[1]
						.split("</body>")[0];
	}

	private List<String> getExternalUrl(String body) {
		List<String> result = new ArrayList<>();
		for (String url : body.split("<a href=\"")) {
			if (!url.startsWith("https://")) continue;
			result.add(url.split("\">")[0]);
		}
		return result;
	}

	private String getContents(String body, List<String> externalUrl) {
		body = body.replaceAll("</a>", "");
		String temp;
		for (String url : externalUrl) {
			temp = "<a href=\"" + url + "\">";
			body = body.replaceFirst(temp, "");
		}
		return body;
	}

	private int getBasicPoint(String contents, String word) {
		int result = 0;
		contents = contents.toLowerCase();
		String[] split = contents.split("[^a-zA-Z]");
		for (String s : split) {
			if (word.equals(s)) result++;
		}
		return result;
	}

	static class Page {

		String url;
		List<String> externalUrl;
		int index;
		int basicPoint;
		int linkCount;
		double linkPoint;
		double matchingPoint;

		public Page(String url, List<String> externalUrl, int index, int basicPoint) {
			this.url = url;
			this.externalUrl = externalUrl;
			this.index = index;
			this.basicPoint = basicPoint;
			this.linkCount = externalUrl.size();
			this.linkPoint = 0;
			this.matchingPoint = basicPoint;
		}

		public void calMatchingPoint() {
			this.matchingPoint += this.linkPoint;
		}
	}
}
