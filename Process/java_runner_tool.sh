#!/bin/bash

# Set Java JDK path using verified short path
export JAVA_HOME="/c/PROGRA~1/ECLIPS~1/JDK-21~1.7-H"
export PATH="$JAVA_HOME/bin:$PATH"

# Set base paths
SRC_DIR="/c/WorkSpace/javaProject/src/com"
CLASS_DIR="/c/WorkSpace/javaProject/temp_classes"
LOG_DIR="/c/WorkSpace/Logs/javaProjectLogs"
SUCCESS_LOG="$LOG_DIR/success.log"
ERROR_LOG="$LOG_DIR/error.log"

# Create necessary directories
mkdir -p "$CLASS_DIR"
mkdir -p "$LOG_DIR"

# Clear previous logs
> "$SUCCESS_LOG"
> "$ERROR_LOG"

# Array to hold classes with main methods
declare -a MAIN_CLASSES=()

# Function to compile all Java files
compile_all() {
    echo "📦 Compiling all Java files..."
    find "$SRC_DIR" -name "*.java" > all_sources_unix.txt

    # Convert Unix paths to Windows format for javac
    > all_sources.txt
    while read -r unix_path; do
        cygpath -w "$unix_path" >> all_sources.txt
    done < all_sources_unix.txt

    javac -d "$CLASS_DIR" @all_sources.txt 2>>"$ERROR_LOG"
    if [ $? -eq 0 ]; then
        echo "✅ Compilation successful." | tee -a "$SUCCESS_LOG"
        
        # Identify classes with main methods
        while read -r file; do
            if grep -q "public static void main" "$file"; then
                file_name=$(basename "$file" .java)
                package_line=$(grep "^package " "$file")
                if [[ $package_line ]]; then
                    package_name=$(echo "$package_line" | sed 's/package //' | sed 's/;//')
                    full_class_name="$package_name.$file_name"
                else
                    full_class_name="$file_name"
                fi
                MAIN_CLASSES+=("$full_class_name")
            fi
        done < all_sources_unix.txt
    else
        echo "❌ Compilation failed. Check error log." | tee -a "$ERROR_LOG"
    fi

    rm -f all_sources.txt all_sources_unix.txt
}

# Function to run all main classes
run_all_main_classes() {
    echo "🚀 Running all classes with main methods..."
    for class in "${MAIN_CLASSES[@]}"; do
        echo "▶️ Running $class..." | tee -a "$SUCCESS_LOG"
        java -cp "$CLASS_DIR" "$class" | tee -a "$SUCCESS_LOG"
    done
}

# Function to run all Java files (compile then run)
run_all() {
    compile_all
    run_all_main_classes
    echo "✅ All files processed. Check logs in $LOG_DIR."
}

# Function to run Java files from a specific topic
run_topic() {
    echo "Enter the topic folder name (e.g., arrays, oopsConcept):"
    read topic
    local topic_dir="$SRC_DIR/$topic"
    if [ -d "$topic_dir" ]; then
        echo "🔄 Compiling and running Java files in $topic_dir..."
        find "$topic_dir" -name "*.java" > topic_sources_unix.txt

        # Convert Unix paths to Windows format for javac
        > topic_sources.txt
        while read -r unix_path; do
            cygpath -w "$unix_path" >> topic_sources.txt
        done < topic_sources_unix.txt

        javac -d "$CLASS_DIR" @topic_sources.txt 2>>"$ERROR_LOG"
        if [ $? -eq 0 ]; then
            echo "✅ Compilation successful." | tee -a "$SUCCESS_LOG"
            while read -r file; do
                if grep -q "public static void main" "$file"; then
                    file_name=$(basename "$file" .java)
                    package_line=$(grep "^package " "$file")
                    if [[ $package_line ]]; then
                        package_name=$(echo "$package_line" | sed 's/package //' | sed 's/;//')
                        full_class_name="$package_name.$file_name"
                    else
                        full_class_name="$file_name"
                    fi
                    echo "▶️ Running $full_class_name..." | tee -a "$SUCCESS_LOG"
                    java -cp "$CLASS_DIR" "$full_class_name" | tee -a "$SUCCESS_LOG"
                fi
            done < topic_sources_unix.txt
        else
            echo "❌ Compilation failed. Check error log." | tee -a "$ERROR_LOG"
        fi
        rm -f topic_sources.txt topic_sources_unix.txt
    else
        echo "❌ Topic folder $topic_dir not found."
    fi
}

# Function to view logs
view_logs() {
    echo "Select log to view:"
    echo "1. Success log"
    echo "2. Error log"
    read log_choice
    case $log_choice in
        1) less "$SUCCESS_LOG" ;;
        2) less "$ERROR_LOG" ;;
        *) echo "Invalid choice." ;;
    esac
}

# Function to clean up compiled class files
clean_classes() {
    echo "🧹 Cleaning up compiled class files..."
    rm -rf "$CLASS_DIR/*"
    echo "✅ Compiled class files cleaned."
}

# Main menu
while true; do
    echo "Java Runner Tool"
    echo "----------------"
    echo "1. Run all Java files"
    echo "2. Run a specific topic"
    echo "3. View logs"
    echo "4. Clean compiled classes"
    echo "5. Exit"
    read choice
    case $choice in
        1) run_all ;;
        2) run_topic ;;
        3) view_logs ;;
        4) clean_classes ;;
        5) exit 0 ;;
        *) echo "Invalid choice. Please try again." ;;
    esac
done
