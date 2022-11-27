'''
Name:       Salah Mahamod
Student ID: 20152428
Date:       10/05/2022
Due:        22/05/2022

Purpose:    Implementing RSA Encryption and Decryption
'''
 
import random

# Complexity of Algorithm
KEY_LENGTH = 128            
PQ_LENGTH = KEY_LENGTH / 2
PUBLIC_KEY_E = 2 ** 16 + 1
BLOCK_SIZE = 10

def main():
    print("RSA Algorithm")
    filename = "RSA-test.txt" #input("Name of file you would like to encrypt: ")

    print("\nPhase 1: Key Generation")
    n, e, d= generateKeys()
    print("Kpub = (" + str(n) + ", " + str(e) + ")" + "\n   d = " + str(d))

    print("\nPhase 2: Encryption")
    print("The " + filename + " file is encrypted with public exponent, e = " + str(e)+ ".")
    plaintext = readFile(filename)
    encryptedtext = encryption(plaintext, e, n)
    eFilename = "ENCRYPTED_HEX_" + filename
    writeFile(eFilename, encryptedtext.encode('utf-8').hex())
    print(eFilename + " has been created an contains the ciphertext." )

    print("\nPhase 3: Decryption")
    print("The ciphertext is decrypted with private exponent, d = " + str(d)+ ".")
    ciphertext = encryptedtext
    recoveredtext = decryption(ciphertext, d, n)
    rFilename = "RECOVERED_" + filename
    writeFile(rFilename, recoveredtext)
    print(rFilename + " has been created an contains the recovered plaintext." )

'''
Function modular_pow
Returns: result = (base ** exp) mod n

Obtained from my work on FCC Practical 06
(accessed 13/05/2022)
'''
def modular_pow(base, exp, n):
    # Ensure the base is less than n
    base = base % n
     
    if(base == 0):
        return 0
    result = 1
    while(exp > 0) :
        # if exp is odd
        if ((exp & 1) == 1) :
            result = (result * base) % n
        # exp is even
        exp = exp >> 1
        base = (base * base) % n
         
    return result

'''
Function lehmann
Checks if p is prime
The probability of p being prime is 1 - 1/(2**t)

Obtained from my work on FCC Practical 05 (but then made a call to modular_pow())
(accessed 13/05/2022)
'''
def lehmann(p, t):
    i = 0
    check = True
    while(check and i != t):
        a = random.randint(2, p-1)
        exp = (int)((p-1) / 2)
        r = modular_pow(a, exp, p)
        if(r != 1 and r != p-1):
            check = False
        i += 1
    return check

'''
Function isPrime
Firstly checks if integer is among the low primes, then checks if it divisble by them, if integer isn't then it is passed on to lehmann() primality test
doesn't check for integer = 2
The initial check removes invalid integers, which reduces load as the Lehmann algorithm doesn't need to be called according to Paul, 2009.

Based on code from P. Kehrer, https://langui.sh/2009/03/07/generating-very-large-primes/ I have modified P.Kehrer's code so it uses the lehmann() primality 
test I implemented.(accessed 10/05/2022)
Kehrer, Paul. 2009. "Generating (Very) Large Primes." LANGUI.SH. https://langui.sh/2009/03/07/generating-very-large-primes/
'''
def isPrime(integer):
    lowPrimes= [3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97
               ,101,103,107,109,113,127,131,137,139,149,151,157,163,167,173,179
               ,181,191,193,197,199,211,223,227,229,233,239,241,251,257,263,269
               ,271,277,281,283,293,307,311,313,317,331,337,347,349,353,359,367
               ,373,379,383,389,397,401,409,419,421,431,433,439,443,449,457,461
               ,463,467,479,487,491,499,503,509,521,523,541,547,557,563,569,571
               ,577,587,593,599,601,607,613,617,619,631,641,643,647,653,659,661
               ,673,677,683,691,701,709,719,727,733,739,743,751,757,761,769,773
               ,787,797,809,811,821,823,827,829,839,853,857,859,863,877,881,883
               ,887,907,911,919,929,937,941,947,953,967,971,977,983,991,997]
    # These prime integers under 1000 will be iterated over to ensure that;
    # (a) integer isn't among this list - if it is then it is prime and,
    # (b) integer isn't a composite number that is a product of these low integers - if it then it is not prime

    if (integer >= 3): # integer is greater than 3
         if (integer&1 != 0): # integer is not even
             for prime in lowPrimes:
                 if (integer == prime):
                    return True # (a)
                 if (integer % prime == 0):
                     return False # (b)
             return lehmann(integer, 500)
    return False # either integer is less than 3 or integer is

'''
Function generateLargePrime
Generates a large prime and calls isPrime() to see if its prime
size is the desired length for the prime integer

Based on code from P. Kehrer, https://langui.sh/2009/03/07/generating-very-large-primes/
(accessed 10/05/2022)
Kehrer, Paul. 2009. "Generating (Very) Large Primes." LANGUI.SH. https://langui.sh/2009/03/07/generating-very-large-primes/
'''
def generateLargePrime(size):
     
     while(True): # Never ending loop
         integer = random.randrange(2**(size-1),2**(size))
         if isPrime(integer) == True: # Integer is prime (high probability)
             return integer # exit the loop here

'''
Function euclidean
Returns: greatest common denominator (gcd) of num1 and num2
Assumption: num1 and num2 >= 0

Obtained from my work on FCC Practical 03
(accessed 13/05/2022)
'''
def euclidean(num1, num2):
    if(num1 == num2): # If the two integers are equal return their value
        return num1
    
    if(num2 == 0): # If integer 2 is equal to zero then return whatever integer 1 is
        return num1

    while(num1 != 0):
        if(num1 < num2): # If num1 is less than num2 switch the values
            num1, num2 = num2, num1
        #print(str(num1) + " " + str(num2)) # Debugging comment
        num1 = num1 % num2
    return num2

'''
Function extendedEuclidean
Returns: gcd of num1 & num2, and two coefficients s and t such that, s(num1) + t(num2) = gcd(num1, num2)
Assumption: num1 and num2 >= 0

Obtained from my work on FCC Practical 03
(accessed 13/05/2022)

This code is based on the pseudocode given in Paar, Christof. and Pelzl, Jan. 2009. Understanding Cryprtography. Leuven: Springer   
'''
def extendedEuclidean(num1, num2):
    if num2 == 0: # If num2 is zero
        return (num1, 1, 0)
    elif num1 == 0: # If num1 is zero
        return (num2, 0, 1)
    else:
        s2, t2, s1, t1 = 1, 0, 0, 1 # Initialisation
        while num2 > 0:
            q = num1 // num2 # Floor division
            r, s, t = num1 - num2 * q, s2 - q * s1, t2 - q * t1
            num1, num2, s2, t2, s1, t1 = num2, r, s1, t1, s, t # Extended Euclidean 
            #print(str(num1) + " " + str(num2)) # Debugging comment
    return num1, s2, t2

'''
Function generateKeys
The steps are outlined as comments
Returns: n, e and d. n and e (public exponent) are the public key. d (private exponent) is the private key
'''
def generateKeys():
    gcd = 0
    d = 0
    while(gcd != 1 or d < 0):
        # Step 1 Choosing two large primes
        p = generateLargePrime(PQ_LENGTH)
        q = generateLargePrime(PQ_LENGTH)
        # Step 2 Computing n, which is the product of the two primes
        n = p * q

        # Step 3 Computing Phi of n, which is the number of integers from 0 to n-1 that are coprime with n
        phiN = (p-1) * (q-1)
        # This is based on Euler's Phi Function, which is the product of the prime factors subtracted by 1
        # n was the product of primes (large) p and q

        # Step 4 Select the public exponent e
        e = PUBLIC_KEY_E

        # Step 5 Compute private key d
        gcd, d, x = extendedEuclidean(e, phiN)
    '''
    print("p is", p)
    print("q is", q)
    print("n is ", n)
    print("phi(n) is", phiN)
    print("e is", e)
    print("gcd(e, phi(n)) should be equal to 1 and is", euclidean(e, phiN))
    print("d is", d)
    print("e * d mod phi(n) should be equal to 1, indicating they're modular inverses and is ", (e*d) % phiN)
    # debugging comments
    '''

    return n, e, d

'''
Function intToStrCheck
Ensures that the integer has a length that is divisible by 3
Returns: An integer as a string
'''
def intToStrCheck(integer):
    string = str(integer) 
    n = len(string) % 3 # If the number of digits isn't divisible by 3 add a n * '0' at the front
    if(n!=0):
        n = 3-n
    string = n*'0' + string
    return string

'''
Function encryption
Encrypts a plaintext message in decimal using RSA encryption scheme
ciphertext = plaintext ** public exponent % n
Note: This enryption algorthim encrypts BLOCK_SIZE bytes at a time and seperates blocks of ciphertext with a space
'''
def encryption(plaintext, e, n):
    ciphertext = ""
    for i in range(0, len(plaintext), BLOCK_SIZE):
        j = i + BLOCK_SIZE # Index for the range
        if(j >= len(plaintext)): # If j is out of the upper bound
            j = len(plaintext) # set it as the last character
        
        block = plaintext[i:j] # block to be encrypted
        
        block = stringToDecimal(block) # Convert all character in each block into 3 digits
        
        encryptedBlock =  intToStrCheck(modular_pow(int(block), e, n)) # Encrypt and ensure block length is divisible by 3
        
        ciphertext = ciphertext + decimalToString(encryptedBlock) + " " # Convert each 3 digits back to characters and add a space in between ciphertexts

        if(j==len(plaintext)):
            ciphertext = ciphertext[:len(ciphertext) - 1] # remove the last character which is a space
    return ciphertext

'''
Function decryption
Decrypts a ciphertext message in decimal using RSA decryption scheme
recovered plaintext = ciphertext ** private exponent % n
'''
def decryption(ciphertext, d, n):
    recoveredtext = ""
    for block in ciphertext.split(" "): # block to be decrypted 
        if(block != ""):
            
            block = stringToDecimal(block) # Convert all characters in each block into 3 digits
            
            recoveredBlock = intToStrCheck(modular_pow(int(block), d, n)) # Decrypt and ensure block length is divisible by 3
            
            recoveredtext = recoveredtext + decimalToString(recoveredBlock) # Convert each 3 digits back to characters
    return recoveredtext

'''
Function readFile
Returns: The entire file in a single string

Obtained from my work on FCC Practical 01
(accessed 14/05/2022)
'''
def readFile(filename):
    f = open(filename, 'r')
    file = f.read(-1) # Read the entire file
    f.close()
    return file

'''
Function writeFile
writes a string to the specified file

Obtained from my work on FCC Assignment 1
(accessed 14/05/2022)
'''
def writeFile(filename, string):
    # write string to a file
    f = open(filename, 'w')
    f.write(string)
    f.close()

'''
Function stringToDecimal
Returns: Each character in the string as a 3 digit integer
These 3 digit integers are concatenated to one another
'''
def stringToDecimal(string):
    decimal = ""
    for character in string:
        decimal = decimal + str(format(ord(character), '03d'))
        # Convert the character to its decimal ASCII value and format it to be 3 digits
    return decimal

'''
Function decimalToString
Returns: Converts 3 integers to their ASCII equivalent (decimal to char)
'''
def decimalToString(integer):
    string = ""
    for i in range(0, len(integer), 3):
        string = string + chr(int(integer[i:i+3]))
        # Convert neigbouring 3 integers into a decimal and use that to retrieve the character equivalent
    return string

main() # Call main

'''
References

Paar, Christof. and Pelzl, Jan. 2009. Understanding Cryptography. Leuven: Springer. https://www.crypto-textbook.com/ 
Kehrer, Paul. 2009. "Generating (Very) Large Primes." LANGUI.SH. https://langui.sh/2009/03/07/generating-very-large-primes/ 
Paar, Christof. 2014. Lecture 11: Number Theory for PKC: Euclidean Algorithm, Euler's Phi Function & Euler's Theorem. YouTube video. 01:31:01. https://www.youtube.com/watch?v=fq6SXByItUI 
Paar, Christof. 2014. Lecture 12: The RSA Cryptosystem and Efficient Exponentiation by Christof Paar. YouTube video. 01:28:26. https://www.youtube.com/watch?v=QSlWzKNbKrU 
Paar, Christof. 2014. Lecture 18: Digital Signatures and Security Services by Christof Paar. YouTube video. 01:17:14. https://www.youtube.com/watch?v=jbBe4AS5pk0&t=349s 
Clay, Adam. 2020. Digital signatures in RSA. YouTube video. 09:02. https://www.youtube.com/watch?v=rLR8WcXy03Q 
Neso Academy. 2021. Extended Euclidean Algorithm (Solved Example 1). YouTube video. 10:15. https://www.youtube.com/watch?v=lq285DDdmtw
'''