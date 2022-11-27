CC = gcc
EXEC = three
CFLAGS = -Wall -ansi -pedantic -Werror
OBJ = display.o plot.o


$(EXEC) : $(OBJ)
	$(CC) $(OBJ) -o $(EXEC)

#comment
display.o : display.c plot.h
	$(CC) $(CFLAGS)     display.c -c

plot.o : plot.c plot.h
	$(CC) $(CFLAGS)     plot.c -c

clean:
	rm -f $(EXEC) $(OBJ)
