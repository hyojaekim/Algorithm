import sys

while True:
    try :
        first, second = map(int, sys.stdin.readline().split())
        print(first + second)
    except :
        break