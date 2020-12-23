import sys

count, maxNumber = map(int, sys.stdin.readline().split())

numbers = map(int, sys.stdin.readline().split())
result = []

for number in numbers:
    if number < maxNumber:
        result.append(number)

for number in result:
    print(number, end = ' ')