result = []

while True:
    firstNumber, secondNumber = map(int, input().split())
    if (firstNumber == 0 & secondNumber == 0):
        break
    
    result.append(firstNumber + secondNumber)

for number in result:
    print(number)