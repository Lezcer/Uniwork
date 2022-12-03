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

dictionary = {}

total = 0

for i in range(3):
    max_cal = max(calories)

    index = calories.index(max_cal)
    # Index containing the largest number

    calories.pop(index)

    dictionary.update({index:max_cal})

    total += max_cal
    

print("The total calories carried by the top three Elves is: " +  str(total) + " calories")