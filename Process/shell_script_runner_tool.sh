
#!/bin/bash

# Set paths
SRC_DIR="/c/WorkSpace/shellScriptProject"
LOG_DIR="/c/WorkSpace/Logs/shellScriptProjectLogs"
SUCCESS_LOG="$LOG_DIR/success.log"
ERROR_LOG="$LOG_DIR/error.log"

# Create necessary directories
mkdir -p "$LOG_DIR"

# Clear previous logs
> "$SUCCESS_LOG"
> "$ERROR_LOG"

# Function to run all shell script files
run_all_files() {
    echo "🔄 Running all shell script files one by one..."
    find "$SRC_DIR" -name "*.sh" | while read -r file; do
        file_name=$(basename "$file")
        echo "📄 Running $file_name..."
        echo "Running $file_name..." >> "$SUCCESS_LOG"
        bash "$file" >> "$SUCCESS_LOG" 2>>"$ERROR_LOG"
        if [ $? -eq 0 ]; then
            echo "✅ Ran $file_name successfully."
            echo "Ran $file_name successfully." >> "$SUCCESS_LOG"
        else
            echo "❌ Failed to run $file_name. See error log."
            echo "Failed to run $file_name." >> "$ERROR_LOG"
        fi
    done
    echo "✅ All files processed. Check logs in $LOG_DIR."
}

# Function to run files from a specific topic
run_topic_files() {
    echo "Enter the topic folder name:"
    read topic
    TOPIC_DIR="$SRC_DIR/$topic"
    if [ -d "$TOPIC_DIR" ]; then
        echo "🔄 Running shell script files in $topic..."
        find "$TOPIC_DIR" -name "*.sh" | while read -r file; do
            file_name=$(basename "$file")
            echo "📄 Running $file_name..."
            echo "Running $file_name..." >> "$SUCCESS_LOG"
            bash "$file" >> "$SUCCESS_LOG" 2>>"$ERROR_LOG"
            if [ $? -eq 0 ]; then
                echo "✅ Ran $file_name successfully."
                echo "Ran $file_name successfully." >> "$SUCCESS_LOG"
            else
                echo "❌ Failed to run $file_name. See error log."
                echo "Failed to run $file_name." >> "$ERROR_LOG"
            fi
        done
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
    rm -rf "$SRC_DIR/__pycache__"
    echo "✅ Clean up complete."
}

# Menu-driven interface
while true; do
    echo "Shell Script Runner Tool"
    echo "------------------------"
    echo "1. Run all shell script files"
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
