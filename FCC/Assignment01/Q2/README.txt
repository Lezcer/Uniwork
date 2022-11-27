This Folder covers Question 2 of the Assignment "Implement DES"

The DES.py script encrypts and decrypts .txt files by implementing the Data Encryption Standard (DES)

To run this script:
python3.9 DES.py <PLAINTEXT>.txt

As the user you will be prompted to enter a key, any key is accepted.

The program writes the encrypted text to ENCRYPTED_HEX_<PLAINTEXT>.txt.
The program writes the decrypted text to RECOVERED_<PLAINTEXT>.txt.
    
Note: There are two different padding strategies Implemented, the key acquired from user input is pa
      -dded to 64 bits using the equal padding strategy. Essentially the bits are repeated till the 
      key is 64 bits long. read more at function pad(bits) line #30.
      
      The PKCS5 padding strategy is used to pad the last block of the plaintext. In essence, if the n
      -umber of bits is divisible by 64, another block of 8 bytes of 00001000 (08 in Hexadecimal) is 
      appended, otherwise the missing bytes are appended to the last block as 8 subrtracted by the nu
      -mber of missing bytes -> x (in binary), multiplied by x.
      For e.g: For a last block with 1 missing byte, i.e. size 56 out of 64 bits, the 1 missing byte 
      (64 - 56) / 8 = 1 (x), in binary 00000001 is appended to the last block which makes it 1 (x) ti 
      -mes. read more about this at function padding(bits) line #118.
      
      After decryption the last byte is type casted to an integer and this will indicate the extra by
      -tes appended, which will be unpadded. read more about this at function unpad(block) line #131.