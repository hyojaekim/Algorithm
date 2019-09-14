import sys

count = sys.stdin.readline()
inputNumber = list(map(int, sys.stdin.readline().split()))

max, min = inputNumber[0], inputNumber[0]

for number in inputNumber:
    if (max < number):
        max = number
    if (min > number):
        min = number

print(min, max)