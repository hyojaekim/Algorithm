import sys

remainders = []

for index in range(10):
    remainder = int(sys.stdin.readline()) % 42
    isInclude = False

    for remainderNumber in remainders:
        if(remainder == remainderNumber):
            isInclude = True
            break
    
    if(isInclude == False):
        remainders.append(remainder)

print(len(remainders))