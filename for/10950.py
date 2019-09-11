count = int(input())
result = []

for i in range(count):
    a, b = input().split()
    a = int(a)
    b = int(b)
    result.append(a + b)

for number in result:
    print(number)