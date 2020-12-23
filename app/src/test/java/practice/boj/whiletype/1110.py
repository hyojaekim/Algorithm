import sys

inputNumber = int(sys.stdin.readline())
compareNumber = str(inputNumber)
count = 0

def numberSizeControl(number):
    if int(number) < 10:
        number = str(number).zfill(2)
    return str(number)

while True:
    count += 1

    compareNumber = numberSizeControl(compareNumber)
    sumNumber = numberSizeControl(int(compareNumber[0]) + int(compareNumber[1]))
    compareNumber = int(compareNumber[1] + sumNumber[1])

    if compareNumber == inputNumber:
        print(count)
        break