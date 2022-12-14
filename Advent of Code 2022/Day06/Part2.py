filename = "input"

file = open(filename,'r').read()

count = 0

for index in range(14, len(file)+1):
    current = file[index-14:index]
    # difference between Part1 and and Part2 is the 14s where 4
    if(len(set(current)) == len(current)):
        point = index
        print(current)
        break

print("The number of characters that need to be processed before the first start-of-packet marker is detected", point)
