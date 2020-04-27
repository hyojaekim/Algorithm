import sys

count = int(sys.stdin.readline())
score = list(map(int, sys.stdin.readline().split()))
maxScore = score[0]
totalScore = 0

for number in score:
    if(maxScore < number):
        maxScore = number

for index in range(len(score)):
    score[index] = score[index] / maxScore * 100
    totalScore += score[index]

print(round(totalScore / count, 2))