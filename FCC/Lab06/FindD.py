e = int(input("enter e: "))
phiN = int(input("enter phi(n): "))

for d in range(0, phiN):
    remainder = (e * d) % phiN
    if(remainder == 1):
        print("d is: "+ str(d))