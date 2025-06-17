#!/bin/bash

# Set paths
SRC_DIR="/c/WorkSpace/pythonProject"
LOG_DIR="/c/WorkSpace/Logs/pythonProjectLogs"
SUCCESS_LOG="$LOG_DIR/success.log"
ERROR_LOG="$LOG_DIR/error.log"

# Set custom Python interpreter path
PYTHON_INTERPRETER="/c/Program Files/Python313/python.exe"

# Create necessary directories
mkdir -p "$LOG_DIR"

# Clear previous logs
> "$SUCCESS_LOG"
> "$ERROR_LOG"

# Normalize line endings to Unix (LF)
normalize_file() {
    sed -i 's/\r$//' "$1"
}

# Function to run Python files in a directory
run_python_files_in_dir() {
    local dir="$1"
    find "$dir" -name "*.py" | while read -r file; do
        file_name=$(basename "$file")
        echo "📄 Running $file_name..."
        echo "Running $file_name..." >> "$SUCCESS_LOG"

        normalize_file "$file"

        # Skip files that use input()
        if grep -q "input(" "$file"; then
            echo "⚠️  Skipping $file_name (uses input, not suitable for batch run)." | tee -a "$ERROR_LOG"
            continue
        fi

        output=$("$PYTHON_INTERPRETER" "$file" 2>&1)
        exit_code=$?

        echo "$output"
        echo "$output" >> "$SUCCESS_LOG"

        if [ $exit_code -eq 0 ]; then
            echo "✅ Ran $file_name successfully."
            echo "Ran $file_name successfully." >> "$SUCCESS_LOG"
        else
            echo "$output" >> "$ERROR_LOG"
            echo "❌ Failed to run $file_name. Exit code: $exit_code"
            echo "Failed to run $file_name. Exit code: $exit_code" >> "$ERROR_LOG"

            # Detect common Python errors
            if echo "$output" | grep -q "EOFError"; then
                echo "⚠️  Input error (EOFError) in $file_name" | tee -a "$ERROR_LOG"
            elif echo "$output" | grep -q "SyntaxError"; then
                echo "⚠️  Syntax error in $file_name" | tee -a "$ERROR_LOG"
            elif echo "$output" | grep -q "Exception"; then
                echo "⚠️  General exception in $file_name" | tee -a "$ERROR_LOG"
            fi
        fi
    done
}

# Function to run all Python files
run_all_files() {
    echo "🔄 Running all Python files one by one..."
    run_python_files_in_dir "$SRC_DIR"
    echo "✅ All files processed. Check logs in $LOG_DIR."
}

# Function to run files from a specific topic
run_topic_files() {
    echo "Enter the topic folder name:"
    read topic
    TOPIC_DIR="$SRC_DIR/$topic"
    if [ -d "$TOPIC_DIR" ]; then
        echo "🔄 Running Python files in $topic..."
        run_python_files_in_dir "$TOPIC_DIR"
        echo "✅ All files processed. Check logs in $LOG_DIR."
    else
        echo "❌ Topic folder $topic not found."
    fi
}

# Function to view logs
view_logs() {
    echo "Choose log to view:"
    echo "1. Success log"
    echo "2. Error log"
    read log_choice
    case $log_choice in
        1) cat "$SUCCESS_LOG";;
        2) cat "$ERROR_LOG";;
        *) echo "❌ Invalid choice.";;
    esac
}

# Function to clean up compiled classes
clean_up() {
    echo "🔄 Cleaning up compiled classes..."
    find "$SRC_DIR" -type d -name "__pycache__" -exec rm -rf {} +
    echo "✅ Clean up complete."
}

# Menu-driven interface
while true; do
    echo ""
    echo "Python Runner Tool"
    echo "------------------"
    echo "1. Run all Python files"
    echo "2. Run files from a specific topic"
    echo "3. View logs"
    echo "4. Clean up"
    echo "5. Exit"
    read choice
    case $choice in
        1) run_all_files;;
        2) run_topic_files;;
        3) view_logs;;
        4) clean_up;;
        5) exit;;
        *) echo "❌ Invalid choice.";;
    esac
done
