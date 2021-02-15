one, two, three = input().split()

one = int(one)
two = int(two)
three = int(three)

if one > two:
    one, two = two, one
if one > three:
    one, three = three, one
if two > three:
    two, three = three, two

print(two)