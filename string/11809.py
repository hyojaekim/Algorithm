min = int(ord('a'))
max = int(ord('z'))

result = [-1] * ((max + 1) - min)

inputData = str(input())

for index in range(0, len(inputData)):
    data = inputData[index]
    dataIndex = int(ord(data)) - min

    if result[dataIndex] == -1:
        result[dataIndex] = index

for data in result:
    print(data, end=' ')

