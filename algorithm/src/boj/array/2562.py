import sys

max, maxIndex = int(sys.stdin.readline()), 0

for index in range(1, 9):
    number = int(sys.stdin.readline())

    if (max < number):
        max = number
        maxIndex = index

print(max)
print(maxIndex+1)