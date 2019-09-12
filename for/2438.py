import sys

heightNumber = int(sys.stdin.readline())

for height in range(heightNumber):
    print("*", end='')
    for width in range(0, height):
        print("*", end='')
    print()