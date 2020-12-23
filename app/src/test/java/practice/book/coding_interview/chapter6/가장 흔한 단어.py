"""
금지된 단어를 제외한 가장 흔하게 등장하는 단어를 출력.
1. 대소문자 구분 x
2. 구두점(마췸표, 쉼표 등) 또한 무시

[input]
paragraph = "Bob hit a ball, the hit BALL flew far after it was hit."
banned = ["hit"]

[output]
"ball"

[구현]
1. 소문자로 변경
2. 영문, 공백만 허용
3. banned에 포함된 단어 제거
4. 빈도수 높은 것 출력
"""

import re
import collections


class Solution:

    def one(self, paragraph: str, banned: list) -> str:
        letters = ''.join(re.compile("[a-zA-Z\s]").findall(paragraph)).lower()
        filtered_letters = list(filter(lambda x: (x not in banned), letters.split()))
        return collections.Counter(filtered_letters).most_common(1)[0][0]


s = Solution()
result = s.one("Bob hit a ball, the hit BALL flew far after it was hit.", ["hit"])
print(result)