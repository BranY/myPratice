cmake_minimum_required(VERSION 3.12)
project(cpp)

set(CMAKE_CXX_STANDARD 11)

set(CMAKE_BUILD_TYPE Debug)

add_executable(cpp src/other/hotelManageSystem2.cpp src/other/hotelManageSystem.c src/other/hotelManageSystem1.c src/other/hotelManageSystem.h)
