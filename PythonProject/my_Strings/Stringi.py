def main():
    str_ = "Hello World"
    print(str_.upper())

    str1 = ""
    for i in range(len(str_)):
        s = str_[i]
        str1 = s + str1  # Prepend character to reverse

    print("reverse " + str1)

if __name__ == "__main__":
    main()
