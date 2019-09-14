import sys

value = list(map(int, sys.stdin.readline().split()))
sortValue = value[0] - value[1]
isMixed = False

for index in range(0, (len(value)-1)):
    subNumber = value[index] - value[index+1]

    if ((subNumber != -1 & subNumber != 1) | subNumber != sortValue):
        isMixed = True
        print("mixed")
        break

if (isMixed == False):
    if (sortValue == 1):
        print("descending")
    if (sortValue == -1):
        print("ascending")