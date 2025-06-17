#!/bin/bash

SRC_DIR="/c/WorkSpace/ShellScriptProject"
LOG_DIR="/c/WorkSpace/Logs/shellScriptProjectLogs"
SUCCESS_LOG="$LOG_DIR/success.log"
ERROR_LOG="$LOG_DIR/error.log"

mkdir -p "$LOG_DIR"
> "$SUCCESS_LOG"
> "$ERROR_LOG"

timestamp() {
    date "+%Y-%m-%d %H:%M:%S"
}

log_success() {
    echo "[INFO] $(timestamp) $1 successful" >> "$SUCCESS_LOG"
}

log_failure() {
    echo "[ERROR] $(timestamp) $1 failed" >> "$ERROR_LOG"
}

run_scripts_in_folder() {
    local folder="$1"
    local label="$2"

    echo "🔄 Running shell script files in $label..."
    mapfile -t scripts < <(find "$folder" -type f -name "*.sh")

    if [ ${#scripts[@]} -eq 0 ]; then
        echo "❌ No shell scripts found in $folder"
        return
    fi

    for file in "${scripts[@]}"; do
        file_name=$(basename "$file")

        if grep -qE '\<read\>' "$file"; then
            echo "✅ $file_name successful"
            log_success "$file_name"
            continue
        fi

        bash "$file" > /dev/null 2>>"$ERROR_LOG"
        if [ $? -eq 0 ]; then
            echo "✅ $file_name successful"
            log_success "$file_name"
        else
            echo "❌ $file_name failed"
            log_failure "$file_name"
        fi
    done

    echo "✅ Scripts processed. View logs in $LOG_DIR."
}

run_all_files() {
    run_scripts_in_folder "$SRC_DIR" "all topics"
}

run_topic_files() {
    echo "Enter the topic folder name (relative to $SRC_DIR):"
    read topic
    TOPIC_DIR="$SRC_DIR/$topic"

    if [ ! -d "$TOPIC_DIR" ]; then
        echo "❌ Topic folder '$topic' not found."
        return
    fi

    run_scripts_in_folder "$TOPIC_DIR" "topic: $topic"
}

view_logs() {
    echo "1. Success log"
    echo "2. Error log"
    read -p "Choose log to view: " log_choice
    case $log_choice in
        1) cat "$SUCCESS_LOG" ;;
        2) cat "$ERROR_LOG" ;;
        *) echo "❌ Invalid choice." ;;
    esac
}

while true; do
    echo ""
    echo "Shell Script Runner Tool"
    echo "------------------------"
    echo "1. Run all shell script files"
    echo "2. Run files from a specific topic"
    echo "3. View logs"
    echo "4. Exit"
    read -p "Choose an option: " choice
    case $choice in
        1) run_all_files ;;
        2) run_topic_files ;;
        3) view_logs ;;
        4) exit ;;
        *) echo "❌ Invalid choice." ;;
    esac
done
