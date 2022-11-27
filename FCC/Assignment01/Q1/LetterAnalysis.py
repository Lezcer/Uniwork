#!/usr/bin/python
# @author Salah Mahamod

from curses.ascii import LF
import sys

f = open(sys.argv[1], 'r')
# Taking filename as an argument
ch = f.read(1)
# Reading given file

newFile = "LA_DECRYPTED_" + sys.argv[1]
# Decrypted text file created
ff = open(newFile, 'w')

# Writing to the Decrypted text file

total = 0
tt = 0

lFreq = [0] * 26
# Create an array of size 26 with each element as 0

refTable = [12.6, 9.37, 8.34, 7.7, 6.8, 6.71, 6.14, 6.13, 5.68, 4.24, 4.14, 2.85, 2.73, 2.53, 2.34, 2.04, 2.03, 1.92, 1.66, 1.54, 1.06, 0.87, 0.23, 0.20, 0.09, 0.06]
descLetters = ['E', 'T',  'A', 'O', 'N' , 'I' , 'S',  'H',  'R',  'L',  'D',  'U', 'C',  'M',  'W',   'Y' , 'F' , 'G' , 'P' , 'B' , 'V' , 'K' , 'J' , 'X' , 'Q' , 'Z']
#           E     T     A     0    N    I     S     H     R     L     D     U     C     M     W     Y     F     G     P     B     V     K     J     X     Q     Z
# Table used for substitution and comparison
# Trost Stefan. 2017. "Alphabet and Character Frequency: English." Stefan Trost Media. https://www.sttmedia.com/characterfrequency-english

while ch != '':
    num = ord(ch)
    if num <= 90 and num >= 65:
        total += 1
        lFreq[num - 65] += 1
    elif num <= 122 and num >= 97:
        total += 1
        lFreq[num - 97] += 1
    ch = f.read(1)
# Find the amount of times each character occurs in a file

letters = [0] * 26 
for x in range(26):
    letters[x] = round((100 * lFreq[x] / total), 3)
letterchar = ['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z']
string = ""
for i in range(26):
     string = string + "(" + letterchar[i] +", "+ str(letters[i]) + ")\n"
print("Letter frequency percentage:\n" + string)


descFreq = [[0,'A'],[0,'B'],[0,'C'],[0,'D'],[0,'E'],[0,'F'],[0,'G'],[0,'H'],\
            [0,'I'],[0,'J'],[0,'K'],[0,'L'],[0,'M'],[0,'N'],[0,'O'],[0,'P'],\
            [0,'Q'],[0,'R'],[0,'S'],[0,'T'],[0,'U'],[0,'V'],[0,'W'],[0,'X'],\
                                    [0,'Y'],[0,'Z']]
for xx in range(26):
    descFreq[xx][0] = lFreq[xx]
# Each frequency that corresponded with a letter are now in the same i index of this Array (i,j)

descFreq.sort(reverse=True)
# Sorts the array in descending order

subTable = [['','E'],['','T'],['','A'],['','O'],['','N'],['','I'],['','S'],['','H'],\
            ['','R'],['','L'],['','D'],['','U'],['','C'],['','M'],['','W'],['','Y'],\
            ['','F'],['','G'],['','P'],['','B'],['','V'],['','K'],['','J'],['','X'],\
                                          ['','Q'],['','Z']]
# The 'A's will be replaced below

for y in range(26):
    subTable[y][0] = descFreq[y][1]
# This sub table now knows which letters should be replaced

f = open(sys.argv[1], 'r')
# Reset the file reader
ch = f.read(1).upper()
# Reading given file (all as capital letters)

while ch != '':
    for yy in range(26):
        if ch == subTable[yy][0]:
            ch = subTable[yy][1]
            # Sub letter
            break
    ff.write(ch)
    # Write to the decrypted text file
    ch = f.read(1).upper()
ff.write(ch)

f.close()
ff.close()

