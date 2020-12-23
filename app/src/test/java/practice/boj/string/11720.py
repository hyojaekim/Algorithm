count = int(input())
inputNumber = str(input())

result = 0
for i in range(count):
    result += int(inputNumber[i])

print(result)