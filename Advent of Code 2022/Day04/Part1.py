filename = "input"

lines = open(filename,'r').read().splitlines()
# Read the whole file by lines

count = 0

for line in lines:
    # line = "2-4,6-8"
    first = line.split(",")[0]
    second = line.split(",")[1]
    # Split the assignment pairs
    # first, second = "2-4", "6-8"

    fUpper = int(first.split("-")[0])
    fLower = int(first.split("-")[1])
    sUpper = int(second.split("-")[0])
    sLower = int(second.split("-")[1])
    # Get the lower and upper range for each pair
    # fUpper, fLower, sUpper, sLower = 2, 4, 6, 8

    if((fUpper <= sUpper and fLower >= sLower) or (fUpper >= sUpper and fLower <= sLower)):
        count += 1

print("The total assignment pairs in which one range fully contain the other is:", count)

