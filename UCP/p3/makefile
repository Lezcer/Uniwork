CC = gcc
EXEC = program
CFLAGS = -Wall -ansi -pedantic -Werror
OBJ = order.o numbers.o


$(EXEC) : $(OBJ)
	$(CC) $(OBJ) -o $(EXEC)

#comment
order.o : order.c order.h
	$(CC) $(CFLAGS) order.c -c

numbers.o : numbers.c order.h
	$(CC) $(CFLAGS) numbers.c -c

clean:
	rm -f $(EXEC) $(OBJ)
