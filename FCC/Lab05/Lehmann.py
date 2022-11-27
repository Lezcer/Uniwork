import random

###################################################
# Function isPrime
# Checks if p is prime
# The probability of p being prime is 1 - 1/(2**t)
###################################################
def lehmann(p, t):
    i = 0
    check = True
    while(check and i != t):
        a = random.randint(2, p-1)
        exp = (p-1) / 2
        r = ((int)(a ** exp)) % p
        if(r != 1 and r != p-1):
            check = False
        i += 1
    return check

num = input("Enter a number above 1000 to see if it is prime: ")
if(lehmann(int(num), 20)):
    print("It's prime! :)")
else:
    print("It's not prime :(")
