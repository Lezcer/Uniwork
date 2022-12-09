filename = "input.txt"

lines = open(filename,'r').read().splitlines()
# Read the whole file by lines

sum = 0 

for line in lines:
    i = int(len(line) / 2)
    compartment1 = line[:i]
    compartment2 = line[i:i*2]
    # Half the string into 2 equal compartments

    for letter in compartment1:
        for j in range(len(compartment2)):
            if(letter == compartment2[j]):
                character = letter
    # Find the character that occurs in both

    decimal = ord(character)
    if(decimal >= 97 and decimal <= 122):
        priority = decimal - 96
        # Is lower case
    elif(decimal >= 65 and decimal <= 90):
        priority = decimal - 38
        # Is upper case
    # Find the priority of the character

    sum += priority

print("The sum of the priorities of those item types is:", sum)