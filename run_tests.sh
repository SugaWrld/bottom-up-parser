#!/bin/bash

# Define an array of test cases
test_cases=("succ_01" "succ_02" "succ_03" "succ_04" "succ_05" "succ_06" "succ_07" "succ_08" "succ_09" "succ_10")

# Loop through each test case
for test_case in "${test_cases[@]}"
do
    echo "$test_case"
    diff <(java Program "tests/$test_case.minc") "tests/output_$test_case.txt"
    read -p "Press Enter to continue to the next test case..."
done