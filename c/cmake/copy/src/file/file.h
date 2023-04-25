#ifndef FILE_LIBRARY_H
#define FILE_LIBRARY_H

#include <stdio.h>

long get_file_size(FILE *file, const char *filename);
FILE * open_file(const char *filename, const char *modes);
void close_file(FILE *file, const char *filename);
void set_buffer(FILE *file, char *bufer, int __modes, size_t buffersize);
void copy(FILE *inputfile, FILE *outputfile, long filesize, void (*show)(long total,long deal));

#endif