package study;

public class 괄호_변환 {

    public String solution(String p) {
        if (isCollect(p)) return p;
        return convert(p);
    }

    private boolean isCollect(String p) {
        while (p.contains("()")) {
            p = p.replace("()", "");
        }
        return p.length() == 0;
    }

    private String convert(String p) {
        if (p.length() == 0) return "";
        String u = getU(p);
        String v = getV(p, u);
        if (isCollect(u)) {
            return u + convert(v);
        } else {
            String result = "(" + convert(v) + ")";
            char[] temp = u.substring(1, u.length() - 1).toCharArray();
            for (int i = 0; i < temp.length; i++) {
                if (temp[i] == '(') temp[i] = ')';
                else temp[i] = '(';
            }
            return result + new String(temp);
        }
    }

    private String getU(String p) {
        char a = p.charAt(0);
        int cntA = 1;
        int cntB = 0;
        for (int i = 1; i < p.length(); i++) {
            if (p.charAt(i) == a) cntA++;
            else cntB++;
            if (cntA == cntB) {
                return p.substring(0, i + 1);
            }
        }
        return "";
    }

    private String getV(String p, String u) {
        if (u.length() >= p.length()) return "";
        return p.substring(u.length());
    }
}
