"""
로그를 재정렬하라. 기준은 아래와 같다.
1. 로그의 가장 앞 부분은 식별자
2. 문자로 구성된 로그가 숫자 로그보다 앞에 온다.
3. 식별자는 순서에 영향을 끼치지 않지만, 문자가 동일한 경우 식별자 순으로 한다.
4. 숫자 로그는 입력 순서대로 한다.

[input]
logs = ["dig1 8 1 5 1", "lef1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero"]

[output]
["let1 art can", "let3 art zero", let2 own kit dig", "dig1 8 1 5 1", "dig2 3 6"]

[구현]
1. 숫자인지 문자인지 판별
문자: 식별자 순
숫자: 인덱스 순
"""


class Solution:

    def one(self, logs: list) -> list:
        digits, letters = [], []
        for log in logs:
            if log.split()[1].isdigit():
                digits.append(log)
            else:
                letters.append(log)

        letters.sort(key=lambda x: (x.split()[1:], x.split()[0]))
        return letters + digits


s = Solution()
result = s.one(["dig1 8 1 5 1", "lef1 art can", "dig2 3 6", "let2 own kit dig", "let3 art zero"])
print(result)
