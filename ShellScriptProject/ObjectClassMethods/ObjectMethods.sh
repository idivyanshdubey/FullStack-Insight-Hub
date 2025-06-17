#!/bin/bash

# Custom logger function
log_info() {
    echo "[INFO] $(date '+%Y-%m-%d %H:%M:%S') - $1"
}

# Check if enough arguments are provided
if [ "$#" -ne 6 ]; then
    log_info "Usage: $0 <ISBN1> <Title1> <Author1> <ISBN2> <Title2> <Author2>"
    exit 1
fi

# Assign command-line arguments to variables
isbn1="$1"
title1="$2"
author1="$3"
isbn2="$4"
title2="$5"
author2="$6"

# Function to compare books
compare_books() {
    if [ "$1" == "$4" ] && [ "$2" == "$5" ] && [ "$3" == "$6" ]; then
        log_info "Books are equal."
    else
        log_info "Books are not equal."
    fi
}

# Function to get hash code (simple sum of ASCII values)
get_hash_code() {
    local str="$1$2$3"
    local hash=0
    for (( i=0; i<${#str}; i++ )); do
        hash=$((hash + $(printf "%d" "'${str:$i:1}")))
    done
    echo $hash
}

# Function to clone book details
clone_book() {
    echo "$1,$2,$3"
}

# Log book details
log_info "Book 1: ISBN='$isbn1', Title='$title1', Author='$author1'"
log_info "Book 2: ISBN='$isbn2', Title='$title2', Author='$author2'"

# Compare books
compare_books "$isbn1" "$title1" "$author1" "$isbn2" "$title2" "$author2"

# Get hash codes
hash1=$(get_hash_code "$isbn1" "$title1" "$author1")
hash2=$(get_hash_code "$isbn2" "$title2" "$author2")
log_info "Book 1 Hash Code: $hash1"
log_info "Book 2 Hash Code: $hash2"

if [ "$hash1" == "$hash2" ]; then
    log_info "Books have the same hash code."
else
    log_info "Books have different hash codes."
fi

# Log class info (simulated)
log_info "Class Info: Book"

# Clone book 1
cloned_book=$(clone_book "$isbn1" "$title1" "$author1")
IFS=',' read -r cloned_isbn cloned_title cloned_author <<< "$cloned_book"
log_info "Cloned Book: ISBN='$cloned_isbn', Title='$cloned_title', Author='$cloned_author'"
