import sys

one = int(sys.stdin.readline())
two = int(sys.stdin.readline())
three = int(sys.stdin.readline())

mulNumber = str(one * two * three)

for number in range(0, 10):
    count = 0
    
    for index in range(len(mulNumber)):
        if (number == int(mulNumber[index])):
            count += 1

    print(count)