filename = "input"

f = open(filename, 'r')
lines = f.readlines()
# Read the whole file

calories = [0]
# Array stores total calories from each Elf

for line in lines:
    if(line[0] != '\n'):
    # The first char of the line isn't a new line char
    # i.e. it contains a number
        element = len(calories) - 1 
        calories[element] = calories[element] + int(line)
    else:
    # Is a new line character indicating what another Elf is carrying
        calories.append(0)

max_calories = max(calories)
# Find the largest number in the array

index = calories.index(max_calories)
# Index containing the largest number

print("The Elf carrying the most total calories is: " + str(index+1) + " with " + str(calories[index]) + " calories")