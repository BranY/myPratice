# c++

dp: dynamic programming



### gen-cmake
```
-h show this message
-t <type>, --type=<type> generate project with <type>. Supported types:
    app - generate execurable application
    shared - generate dynamically linked library
    static - generate archives of object files
-n <name>, --name=<name> project name
-s <standard>, --standard=<standard> C++ standard. C++11 is used by default
    available options are: 03, 11, 14
-p <package1,package2,packageN>, --packages=<package1,package2,packageN> comma separated
    list of libraries to link with
```
generate CMakeLists.txt with C++11 and BOOST libray linkage

python3 gen-cmake.py -t app -n cpp -s 11 -p boost

