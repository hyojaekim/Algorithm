data = str(input()).split(' ')

first = int((data[0])[::-1])
second = int((data[1])[::-1])

result = first > second
if result:
    print(first)
else:
    print(second)


