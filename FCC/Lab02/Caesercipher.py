import sys

def encrypt(plaintext, key):
    ciphertext = ""
    for i in range(len(plaintext)):
        ch = plaintext[i]
        #If it is an upper case letter
        if(ord(ch) <= 90 and ord(ch) >= 65):
            cch = (ord(ch) - 65 + key) % 26 + 65
            ciphertext += chr(cch)
        #If it is an lower case letter
        elif(ord(ch) <= 122 and ord(ch) >= 97):
            cch = (ord(ch) - 97 + key) % 26 + 97
            ciphertext += chr(cch)
        else:
            ciphertext += ch
    return ciphertext

def decrypt(ciphertext, key):
    plaintext = ""
    for i in range(len(ciphertext)):
        ch = ciphertext[i]
        #If it is an upper case letter
        if(ord(ch) <= 90 and ord(ch) >= 65):
            dch = (ord(ch) - 65 - key) % 26 + 65
            plaintext += chr(dch)
        #If it is an lower case letter
        elif(ord(ch) <= 122 and ord(ch) >= 97):
            dch = (ord(ch) - 97 - key) % 26 + 97
            plaintext += chr(dch)
        else:
            plaintext += ch
    return plaintext
    
message = input("Enter the message you would like to encrypt using Caeser (Shift) cipher: ")
key = input("Enter the key: ")
ctext = encrypt(message, int(key))
print("The encyrpted message is: " + ctext)
ptext = decrypt(ctext, int(key))
print("The decrypted ciphertext is: " + ptext)