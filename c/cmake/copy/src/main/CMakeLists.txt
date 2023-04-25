# Add executable called "copy" that is built from the source files "copy.c".
# The extensions are automatically found.
add_executable(copy copy.c)

# 用于链接 库文件
# 根据调库原则，一:要有库的头文件，二:要有库生成的共享或者静态链接库
# include_directories 命令包含头文件
# target_link_libraries 包含库文件
# 第一个参数表示的是可执行文件的名称，这个文件执行需要链接那些库，就全写在后面
# Link the executable to the File and Progress library. 
# Since the File and Progress library has public include directories we will use those link directories
# when building copy
target_link_libraries(copy File Progress)