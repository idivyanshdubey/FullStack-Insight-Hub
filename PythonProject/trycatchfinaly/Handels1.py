def main():
    try:
        with open("nonexistent.txt", "r") as file:
            content = file.read()
    except FileNotFoundError:
        print("File not found!")

if __name__ == "__main__":
    main()
