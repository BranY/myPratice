# -----------------------------------------------------------------------------
# CMake project wrapper Makefile ----------------------------------------------
# -----------------------------------------------------------------------------

SHELL := /bin/bash
RM    := rm -rf
MKDIR := mkdir -p
LN := ln -s
BUILD_DIR ?= build
GENERATOR ?= Xcode

debug: BUILD_TYPE=Debug
debug: ./$(BUILD_DIR)/Makefile compile_commands.json
	@ $(MAKE) -C $(BUILD_DIR) -j8

release: BUILD_TYPE=Release
release: ./$(BUILD_DIR)/Makefile compile_commands.json
	@ $(MAKE) -C $(BUILD_DIR) -j8

all: ./$(BUILD_DIR)/Makefile compile_commands.json
	@ $(MAKE) -C $(BUILD_DIR) -j8

compile_commands.json:
	@ $(LN) $(BUILD_DIR)/compile_commands.json .

./$(BUILD_DIR)/Makefile:
	@  ($(MKDIR) $(BUILD_DIR) > /dev/null)
	@  (cd $(BUILD_DIR) > /dev/null 2>&1 && cmake .. -DCMAKE_BUILD_TYPE=$(BUILD_TYPE) -DCMAKE_EXPORT_COMPILE_COMMANDS=ON)

clean:
	@ $(MAKE) -C $(BUILD_DIR) clean

bench:
	@  (cd $(BUILD_DIR) > /dev/null && ctest -L bench --verbose)

test:
	@  (cd $(BUILD_DIR) > /dev/null && ctest -L unit --verbose)

workspace:
	@  ($(MKDIR) $(BUILD_DIR) > /dev/null)
	@ (cd $(BUILD_DIR) > /dev/null && cmake -G $(GENERATOR) ..)

distclean:
	@- $(RM) -rf ./$(BUILD_DIR)
