def main():
    # Equivalent to StringTokenizer with space delimiter
    text = "Java is fun"
    tokens = text.split(" ")
    for token in tokens:
        print(token)

    print("---")

    # Equivalent to split with multiple delimiters (comma and space)
    text2 = "Java,is fun"
    import re
    tokens2 = re.split(r"[,\s]", text2)
    for token in tokens2:
        if token:  # Avoid printing empty strings
            print(token)

if __name__ == "__main__":
    main()
