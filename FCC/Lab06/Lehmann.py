import random

###################################################
# Function lehmann
# Checks if p is prime
# The probability of p being prime is 1 - 1/(2**t)
###################################################
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

#################################################
# Returns the integer in binary form as a string
#################################################
def integerToBinary(integer):
    return format(integer, "b")

########################################
# Function squAndMult
# Returns: result = (base ** exp) mod n
########################################
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

# Testing modular_pow()

#base = (int)(input("Please enter the message or the ciphertext: "))
#exp = (int)(input("Please enter the public or private exponent (for ciphertext): "))
#n = (int)(input("Please enter public value 'n': "))

#result = modular_pow(base, exp, n)
#print("The ciphertext / recovered plaintext is: " + str(result))
def isPrime(n):
     #lowPrimes is all primes (sans 2, which is covered by the bitwise and operator)
     #under 1000. taking n modulo each lowPrime allows us to remove a huge chunk
     #of composite numbers from our potential pool without resorting to Rabin-Miller
     lowPrimes =   [3,5,7,11,13,17,19,23,29,31,37,41,43,47,53,59,61,67,71,73,79,83,89,97
                   ,101,103,107,109,113,127,131,137,139,149,151,157,163,167,173,179
                   ,181,191,193,197,199,211,223,227,229,233,239,241,251,257,263,269
                   ,271,277,281,283,293,307,311,313,317,331,337,347,349,353,359,367
                   ,373,379,383,389,397,401,409,419,421,431,433,439,443,449,457,461
                   ,463,467,479,487,491,499,503,509,521,523,541,547,557,563,569,571
                   ,577,587,593,599,601,607,613,617,619,631,641,643,647,653,659,661
                   ,673,677,683,691,701,709,719,727,733,739,743,751,757,761,769,773
                   ,787,797,809,811,821,823,827,829,839,853,857,859,863,877,881,883
                   ,887,907,911,919,929,937,941,947,953,967,971,977,983,991,997]
     if (n >= 3):
         if (n&1 != 0):
             for p in lowPrimes:
                 if (n == p):
                    return True
                 if (n % p == 0):
                     return False
             return lehmann(n, 60)
     return False

'''
Function generateLargePrime
Generates a large prime and calls isPrime() to see if its prime
Kehrer, Paul. 2009. "Generating (Very) Large Primes." LANGUI.SH. https://langui.sh/2009/03/07/generating-very-large-primes/
I have made changes
'''
def generateLargePrime(k):
     #k is the desired bit length
     while True:
        #randrange is mersenne twister and is completely deterministic
        #unusable for serious crypto purposes
         n = random.randrange(2**(k-1),2**(k))
         if isPrime(n) == True:
             return n

p = generateLargePrime(64)
q = generateLargePrime(64)
print("p: "+ str(p))
print("q: "+ str(q))

n = p * q
print(n)

num = input("Enter a number above 1000 to see if it is prime: ")
if(lehmann(int(num), 20)):
    print("Likely it is prime")
else:
    print("It is composite")
