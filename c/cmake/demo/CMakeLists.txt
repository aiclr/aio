# CMakeLists.txt 会自动创建两个变量，PROJECT_SOURCE_DIR和PROJECT_NAME
# ${PROJECT_SOURCE_DIR}：本CMakeLists.txt所在的文件夹路径
# ${PROJECT_NAME}：本CMakeLists.txt的project名称

# 检查cmake的版本，至少为3.19
cmake_minimum_required(VERSION 3.26)
# 第一个参数为项目名
project(demo C)
#
set(CMAKE_C_STANDARD 11)

#  默认存放库文件的位置
# 如果产生的是静态库并且没有指定 CMAKE_ARCHIVE_OUTPUT_DIRECTORY 则存放在该目录下，动态库也类似
set(LIBRARY_OUTPUT_PATH ${PROJECT_SOURCE_DIR}/lib)
# 默认存放静态库的文件夹位置
set(CMAKE_ARCHIVE_OUTPUT_DIRECTORY ${PROJECT_SOURCE_DIR}/lib/static)
# 默认存放动态库的文件夹位置
set(CMAKE_LIBRARY_OUTPUT_DIRECTORY ${PROJECT_SOURCE_DIR}/lib/shared)

# 让库文件搜索以${PROJECT_SOURCE_DIR}/include/为基础
# 不设置
# #include "${PROJECT_SOURCE_DIR}/include/shared/swap.h"
# 设置
# #include "shared/swap.h"
include_directories(${PROJECT_SOURCE_DIR}/include/)

# 生成动态库 第一参数为库名，生成的库文件，会自动在名字前面加上lib  eg:lib_swap 后缀.so
add_library(_swap SHARED src/shared/swap.c)
# 生成静态库 第一参数为库名，生成的库文件，会自动在名字前面加上lib  eg:lib_max 后缀.a
add_library(_max src/static/max.c)
# 生成可执行文件 main函数 第一个参数为可执行文件名
add_executable(hello example/hi.c)

# 用于链接 库文件
# 根据调库原则，一:要有库的头文件，二:要有库生成的共享或者静态链接库
# include_directories命令包含头文件
# target_link_libraries包含库文件
# 第一个参数表示的是可执行文件的名称，这个文件执行需要链接那些库，就全写在后面
target_link_libraries(hello _swap _max)