import sys

testCaseCount = int(sys.stdin.readline())

for i in range(testCaseCount):
    numbers = list(map(int, sys.stdin.readline().split()))
    count = numbers[0]
    scoreAverage = 0

    for index in range(1, len(numbers)):
        scoreAverage += numbers[index]

    scoreAverage = scoreAverage / count
    result = 0
    for index in range(1, len(numbers)):
        if scoreAverage < numbers[index]:
              result += 1

    print(str('%.3f' % round(result / count * 100, 3)) + "%")