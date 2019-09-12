import sys

count = int(sys.stdin.readline())
result = []

for i in range(count):
    a, b = map(int, sys.stdin.readline().split())
    temp = str(str(a) + " + " + str(b) + " = ")
    result.append(temp + str(a + b))

for index in range(0, len(result)):
    print("Case #" + str(index+1) + ": " + result[index])