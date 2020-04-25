# CMake 最低版本号要求
cmake_minimum_required(VERSION 3.10)

# 项目信息
project(cpp)

set(CMAKE_CXX_STANDARD 11)

# 查找当前目录下的所有源文件
# 并将名称保存到 DIR_SRCS 变量
aux_source_directory(DP DIR_SRCS)

# 添加 ${name} 子目录
# add_subdirectory(${name})

# 生成链接库
# add_library (MathFunctions ${DIR_LIB_SRCS})

# 指定生成目标
add_executable(cpp ${DIR_SRCS})

# 查找当前目录下的所有源文件
# 并将名称保存到 DIR_LIB_SRCS 变量
# aux_source_directory(. DIR_LIB_SRCS)

# 生成链接库
# add_library (MathFunctions ${DIR_LIB_SRCS})

# 添加链接库
# target_link_libraries(Demo MathFunctions)

# 1. https://blog.csdn.net/ktigerhero3/article/details/70313350
# 2. https://www.hahack.com/codes/cmake/
