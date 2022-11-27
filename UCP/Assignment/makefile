CC = gcc
EXEC = ASSIGNMENT
CFLAGS = -Wall -ansi -pedantic
OBJ = main.o fileio.o bracketcheck.o linkedlist.o newSleep.o


$(EXEC) : $(OBJ)
	$(CC) $(OBJ) -o $(EXEC)

main.o : main.c fileio.h bracketcheck.h
	$(CC) $(CFLAGS) main.c -c

fileio.o : fileio.c fileio.h
	$(CC) $(CFLAGS) fileio.c -c

bracketcheck.o : bracketcheck.c bracketcheck.h linkedlist.h newSleep.o
	$(CC) $(CFLAGS) bracketcheck.c -c

newSleep.o : newSleep.c newSleep.h
	$(CC) $(CFLAGS) newSleep.c -c

linkedlist.o : linkedlist.c linkedlist.h
	$(CC) $(CFLAGS) linkedlist.c -c

ifdef STACK=1
CFLAGS += -D STACK=1
STACK=1: clean $(EXEC)
endif

clean:
	rm -f $(EXEC) $(OBJ)
