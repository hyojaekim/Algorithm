resultCount = int(input())
result = []

for x in range(resultCount):
    inputData = input()
    splitData = inputData.split(' ')

    count = int(splitData[0])
    data = splitData[1]

    resultString = ""
    for i in range(len(data)):
        temp = data[i]
        for j in range(count):
            resultString += temp
    
    result.append(resultString)

for temp in result:
    print(temp)