a = int(input())
b = input()

splitNumber = list(map(int, list(b)))
ten = 1
result = 0

for number in reversed(splitNumber):
    number = a * number
    print(number)
    result = result + (number * ten)
    ten = ten * 10

print(result)



