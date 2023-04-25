# Create a static library called "File" which includes the source file "File.c".
# The extension is already found. Any number of sources could be listed here.
add_library(File file.c)
# add_library(File SHARED file.c)

# Make sure the compiler can find include files for our file static library
# when other libraries or executables link to Hello
target_include_directories(File PUBLIC ${CMAKE_CURRENT_SOURCE_DIR})