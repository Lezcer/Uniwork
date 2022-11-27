CC = gcc
EXEC = ./pipesim
CFLAGS = -Wall -ansi -pedantic -g
OBJ = LinkedList.o testharness.o ipc.o

$(EXEC) : $(OBJ)
	$(CC) $(OBJ) -o $(EXEC)

LinkedList.o : LinkedList.c LinkedList.h
	$(CC) $(CFLAGS) LinkedList.c -c
testharness.o : testharness.c testharness.h LinkedList.h
	$(CC) $(CFLAGS) testharness.c -c
ipc.o : ipc.c ipc.h LinkedList.h testharness.h
	$(CC) $(CFLAGS) ipc.c -c

clean:
	rm -f $(EXEC) $(OBJ)
