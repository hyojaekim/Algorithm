package programmers.kakao_2019;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class 매칭점수 {
    Map<String, Page> pageMap;
    public int solution(String word, String[] pages) {
        pageMap = new HashMap<>();
        word = word.toLowerCase();
        int index = 0;
        for (String page : pages) {
            String url = page.split("</head>")[0].split("content=\"https://")[1].replace("\"/>", "").replace("\n", "");
            String body = page.split("<body>")[1].split("</body>")[0];
            String[] split = body.split("\n");
            List<String> outsideLink = new ArrayList<>();
            int point = 0;
            for (String content : split) {
                if (content.isEmpty()) continue;
                if (content.contains("<a href")) {
                    String[] tempSplit = content.split("</a>");
                    String[] tempSplit2 = tempSplit[0].split("https://")[1].replace(" ", "").split("\">");
                    outsideLink.add(tempSplit2[0]);
                    if (tempSplit2.length > 1) {
                        content = tempSplit[1];
                    }
                    if (tempSplit.length > 1) {
                        content += tempSplit[1];
                    }
                }
                content = content.toLowerCase();
                int fromIndex = -1;
                while ((fromIndex = content.indexOf(word, fromIndex + 1)) >= 0) {
                    point++;
                }
            }
            Page p = new Page(url, point, outsideLink, index++);
            pageMap.put(url, p);
        }

        for (Page page : pageMap.values()) {
            page.setMatchPoint();
        }
        List<Page> resultPages = new ArrayList<>(pageMap.values());
        resultPages.sort((o1, o2) -> {
            double result = o2.matchPoint - o1.matchPoint;
            if (result < 0) {
                return -1;
            } else if (result == 0) {
                return o1.idx - o2.idx;
            } else {
                return 1;
            }
        });
        return resultPages.get(0).idx;
    }

    class Page {
        String url;
        int point;
        List<String> outsideLink;
        double linkPoint;
        double matchPoint;
        int idx;

        public Page(String url, int point, List<String> outsideLink, int idx) {
            this.url = url;
            this.point = point;
            this.outsideLink = outsideLink;
            this.linkPoint = (double) point / outsideLink.size();
            this.matchPoint = 0;
            this.idx = idx;
        }

        public void setMatchPoint() {
            double matchPoint = 0;
            for (Page page : pageMap.values()) {
                if (isSame(page)) continue;
                if (page.isContains(this.url)) {
                    matchPoint += page.linkPoint;
                }
            }
            this.matchPoint = this.point + matchPoint;
        }

        public boolean isSame(Page page) {
            return this.url.equals(page.url);
        }

        public boolean isContains(String requestUrl) {
            for (String url : outsideLink) {
                if (url.equals(requestUrl)) {
                    return true;
                }
            }
            return false;
        }
    }
}
