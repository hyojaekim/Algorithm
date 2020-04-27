inputNumber = int(input())
count = 0

for i in range(1, inputNumber+1):
    totalNumber = str(i)
    result = True
    temp = 0;
    for index in range(1, len(totalNumber)):
        subNumber = int(totalNumber[index]) - int(totalNumber[index-1])
        if index == 1:
            temp = subNumber
            continue
        
        if temp != subNumber:
            result = False
            break
    
    if result == True:
        count += 1

print(count)