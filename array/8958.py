import sys

count = int(sys.stdin.readline())

for i in range(count):
    state = sys.stdin.readline()
    score = []
    totalScore = 0

    for index in range(len(state)):
        score.append(0)
        if state[index] == "O":
            score[index] += 1
            if index != 0:
                score[index] += score[index - 1]
        totalScore += score[index]

    print(totalScore)