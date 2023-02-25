CC = gcc

#################################################
#a.out:909_usehotel.o 910_hotel.o
#	$(CC) -o hotel 909_usehotel.o 910_hotel.o
#909_usehotel.o:909_usehotel.c
#	$(CC) -o 909_usehotel.o -c 909_usehotel.c
#910_hotel.o:910_hotel.c
#	$(CC) -o 910_hotel.o -c 910_hotel.c
##################################################
#a.out:909_usehotel.o 910_hotel.o
#	$(CC) -o $@ $^
#909_usehotel.o:909_usehotel.c
#	$(CC) -o $@ -c $^
#910_hotel.o:910_hotel.c
#	$(CC) -o $@ -c $^
##################################################
.SUFFIXES:.c .o
.c.o:
	@echo $@ $^
	$(CC) -c -o $@ $^
a.out:909_usehotel.o 910_hotel.o
	$(CC) -o $@ $^
909_usehotel.o:909_usehotel.c
910_hotel.o:910_hotel.c
##################################################
clean:
	-rm *.o *.out
