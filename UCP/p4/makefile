CC = gcc
EXEC = test
CFLAGS = -Wall -ansi -pedantic -Werror
OBJ = Question4.o Question2.o colour.o


$(EXEC) : $(OBJ)
	$(CC) $(OBJ) -o $(EXEC)

#comment
Question4.o : Question4.c Question4.h
	$(CC) $(CFLAGS) Question4.c -c

colour.o : colour.c colour.h
	$(CC) $(CFLAGS) colour.c -c

Question2.o : Question2.c Question4.h colour.h
	$(CC) $(CFLAGS) Question2.c -c

clean:
	rm -f $(EXEC) $(OBJ)
