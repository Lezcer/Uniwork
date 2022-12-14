filename = "input"

file = open(filename,'r').read()
# Read the whole file

for index in range(4, len(file)+1):
    current = file[index-4:index]
    if(len(set(current)) == len(current)):
        point = index
        print(current)
        break
    # The length of the set and string will be the same only if there are no duplicate strings

print("The number of characters that need to be processed before the first start-of-packet marker is detected", point)
