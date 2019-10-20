data = str(input()).split(' ')
result = len(data)

for i in range(result):
    if data[i] == '':
        result -= 1

print(result)