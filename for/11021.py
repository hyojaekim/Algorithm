import sys

count = int(sys.stdin.readline())
result = []

for i in range(count):
    a, b = map(int, sys.stdin.readline().split())
    result.append(a + b)

for index in range(0, len(result)):
    print("Case #" + str(index + 1) + ": " + str(result[index]))