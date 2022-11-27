
def stringToBinary(string):
    # converts a string into binary
    ints=[]
    bits=[]
    for letter in string:
        ints.append(ord(letter))
        # Covert each letter to its ascii value and append that to the list
    for number in ints:
        bits.append(format(number, '08b'))
        # Convert each number to its binary equivalent, keeping the leading zeros and append it to bits as a string
    byte = ''.join(bits)
    return byte

def binaryToString(bits):
    # converts a string into binary
    string = ""
    for i in range(0, len(bits), 8):
        string = string + chr(int(bits[i:i+8], 2))
    return string

def lengthCheck(bits):
    # ensures bits are padded or chopped
    if len(bits) < 64:
        bits = pad(bits)
    elif len(bits) > 64:
        bits = chop(bits)
    return bits
        
def pad(string):
    # Equal padding strategy
    iterations = int(64 / len(string))
    remainder = 64 % len(string)
    string = string * iterations
    string = string + string[:remainder]
    return string

def chop(key):
    key = key[:64]
    # Simple chopping strategy, ignore everything after the first 64 bits
    return key

def readFileAsBinary(filename):
    # read the whole file as 0 or 1 bits, and return it as a string
    f = open(filename, 'r')
    file = f.read(-1)
    file = stringToBinary(file)
    f.close()
    return file

def readFile(filename):
    # read the whole file, and return it as a string
    f = open(filename, 'r')
    file = f.read(-1)
    f.close()
    return file
def writeFile(filename, string):
    # write string to a file
    f = open(filename, 'w')
    f.write(string)
    f.close()

def binaryToHex(string):
    # convert a binary string to a hexadecimal string
    hexadecimal = ""
    replace = {"0000" : '0',
               "0001" : '1',
               "0010" : '2',
               "0011" : '3',
               "0100" : '4',
               "0101" : '5',
               "0110" : '6',
               "0111" : '7',
               "1000" : '8',
               "1001" : '9',
               "1010" : 'A',
               "1011" : 'B',
               "1100" : 'C',
               "1101" : 'D',
               "1110" : 'E',
               "1111" : 'F' }
    # Dictionary to replace 4 bits with a hexidecimal
    for i in range(0, len(string), 4):
        hexadecimal = hexadecimal + replace[string[i:i+4]]
    return hexadecimal

def hexToBinary(string):
    # convert a hexadecimal string to a binary string
    bits = ""
    replace =  {'0' : "0000",
                '1' : "0001",
                '2' : "0010",
                '3' : "0011",
                '4' : "0100",
                '5' : "0101",
                '6' : "0110",
                '7' : "0111",
                '8' : "1000",
                '9' : "1001",
                'A' : "1010",
                'B' : "1011",
                'C' : "1100",
                'D' : "1101",
                'E' : "1110",
                'F' : "1111" }
    # Dictionary to replace the hexadecimal with 4 bits
    for i in range(len(string)):
        bits = bits + replace[string[i]]
    return bits

def permutation(bits, table, finalBits):
    result = ""
    for i in range(finalBits):
        result = result + bits[table[i] - 1]
        # '- 1' because the tables start from 1 and not 0
    return result

def padding(block):
    # PKCS5 Padding
    replace = {1 : '01' * 1,
               2 : '02' * 2,
               3 : '03' * 3,
               4 : '04' * 4,
               5 : '05' * 5,
               6 : '06' * 6,
               7 : '07' * 7}
    remainder = (len(block) / 8) % 8
    block = block + hexToBinary(replace[8 - remainder])
    return block

def unpad(block):
    string = binaryToHex(block)
    number = int(string[14:]) # Last 2 hexadecimals indicate the padding
    block = block[:64 - number * 8]
    return block

def leftShift(bits, round):
    # Shift the leftmost bit to the right, once or twice
    table= { 1: 1,  2: 1, 
             3: 2,  4: 2,
             5: 2,  6: 2, 
             7: 2,  8: 2,
             9: 1, 10: 2, 
            11: 2, 12: 2,
            13: 2, 14: 2, 
            15: 2, 16: 1}
    number = table[round+1]
    for i in range(number):
        bits = bits[1:len(bits)] + bits[0]
    return bits

def rightShift(bits, round):
    # Shift the rightmost bit to the left, once or twice
    table= { 1: 0,  2: 1, 
             3: 2,  4: 2,
             5: 2,  6: 2, 
             7: 2,  8: 2,
             9: 1, 10: 2, 
            11: 2, 12: 2,
            13: 2, 14: 2, 
            15: 2, 16: 1}
    number = table[round+1]
    for i in range(number):
        bits = bits[len(bits) - 1] + bits[0:(len(bits) - 1)]
    return bits

def scheduleKey(key, i):
    # Schedule the key for each round of the feistel network
    left  = key[0:28]
    right = key[28:56]

    left = leftShift(left, i)
    right = leftShift(right, i)   
    
    key = left + right
    return key

def decScheduleKey(key, i):
    # Schedule the key for each round of the feistel network
    left  = key[0:28]
    right = key[28:56]

    left = rightShift(left, i)
    right = rightShift(right, i)   
    
    key = left + right
    return key

def xor(string1, string2):
    # XOR operation
    result = ""
    for i in range(len(string1)):
        if string1[i] == string2[i]:
            result = result + '0'
        else:
            result = result + '1'
    return result

def sbox(bits):
    result = ""
    for i in range(0, 48, 6):
        input  = bits[i:i+6]
        column = int(input[1:5], 2)
        row    = int(input[0] + input[5], 2)
        index  = int(i / 6)
        result = result + format(sboxes[index][row][column], '08b')
    return result

def fFunction(bits, key):
    # The infamous fFunction

    # Expand the right hand side from 32 bits to 48
    expanded = permutation(bits, expanP, 48) # 32 bits -> 48 bits

    # XOR operations with the expanded RHS with the 48-bit key
    result = xor(expanded, key) # 48 bits

    # Shorten the length of the result to 32 bits with the use of sboxes
    sbox_string = sbox(result) # 48 bits -> 32 bits

    # Permute this result using fFuncP table
    finalResult = permutation(sbox_string, fFuncP, 32) # 32 bits -> 32 bits

    return finalResult

def feistalNetwork(block, key):
    left = block[:32]
    right = block[32:64]
    
    for j in range(16):
        #########################################################################
        # For each of the 16 rounds in the Feistel network

        key = scheduleKey(key, j)
        k = permutation(key, pc2, 48) # 56 bits -> 48
        # Now you have the key 'k' for the round
            
        result = fFunction(right, k)
        # fFunction for the right
            
        left = xor(left, result) 
        # xor the result to the left and set this as left

        left, right = right, left
        # Set right as left and left as right
        #########################################################################

    feistalResult = right + left
    # combine left and right
    return feistalResult

def decFeistalNetwork(block, key):
    left = block[:32]
    right = block[32:64]
    
    for j in range(16):
        #########################################################################
        # For each of the 16 rounds in the Feistel network

        key = decScheduleKey(key, j)
        k = permutation(key, pc2, 48) # 56 bits -> 48
        # Now you have the key 'k' for the round
            
        result = fFunction(right, k)
        # fFunction for the right
            
        left = xor(left, result) 
        # xor the result to the left and set this as left

        left, right = right, left
        # Set right as left and left as right

        #########################################################################

    feistalResult = right + left
    # combine left and right
    return feistalResult

pc1=       [57, 49, 41, 33, 25, 17,  9,
            1, 58, 50, 42, 34, 26, 18,
            10,  2, 59, 51, 43, 35, 27,
            19, 11,  3, 60, 52, 44, 36,
            63, 55, 47, 39, 31, 23, 15,
            7, 62, 54, 46, 38, 30, 22,
            14,  6, 61, 53, 45, 37, 29,
            21, 13,  5, 28, 20, 12,  4]
# Paar, Christof. and Pelzl, Jan. 2009. Understanding Cryprtography (Table 3.11). Leuven: Springer
pc2=       [14, 17, 11, 24,  1,  5,
            3, 28, 15,  6, 21, 10,
            23, 19, 12,  4, 26,  8,
            16,  7, 27, 20, 13,  2,
            41, 52, 31, 37, 47, 55,
            30, 40, 51, 45, 33, 48,
            44, 49, 39, 56, 34, 53,
            46, 42, 50, 36, 29, 32]
# Paar, Christof. and Pelzl, Jan. 2009. Understanding Cryprtography (Table 3.12). Leuven: Springer
iP=        [58, 50, 42, 34, 26, 18, 10,  2,
            60, 52, 44, 36, 28, 20, 12,  4,
            62, 54, 46, 38, 30, 22, 14,  6,
            64, 56, 48, 40, 32, 24, 16,  8,
            57, 49, 41, 33, 25, 17,  9,  1,
            59, 51, 43, 35, 27, 19, 11,  3,
            61, 53, 45, 37, 29, 21, 13,  5,
            63, 55, 47, 39, 31, 23, 15,  7]
# Paar, Christof. and Pelzl, Jan. 2009. Understanding Cryprtography (Fig. 3.8). Leuven: Springer
inverseIP= [40,  8, 48, 16, 56, 24, 64, 32,
            39,  7, 47, 15, 55, 23, 63, 31,
            38,  6, 46, 14, 54, 22, 62, 30,
            37,  5, 45, 13, 53, 21, 61, 29,
            36,  4, 44, 12, 52, 20, 60, 28,
            35,  3, 43, 11, 51, 19, 59, 27,
            34,  2, 42, 10, 50, 18, 58, 26,
            33,  1, 41,  9, 49, 17, 57, 25]
# Paar, Christof. and Pelzl, Jan. 2009. Understanding Cryprtography (Fig. 3.9). Leuven: Springer        
expanP=    [32, 1 , 2 , 3 , 4 , 5 , 4 , 5,
            6 , 7 , 8 , 9 , 8 , 9 , 10, 11,
            12, 13, 12, 13, 14, 15, 16, 17,
            16, 17, 18, 19, 20, 21, 20, 21,
            22, 23, 24, 25, 24, 25, 26, 27,
            28, 29, 28, 29, 30, 31, 32, 1 ]
# Paar, Christof. and Pelzl, Jan. 2009. Understanding Cryprtography (Table 3.1). Leuven: Springer
fFuncP=    [16,  7, 20, 21,
            29, 12, 28, 17,
             1, 15, 23, 26,
             5, 18, 31, 10,
             2,  8, 24, 14,
            32, 27,  3,  9,
            19, 13, 30,  6,
            22, 11,  4, 25]
# Paar, Christof. and Pelzl, Jan. 2009. Understanding Cryprtography (Table 3.10). Leuven: Springer
sboxes=   [[[14, 4, 13, 1, 2, 15, 11, 8, 3, 10, 6, 12, 5, 9, 0, 7],
          [0, 15, 7, 4, 14, 2, 13, 1, 10, 6, 12, 11, 9, 5, 3, 8],
          [4, 1, 14, 8, 13, 6, 2, 11, 15, 12, 9, 7, 3, 10, 5, 0],
          [15, 12, 8, 2, 4, 9, 1, 7, 5, 11, 3, 14, 10, 0, 6, 13]],
            
         [[15, 1, 8, 14, 6, 11, 3, 4, 9, 7, 2, 13, 12, 0, 5, 10],
          [3, 13, 4, 7, 15, 2, 8, 14, 12, 0, 1, 10, 6, 9, 11, 5],
          [0, 14, 7, 11, 10, 4, 13, 1, 5, 8, 12, 6, 9, 3, 2, 15],
          [13, 8, 10, 1, 3, 15, 4, 2, 11, 6, 7, 12, 0, 5, 14, 9]],
   
         [[10, 0, 9, 14, 6, 3, 15, 5, 1, 13, 12, 7, 11, 4, 2, 8],
          [13, 7, 0, 9, 3, 4, 6, 10, 2, 8, 5, 14, 12, 11, 15, 1],
          [13, 6, 4, 9, 8, 15, 3, 0, 11, 1, 2, 12, 5, 10, 14, 7],
          [1, 10, 13, 0, 6, 9, 8, 7, 4, 15, 14, 3, 11, 5, 2, 12]],
       
         [[7, 13, 14, 3, 0, 6, 9, 10, 1, 2, 8, 5, 11, 12, 4, 15],
          [13, 8, 11, 5, 6, 15, 0, 3, 4, 7, 2, 12, 1, 10, 14, 9],
          [10, 6, 9, 0, 12, 11, 7, 13, 15, 1, 3, 14, 5, 2, 8, 4],
          [3, 15, 0, 6, 10, 1, 13, 8, 9, 4, 5, 11, 12, 7, 2, 14]],
        
         [[2, 12, 4, 1, 7, 10, 11, 6, 8, 5, 3, 15, 13, 0, 14, 9],
          [14, 11, 2, 12, 4, 7, 13, 1, 5, 0, 15, 10, 3, 9, 8, 6],
          [4, 2, 1, 11, 10, 13, 7, 8, 15, 9, 12, 5, 6, 3, 0, 14],
          [11, 8, 12, 7, 1, 14, 2, 13, 6, 15, 0, 9, 10, 4, 5, 3]],
       
         [[12, 1, 10, 15, 9, 2, 6, 8, 0, 13, 3, 4, 14, 7, 5, 11],
          [10, 15, 4, 2, 7, 12, 9, 5, 6, 1, 13, 14, 0, 11, 3, 8],
          [9, 14, 15, 5, 2, 8, 12, 3, 7, 0, 4, 10, 1, 13, 11, 6],
          [4, 3, 2, 12, 9, 5, 15, 10, 11, 14, 1, 7, 6, 0, 8, 13]],
         
         [[4, 11, 2, 14, 15, 0, 8, 13, 3, 12, 9, 7, 5, 10, 6, 1],
          [13, 0, 11, 7, 4, 9, 1, 10, 14, 3, 5, 12, 2, 15, 8, 6],
          [1, 4, 11, 13, 12, 3, 7, 14, 10, 15, 6, 8, 0, 5, 9, 2],
          [6, 11, 13, 8, 1, 4, 10, 7, 9, 5, 0, 15, 14, 2, 3, 12]],
        
         [[13, 2, 8, 4, 6, 15, 11, 1, 10, 9, 3, 14, 5, 0, 12, 7],
          [1, 15, 13, 8, 10, 3, 7, 4, 12, 5, 6, 11, 0, 14, 9, 2],
          [7, 11, 4, 1, 9, 12, 14, 2, 0, 6, 10, 13, 15, 3, 5, 8],
          [2, 1, 14, 7, 4, 10, 8, 13, 15, 12, 9, 0, 3, 5, 6, 11]]]
# Paar, Christof. and Pelzl, Jan. 2009. Understanding Cryprtography (Tables 3.2 - 3.9). Leuven: Springer

def encrypt(plaintext, initialKey):
    ciphertext = ""

    if(len(plaintext) % 64 == 0):
        plaintext = plaintext + hexToBinary("08"*8)

    for i in range(0, len(plaintext), 64):
        block = plaintext[i:i+64]
        if(len(block) % 64 != 0):
            block = padding(block)
      
        #############################################################################
        # For each 64 bit block

        block = permutation(block, iP, 64) # 64 bits -> 64 bits
        # Initial Permutation
        
        feistalResult = feistalNetwork(block, initialKey)
        # Put it through the feistal network

        encryptedBlock = permutation(feistalResult, inverseIP, 64) # 64 bits -> 64 bits
        # Final Permutation (i.e. Inverse of Initial Permutation)

        ciphertext = ciphertext + encryptedBlock
        # Finally, the ciphertext
        #############################################################################
    
    return ciphertext

def decrypt(ciphertext, initialKey):
    recoveredtext = ""

    for i in range(0, len(ciphertext), 64):
        block = ciphertext[i:i+64]      
        #############################################################################
        # For each 64 bit block

        block = permutation(block, iP, 64) # 64 bits -> 64 bits
        # Initial Permutation
        
        feistalResult = decFeistalNetwork(block, initialKey)
        # Put it through the feistal network

        decryptedBlock = permutation(feistalResult, inverseIP, 64) # 64 bits -> 64 bits
        # Final Permutation (i.e. Inverse of Initial Permutation)

        recoveredtext = recoveredtext + decryptedBlock
        # Finally, the recovered text
        #############################################################################

    index = int(((len(recoveredtext) / 64) - 1) * 64)
    # Starting index of last block
    ## Note: this will have the padding

    lastBlock = recoveredtext[index:index+64]
    # last block

    recoveredtext = recoveredtext[:index]

    lastBlock = unpad(lastBlock)
    # Remove padding

    recoveredtext = recoveredtext + lastBlock
    # add the unpadded last block to recoveredtext

    return recoveredtext

import sys
print("Please enter a key: ")
key = input()
key = stringToBinary(key)
# Key is converted to a binary string
key = lengthCheck(key)
# The key is padded / chopped (if necessary) to 64 bits

key = permutation(key, pc1, 56) # 64 bits -> 56
# Initial key permutation, parity bits are dropped. Permuted Choice 1

plaintext = readFileAsBinary(sys.argv[1])
# Read file as bits

encryptedText = encrypt(plaintext, key)
# Encrypt the file using DES

newFile1 = "ENCRYPTED_HEX_" + sys.argv[1]
writeFile(newFile1, binaryToHex(encryptedText))
# Convert the bits to hexadecimals and write it to a new file with prefix 'ENCRYPTED_HEX_'

print("Note: " + newFile1 + " has been created and contains the ciphertext as hexidecimals.")

ciphertext = hexToBinary(readFile(newFile1))
# Read the saved file as bits

recoveredPlaintext = decrypt(ciphertext, key)
# Decrypt the file using DES

newFile2 = "RECOVERED_" + sys.argv[1]
writeFile(newFile2, binaryToString(recoveredPlaintext))
# Convert the bits to integers, then characters and write it to a new file with prefix 'RECOVERED_'

print("Note: " + newFile2 + " has been created and contains the recovered plaintext.")

    