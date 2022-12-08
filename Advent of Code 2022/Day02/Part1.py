def mapChoices(choices):
    mappings = {'A':'R',
                'X':'R',
                'B':'P',
                'Y':'P',
                'C':'S',
                'Z':'S'}

    temp = ""
    for i in range(len(choices)):
        temp += mappings[choices[i]] 
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