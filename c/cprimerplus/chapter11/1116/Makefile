CC=gcc
.SUFFIXES:.c .o
.c.o:
	@echo $@ %^
	$(CC) -c -o $@ $^
a.out:1116_put_put.o 1115_put2.o 1114_put1.o
	$(CC) -o $@ $^
1115_put2.o:1115_put2.c
1114_put1.o:1114_put1.c
clean:
	rm *.o *.out

