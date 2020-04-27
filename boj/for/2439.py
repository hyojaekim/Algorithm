import sys

heightNumber = int(sys.stdin.readline())

for height in range(heightNumber):
    for space in range(heightNumber-height, 1, -1):
        print(' ', end = '')
    for width in range(0, height + 1):
        print('*', end = '')
    print()