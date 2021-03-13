package practice.boj;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class BOJ17219 {

	@Test
	void test() {
		solution(16, 4, new String[]{
						"noj.am IU",
						"acmicpc.net UAENA",
						"startlink.io THEKINGOD",
						"google.com ZEZE",
						"nate.com VOICEMAIL",
						"naver.com REDQUEEN",
						"daum.net MODERNTIMES",
						"utube.com BLACKOUT",
						"zum.com LASTFANTASY",
						"dreamwiz.com RAINDROP",
						"hanyang.ac.kr SOMEDAY",
						"dhlottery.co.kr BOO",
						"duksoo.hs.kr HAVANA",
						"hanyang-u.ms.kr OBLIVIATE",
						"yd.es.kr LOVEATTACK",
						"mcc.hanyang.ac.kr ADREAMER",
						"startlink.io",
						"acmicpc.net",
						"noj.am",
						"mcc.hanyang.ac.kr"
		});
	}

	public void solution(int n, int m, String[] sites) {
		Map<String, String> siteInfo = new HashMap<>();
		for (int i = 0; i < n; i++) {
			String[] split = sites[i].split(" ");
			siteInfo.put(split[0], split[1]);
		}

		for (int i = n; i < n + m; i++) {
			System.out.println(siteInfo.get(sites[i]));
		}
	}
}
