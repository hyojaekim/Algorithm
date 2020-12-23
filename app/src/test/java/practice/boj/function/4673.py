result = [False] * 10001

def d(number):
    sumNumber = number + sum(int(i) for i in str(number))
    if sumNumber <= 10000:
        result[sumNumber] = True
    return result[number]

for number in range(1, 10001):
    if d(number) == False:
        print(number)