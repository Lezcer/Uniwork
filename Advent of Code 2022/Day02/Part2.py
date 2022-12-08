def mapChoices(choices):
    mappings = {'A':'R',
                'B':'P',
                'C':'S',}
    
    temp = mappings[choices[0]] 

    values = ['R','P','S']

    if(choices[1] == 'X'):
        #you need to lose
        temp += values[(values.index(temp)-1)%3]
    elif(choices[1] == 'Y'):
        #you need to win
        temp += temp
    elif(choices[1] == 'Z'):
        #you need to win
        temp += values[(values.index(temp)+1)%3]

    return temp
    # Map the characters to the choice

'''
R - R draw
P - P draw
S - S draw

R - P right
P - R left

P - S right
S - P left

R - S left
S - R right
'''

'''
X - Lose
Y - Draw
Z - Win
'''

def outcome(choices):
    choices = mapChoices(choices)

    outcome = {"RR":"draw",
               "PP":"draw",
               "SS":"draw",
               "RP":"right",
               "PR":"left",
               "PS":"right",
               "SP":"left",
               "RS":"left",
               "SR":"right"}
    
    result = outcome[choices]

    points = {'R':1, 
              'P':2, 
              'S':3}

    if(result == "draw"):
        right = 3 + points[choices[1]]
        left  = 3 + points[choices[0]]
    elif(result == "right"):
        right = 6 + points[choices[1]]
        left  = 0 + points[choices[0]]
    elif(result == "left"):
        right = 0 + points[choices[1]]
        left  = 6 + points[choices[0]]
    
    return right, left

filename = "input"

lines = open(filename,'r').read().splitlines()
# Read the whole file by lines

totalR, totalL = 0, 0

for line in lines:
    line = line.split(" ")[0] + line.split(" ")[1]

    right, left = outcome(line)

    totalR += right
    totalL += left

print("The total score would be",totalR)