hour, minute = input().split()

hour = int(hour)
minute = int(minute)

minute = minute - 45

if (minute < 0):
    minute = 60 + minute
    hour = hour - 1

if (hour < 0):
    hour = 24 + hour

print(hour, minute)