stacks = [['M','J','C','B','F','R','L','H'],
          ['Z','C','D'],
          ['H','J','F','C','N','G','W'],
          ['P','J','D','M','T','S','B'],
          ['N','C','D','R','J'],
          ['W','L','D','Q','P','J','G','Z'],
          ['P','Z','T','F','R','H'],
          ['L','V','M','G'],
          ['C','B','G','P','F','Q','R','J']]

filename = "input"

lines = open(filename,'r').read().splitlines()
# Read the whole file by lines

for line in lines:
    indexes = line.split()
    count = int(indexes[1])
    fromI = int(indexes[3]) - 1 
    toI = int(indexes[5]) - 1

    
    for i in range(count):
        stacks[toI].append(stacks[fromI].pop())

crates = []

for stack in stacks:
    if(len(stack) != 0):
        crates.append(stack.pop())

print("The crate that ends up on top of each stack is:",''.join(crates))