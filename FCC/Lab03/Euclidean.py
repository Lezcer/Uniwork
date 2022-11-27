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



print(euclidean(0, 17))
print(extendedEuclidean(0,17))

        
        