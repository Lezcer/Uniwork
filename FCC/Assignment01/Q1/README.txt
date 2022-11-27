This Folder covers Question 1 of the Assignment "Attack a cipher"

(a) The LetterAnalysis.py script performs a letter frequency attack.

    To run this script:
    python3.9 LetterAnalysis.py <CIPHERTEXT>.txt

    The program writes the deciphered-text to LA_DECRYPTED_<CIPHERTEXT>.txt.
    
    Note: this will not be accurate as the actual letter frequency of letters may not follow the pattern 
          used when substituting.

(b) The BruteForce.py script performs an exhaustive key search by going through different combinations f
    -or the parameters "a" and "b" in the affine cipher. 

    To run this script:
    python3.9 BruteForce.py <CIPHERTEXT>.txt

    The program writes the different combinations of the deciphered-text to BF_DECRYPTED_<CIPHERTEXT>.txt

    Note: Reading the BF_DECRYPTED_<CIPHERTEXT>.txt maybe overwhelming as it displays the initial file
          312 times, where 311 are unreadable. Edit line #10 and change the MAXCHAR variable to a number 
          between 30 - 100. In this case, only 30 - 100 characters are decrypted using the affine cipher
          which is enough to distinguish plain english from gibberish and makes the deciphered-text reada
          -ble.
    