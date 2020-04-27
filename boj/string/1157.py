data = str(input())
result = ''
count = 0

while len(data) != 0:
    tempData = data[0]
    lowerData = tempData.lower()
    upperData = tempData.upper()
    tempCount = data.count(lowerData) + data.count(upperData)

    if tempCount > count:
        count = tempCount
        result = tempData
    elif tempCount == count:
        result += tempData

    data = data.replace(lowerData, '').replace(upperData, '')

if len(result) > 1:
    print('?')
else:
    print(result.upper())