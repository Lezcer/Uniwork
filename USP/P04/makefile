CC = gcc
EXEC = test
CFLAGS = -Wall -ansi -pedantic -Werror -g
OBJ = LinkedList.o LinkedListTest.o

$(EXEC) : $(OBJ)
	$(CC) $(OBJ) -o $(EXEC)

LinkedList.o : LinkedList.c LinkedList.h
	$(CC) $(CFLAGS) LinkedList.c -c

LinkedListTest.o : LinkedListTest.c LinkedList.h
	$(CC) $(CFLAGS) LinkedListTest.c -c

clean:
	rm -f $(EXEC) $(OBJ)
