CC = gcc
EXEC = test
CFLAGS = -Wall -ansi -pedantic -Werror
OBJ = linkedlist.o LinkedListTest.o


$(EXEC) : $(OBJ)
	$(CC) $(OBJ) -o $(EXEC)

#comment
linkedlist.o : linkedlist.c linkedlist.h
	$(CC) $(CFLAGS)     linkedlist.c -c

LinkedListTest.o : LinkedListTest.c linkedlist.h
	$(CC) $(CFLAGS)     LinkedListTest.c -c

clean:
	rm -f $(EXEC) $(OBJ)
