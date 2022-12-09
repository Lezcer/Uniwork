#!/usr/bin/python3
filename = "input.txt"

lines = open(filename,'r').read().splitlines()
# Read the whole file by lines

sum = 0

for current in range(0, len(lines), 3):
    subset = lines[current:current+3]
    # Get the the three Elves' items
    
    characters = []
    # This list should reset for every iteration

    for letter in subset[0]:
        for j in range(len(subset[1])):
            if(letter == subset[1][j]):
                characters.append(letter)
    # Find the letters that occur both in the first two rucksacks

    characters = list(dict.fromkeys(characters))
    # Get rid of duplicates 
    
    for chr in characters:
        for k in range(len(subset[2])):
            if(chr == subset[2][k]):
                character = chr
    # Find the character that occurs in all three rucksacks

    print(character)
    decimal = ord(character)
    if(decimal >= 97 and decimal <= 122):
        priority = decimal - 96
        # Is lower case
    elif(decimal >= 65 and decimal <= 90):
        priority = decimal - 38
        # Is upper case
    # Find the priority of the character
    
    sum += priority

print("The sum of the priorities of those item types in the three-Elf group is:", sum)
