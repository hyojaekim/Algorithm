a = int(input())
b = input()

splitNumber = list(map(int, list(b)))
result = 0
ten = 1

for number in reversed(splitNumber):
    number = a * number
    print(number)
    result = result + (number * ten)
    ten = ten * 10

print(result)



