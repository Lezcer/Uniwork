CC = gcc
EXEC = rpg
CFLAGS = -Wall -ansi -pedantic
OBJ = io.o linkedlist.o main.o newSleep.o


$(EXEC) : $(OBJ)
	$(CC) $(OBJ) -o $(EXEC)

#comment
linkedlist.o : linkedlist.c linkedlist.h
	$(CC) $(CFLAGS)     linkedlist.c -c

io.o : io.c io.h linkedlist.h
	$(CC) $(CFLAGS)     io.c -c

main.o : main.c main.h io.h linkedlist.h newSleep.h
	$(CC) $(CFLAGS)     main.c -c

newSleep.o : newSleep.c newSleep.h
	$(CC) $(CFLAGS)     newSleep.c -c

clean:
	rm -f $(EXEC) $(OBJ)
