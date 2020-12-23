"""
문자열을 뒤집는 함수를 작성하라.
입력값은 문자 배열
뒤집은 배열을 출력하면 된다.

리턴 없이 리스트 내부를 직접 조작해야 한다. 즉, 공간 복잡도는 O(1)
"""


class Solution:
    # 투 포인터
    def one(self, strs: list):
        left, right = 0, len(strs) - 1
        while left < right:
            strs[left], strs[right] = strs[right], strs[left]
            left += 1
            right -= 1

        print(strs)

    def two(self, strs: list):
        strs.reverse()
        print(strs)

    def three(self, strs: list):
        strs[:] = strs[::-1]
        print(strs)
