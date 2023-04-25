# CMakeLists.txt 会自动创建两个变量，PROJECT_SOURCE_DIR和PROJECT_NAME
# ${PROJECT_SOURCE_DIR}：本CMakeLists.txt所在的文件夹路径
# ${PROJECT_NAME}：本CMakeLists.txt的project名称

# 检查cmake的版本，至少为3.26
cmake_minimum_required(VERSION 3.26)

project(copy
        VERSION 0.0.0.0
        DESCRIPTION "cp with progress bar"
        HOMEPAGE_URL "https://bougainvilleas.github.io/lotus"
        LANGUAGES C)

set(CMAKE_C_STANDARD 99)

#  默认存放库文件的位置
# 如果产生的是静态库并且没有指定 CMAKE_ARCHIVE_OUTPUT_DIRECTORY 则存放在该目录下，动态库也类似
set(LIBRARY_OUTPUT_PATH ${PROJECT_SOURCE_DIR}/build/lib)
# 默认存放静态库的文件夹位置
set(CMAKE_ARCHIVE_OUTPUT_DIRECTORY ${PROJECT_SOURCE_DIR}/build/lib/static)
# 默认存放动态库的文件夹位置
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${PROJECT_SOURCE_DIR}/build/lib/shared)

# 让库文件搜索以${PROJECT_SOURCE_DIR}/为基础
# 不设置
# #include "${PROJECT_SOURCE_DIR}/file/file.h"
# 设置
# #include "file/file.h"
include_directories(${PROJECT_SOURCE_DIR}/src)


# Recurse into the "src/file" and "src/progress" subdirectories. 
# This does not actually cause another cmake executable to run. 
# The same process will walk through the project's entire directory structure.
add_subdirectory(src/file)
add_subdirectory(src/progress)
# add_subdirectory(src/main)

# Add executable called "copy" that is built from the source files "copy.c".
# The extensions are automatically found.
add_executable(copy src/main/copy.c)

# 用于链接 库文件
# 根据调库原则，一:要有库的头文件，二:要有库生成的共享或者静态链接库
# include_directories 命令包含头文件
# target_link_libraries 包含库文件
# 第一个参数表示的是可执行文件的名称，这个文件执行需要链接那些库，就全写在后面
# Link the executable to the File and Progress library. 
# Since the File and Progress library has public include directories we will use those link directories
# when building copy
target_link_libraries(copy LINK_PUBLIC File Progress)