data = str(input())
repo = ['A', 'D', 'G', 'J', 'M', 'P', 'T', 'W']
lastIndex = 7
result = 0

for index in range(len(data)):
    status = 0
    inputData = data[index]
    for i in range(0, len(repo)):
        if i == lastIndex:
            status = i + 3
            break
        elif inputData == repo[i]:
            status = i + 3
            break
        elif inputData < repo[i+1]:
            status = i + 3
            break
    result = result + status

print(result)