#!/usr/bin/python

import sys

def readFile(filename):
    f = open(filename, 'r')
    ch = f.read(1).upper()
    s = ""

    MAXCHAR = 10000000000 
    i = 0
    # To make reading the file easier as there are 312 (12 x 26) combinations, set this to a lower number
    # I suggest 30 - 50 characters as thats roughly half a line and is long enough for it to be readable
    # MAXCHAR = 40

    while ch != '' and i != MAXCHAR:
        s = s + ch
        ch = f.read(1).upper()
        i += 1
    f.close()
    return s

def decrypt(ciphertext, a, b):
    plaintext = ""
    for i in range(len(ciphertext)):
        ch = ciphertext[i]
        #If it is an upper case letter
        if(ord(ch) <= 90 and ord(ch) >= 65):
            dch = (a * (ord(ch) - 65) - b) % 26 + 65
            plaintext += chr(dch)
        #If it is an lower case letter
        elif(ord(ch) <= 122 and ord(ch) >= 97):
            dch = (a * (ord(ch) - 97) - b) % 26 + 97
            plaintext += chr(dch)
        else:
            plaintext += ch
    return plaintext

aInverse = [1,3,5,7,9,11,15,17,19,21,23,25]
# These are the only values a can be for the affine cipher as their inverses for mod 26 exist

ciphertext = readFile(sys.argv[1])
# read cipher text file and store it as a string


newFile = "BF_DECRYPTED_" + sys.argv[1]
print("Note: " + newFile + " has been created and contains the decrypted ciphertext with 312 (12 x 26) combinations of parameters.")
ff = open(newFile, 'a')
# Decrypted text file created


for b in range(26):
    for a in aInverse:
        text = decrypt(ciphertext, a, b)
        ff.write("For keys: A = " + str(a) + ", B = " + str(b) + "\n")
        ff.write(text)
        ff.write("\n\n")
ff.close()

# The keys are a = 9 and b = 3

print("After analyzing the recovered plaintext in " + newFile + ", the keys are a = 9 and b = 3.")
print("The encrypted text is shown below:\n")
print(decrypt(ciphertext, 9, 3))












