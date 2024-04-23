#!/bin/bash

# Define an array of test cases
#test_cases=("succ_01" "succ_02" "succ_03" "succ_04" "succ_05" "succ_06" "succ_07" "succ_08" "succ_09" "succ_10")
test_cases=("fail_01a" "fail_01b" "fail_01c" "fail_01d" "fail_02a" "fail_02b" "fail_03a" "fail_04a" "fail_04b" "fail_04c",
"fail_04d", "fail_04e");

# Loop through each test case
for test_case in "${test_cases[@]}"
do
    echo "$test_case"
    diff <(java Program "tests/$test_case.minc") "tests/output_$test_case.txt"
    read -p "Press Enter to continue to the next test case..."
done