"""
주어진 문자열이 팰린드롬인지 확인하라.
1. 대소문자 구분하지 않는다.
2. 영문자와 숫자만을 대상으로 한다.
팰린드롬 - 앞뒤가 똑같은 단어나 문장을 말하며 즉, 뒤집어도 같은 말인 것을 의미한다.

"A man, a plan, a canal: Panama"
true

=> 슬라이싱을 사용하면 더 빠르다.
"""
import copy
import re


class Solution:
    def one(self, content: str) -> bool:
        # 알파벳 또는 숫자를 필터링하고 배열로 만들고, 배열을 뒤집은 것도 생성
        content = list(filter(str.isalnum, list(content.lower())))
        reversed_content = copy.deepcopy(content)
        reversed_content.reverse()

        # 팰린드롬 여부 판별
        for i in range(len(content)):
            if content[i] != reversed_content[i]:
                return False

        return True

    # 데크를 이용한 최적화
    def two(self, content: str) -> bool:
        strs = []
        for char in content:
            if char.isalnum():
                strs.append(char.lower())

        # 팰린드롬 여부 판별
        while len(strs) > 1:
            # 리스트 맨 앞과 맨 뒤를 꺼내면서 확인
            if strs.pop(0) != strs.pop():
                return False

        return True

    # 슬라이싱 이용
    def three(self, content: str) -> bool:
        content = content.lower()
        # 정규식으로 불필요한 문자 필터링
        content = re.sub('[^a-z0-9]', '', content)
        return content == content[::-1]
