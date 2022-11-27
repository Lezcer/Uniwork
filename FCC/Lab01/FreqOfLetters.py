import sys
import matplotlib.pyplot as plt

f = open(sys.argv[1], 'r')
ch = f.read(1)
total = 0
tt = 0

letters = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
letterchar = ['A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z']


while ch != '':
    num = ord(ch)
    if num <= 90 and num >= 65:
        total += 1
        letters[num - 65] += 1
    elif num <= 122 and num >= 97:
        total += 1
        letters[num - 97] += 1
    ch = f.read(1)

for x in range(26):
    letters[x] = round((100 * letters[x] / total), 3)

plt.bar(letterchar, letters)
plt.title("Relative frequency of English letters.")
plt.xlabel("Letters")
plt.ylabel("Relative frequency (%)")
plt.show()
    


