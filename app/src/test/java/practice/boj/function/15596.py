a = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10]

def solve(numbers: list):
    result = 0
    for number in numbers:
        result += number
    return result

print (solve(a))